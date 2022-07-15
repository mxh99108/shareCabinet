package com.mxh.share.Service.ServiceImpl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mxh.share.Service.CabService;

import com.mxh.share.common.Result;
import com.mxh.share.entity.Cab;
import com.mxh.share.entity.Orderings;
import com.mxh.share.mapper.CabMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * author:erhui
 * version:1.0
 **/
@Service
public class CabServiceImpl implements CabService {

    @Autowired
    CabMapper cabMapper;

    @Override
    public Result<?> save(Cab cab) {
        cabMapper.insert(cab);
        return Result.success();
    }

    @Override
    public Result update(Cab cab) {
//        Integer sum = cab.getSum();
//        Integer onUsing = cab.getOnUsing();
//        cab.setNotUsing(sum - onUsing);
        cabMapper.updateById(cab);
        return Result.success();
    }

    @Override
    public Result delete(Long id) {
        cabMapper.deleteById(id);
        return Result.success();
    }

    @Override
    public Result select(Integer currentPage, Integer pageSize, Integer cabId) {
        List result;
        Integer size;
        Integer page = (currentPage-1) * pageSize;
        if (cabId == null){
            result = cabMapper.selectCab(page, pageSize);
            size = selectAll();
            System.out.println("数据大小为：" + size);
        } else {
            result = cabMapper.selectCabWithId(page, pageSize, cabId);
            size = 1;
            System.out.println("数据大小为：" + size);
        }

        return Result.success(result,size);
    }

    // 返回数据总数: total
    @Override
    public Integer selectAll() {
        List list = cabMapper.selectAll();
        int size = list.size();
        return size;
    }

}
