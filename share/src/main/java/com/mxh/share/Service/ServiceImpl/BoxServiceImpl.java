package com.mxh.share.Service.ServiceImpl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mxh.share.Service.BoxService;
import com.mxh.share.common.Result;
import com.mxh.share.entity.Box;
import com.mxh.share.entity.Cab;
import com.mxh.share.entity.Orderings;
import com.mxh.share.mapper.BoxMapper;
import com.mxh.share.mapper.CabMapper;
import com.mxh.share.mapper.OrderingsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * author:erhui
 * version:1.0
 **/
@Service
public class BoxServiceImpl implements BoxService {

    @Autowired
    BoxMapper boxMapper;

    @Autowired
    OrderingsMapper orderingsMapper;

    @Autowired
    OrderingsServiceImpl orderingsService;

    @Autowired
    CabMapper cabMapper;

    @Override
    public Result<?> save(Box box) {
        Cab cab = cabMapper.selectOne(Wrappers.<Cab>lambdaQuery()
                .eq(Cab::getId, box.getCabId())
        );
        if (cab == null ){
            return Result.error("-1","该存储柜不存在！");
        } else {
            box.setIsUsed(0);
            boxMapper.insert(box);
            return Result.success();
        }
    }

    @Override
    public Result update(Box box) {
        Integer cabId = box.getCabId();
        Cab result = cabMapper.selectOne(Wrappers.<Cab>lambdaQuery().eq(Cab::getId, cabId));
        if (result == null){
            return Result.error("-1","该存储箱不存在！");
        } else {
            boxMapper.updateById(box);
            return Result.success();
        }
    }

    @Override
    public Result delete(Long id) {
        boxMapper.deleteById(id);
        return Result.success();
    }

    // 存入前开箱门
    @Override
    public Result put(Box box) {
        // 判断信息是否为空
        if( box.getBoxId() == null || box.getCabId() == null){
            return Result.error("-1","请输入完整信息后再使用");
        }
        // 判断箱子是否存在
        Box result = boxMapper.selectOne(Wrappers.<Box>lambdaQuery()
                .eq(Box::getBoxId,box.getBoxId())
                .eq(Box::getCabId,box.getCabId()));

        if (result != null){
            // 判断箱子是否被使用
            if (result.getIsUsed() == 1){
                return Result.error("-1","该存储箱已被使用！");
            } else {
                result.setIsUsed(1);
                result.setTime(new Date());
                result.setUid(box.getUid());
                // 更新数据到数据库
                boxMapper.updateById(result);
                return Result.success();
            }
        } else {
            return Result.error("-1","该存储箱不存在！");
        }
    }

    // 取出物品
    @Override
    public Result take(Orderings orderings) {
        // 判断信息是否为空
        if( orderings.getBoxId() == null || orderings.getCabId() == null || orderings.getOpenPw() == null){
            return Result.error("-1","请输入完整信息后再使用");
        }
        // 到订单表查询是否存在匹配的数据
        Orderings result = orderingsMapper.selectOne(Wrappers.<Orderings>lambdaQuery()
                .eq(Orderings::getCabId, orderings.getCabId())
                .eq(Orderings::getBoxId, orderings.getBoxId())
                .eq(Orderings::getOpenPw, orderings.getOpenPw()));

        if (result == null){
            return Result.error("-1","密码错误，请确认后再尝试！");
        } else {
            Date time = result.getTime();
            // 有数据，且把数据清空
            Box box = boxMapper.selectOne(Wrappers.<Box>lambdaQuery()
                    .eq(Box::getCabId, orderings.getCabId())
                    .eq(Box::getBoxId, orderings.getBoxId())
                    .eq(Box::getTime,time));

            if (box == null){
                return Result.error("-1","密码错误，请确认后再尝试！");
            } else {
                // 设置 box 属性并更新
                box.setIsUsed(0);
                box.setUid(null);
                box.setTime(null);
                boxMapper.updateById(box);
                // 设置 orderings 属性并更新
                result.setFinished(1);
                Double cost = orderingsService.createCost(result.getOid());
                result.setCost(cost);
                orderingsMapper.updateById(result);
                return Result.success("已开箱，请及时取走物品并关门。您本次的费用为：" + cost + "元");
            }
        }
    }
}
