package com.mxh.share.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mxh.share.Service.OrderingsService;
import com.mxh.share.common.Result;
import com.mxh.share.entity.Orderings;
import com.mxh.share.entity.Price;
import com.mxh.share.mapper.OrderingsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * author:erhui
 * version:1.0
 **/
@RestController
@RequestMapping("/orderings")
public class OrderingsController {

    @Autowired
    OrderingsService orderingsService;

    @Autowired
    OrderingsMapper orderingsMapper;


    @PostMapping
    public Result save(@RequestBody Orderings orderings){
        orderings.setFinished(0);
        Result result = orderingsService.save(orderings);
        return Result.success(result);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        orderingsService.delete(id);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        System.out.println("pageNum = " + pageNum + "  pageSize = " + pageSize + " search= " + search);
        new Page<>(pageNum,pageSize);
        // 如果在前端展示数据为NULL 也会渲染到页面
        LambdaQueryWrapper<Orderings> wrapper = Wrappers.<Orderings>lambdaQuery();
        if (StrUtil.isNotBlank(search)){
            wrapper.like(Orderings::getOpenPw,search);
            pageNum = 1;
        }
        Page<Orderings> orderingsPage = orderingsMapper.selectPage(new Page<>(pageNum, pageSize),wrapper);
        return Result.success(orderingsPage);
    }

    @GetMapping("/getPrice")
    public Price getPrice(){
        Price price = orderingsService.getPrice();
        return price;
    }

    @PostMapping("/setPrice")
    public Result setPrice(@RequestBody Price price){
        Result result = orderingsService.setPrice(price);
        return result;
    }
}
