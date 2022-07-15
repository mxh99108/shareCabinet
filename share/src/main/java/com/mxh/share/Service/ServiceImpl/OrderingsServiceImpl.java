package com.mxh.share.Service.ServiceImpl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mxh.share.Service.OrderingsService;
import com.mxh.share.common.Result;
import com.mxh.share.entity.Box;
import com.mxh.share.entity.Orderings;
import com.mxh.share.entity.Price;
import com.mxh.share.mapper.BoxMapper;
import com.mxh.share.mapper.OrderingsMapper;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * author:erhui
 * version:1.0
 **/
@Service
public class OrderingsServiceImpl implements OrderingsService {

    @Autowired
    private OrderingsMapper orderingsMapper;

    @Autowired
    private BoxMapper boxMapper;

    @Override
    public Result<?> save(Orderings orderings) {
        // 判断箱子是否被使用
        Box result = boxMapper.selectOne(Wrappers.<Box>lambdaQuery()
                .eq(Box::getCabId, orderings.getCabId())
                .eq(Box::getBoxId, orderings.getBoxId())
                .eq(Box::getIsUsed, 1)
        );
        // 判断输入信息是否完整
        if (orderings.getCabId() == null || orderings.getBoxId() == null) {
            return Result.error("-1", "请输入完整信息后再试。");
        }

        // 判断箱子是否存在
        Box box = boxMapper.selectOne(Wrappers.<Box>lambdaQuery()
                .eq(Box::getBoxId, orderings.getBoxId())
                .eq(Box::getCabId, orderings.getCabId())
        );
        if (box == null) {
            return Result.error("-1", "该存储箱不存在，请确认后再次提交信息。");
        } else {
            if (result == null) {
                orderings.setTime(new Date());
//                orderings.setCost(2.0);
//                orderings.setCost(this.createCost(orderings.getOid()));
                orderings.setOpenPw(this.createOpenPw());
                orderings.setUid(orderings.getUid());
                orderings.setOid(orderings.getOid());
                orderingsMapper.insert(orderings);
                return Result.success(orderings);
            } else {
                return Result.error("-1", "该存储箱已被使用!");
            }
        }
    }

    @Override
    public Result delete(Long id) {
        orderingsMapper.deleteById(id);
        return Result.success();
    }

    // 生成随机且唯一的开箱密码
    public Integer createOpenPw() {
        Integer openPw;
        openPw = (int) ((Math.random() * 9 + 1) * 100000);
        System.out.println("密码为： " + openPw);
        Orderings result = orderingsMapper.selectOne(Wrappers.<Orderings>lambdaQuery()
                .eq(Orderings::getOpenPw, openPw)
                .eq(Orderings::getFinished, 1)
        );
        // 是否存在同样的开箱密码
        // 存在
        if (result != null) {
            this.createOpenPw();
        }
        return openPw;
    }

    /***
     * 生成费用
     *  前 8小时免费，8-24小时收费 2 元，24小时后，每小时加收 0.5 元
     */

    // 初始费用，0-8h
    double initCost = 0.0;
    // 总费用
    double cost;
    // 基础费用 8-24h
    double baseCost = 2.0;
    // 加收费用
    double addCost = 0.5;
    public Double createCost(Integer oid) {
        // 存放时间
        Integer usingTime = orderingsMapper.calTime(oid);
        if (usingTime <= 8) {
            System.out.println("费用为0");
            cost = 0.0;
            return cost;
        }
        if (usingTime <= 24 && usingTime > 8) {
            System.out.println("费用为2");
            cost = 2.0;
            return cost;
        }
        if (usingTime > 24) {
            cost = 2.0 + (usingTime - 24) * addCost;
            System.out.println("费用为" + cost);
            return cost;
        }
        cost = -1.0;
        return cost;
    }

    /***
     * 得到费用信息
     */
    public Price getPrice(){
        Price price = new Price();
        price.setInitCost(this.initCost);
        price.setBaseCost(this.baseCost);
        price.setAddCost(this.addCost);
        System.out.println(this.initCost + "+" + this.baseCost + "+" +this.addCost);
        return price;
    }

    /***
     * 设置费用信息
     * @param price
     * @return
     */
    @Override
    public Result setPrice(Price price) {
        Result<Object> result = new Result<>();
        try {
            initCost = price.getInitCost();
            baseCost = price.getBaseCost();
            addCost = price.getAddCost();
            System.out.println(initCost+baseCost+addCost);
            result.setCode("1");
        } catch (Exception e) {
            result.setCode("-1");
            result.setMsg("设置失败，请联系系统管理员");
        }
        System.out.println(result.toString());
        return result;
    }
}
