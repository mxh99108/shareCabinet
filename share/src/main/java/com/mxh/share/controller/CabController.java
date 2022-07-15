package com.mxh.share.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mxh.share.Service.CabService;
import com.mxh.share.Service.UserService;
import com.mxh.share.common.Result;
import com.mxh.share.entity.Cab;
import com.mxh.share.mapper.CabMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * author:erhui
 * version:1.0
 **/
@RestController
@RequestMapping("/cab")
public class CabController {

    @Autowired
    CabService cabService;

    @Autowired
    CabMapper cabMapper;


    @PostMapping
    public Result save(@RequestBody Cab cab){
        cab.setOnUsing(0);
        cabService.save(cab);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody Cab cab){
        cabService.update(cab);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        cabService.delete(id);
        return Result.success();
    }

    @GetMapping
    public Result select(@RequestParam(defaultValue = "1") Integer currentPage,
                         @RequestParam(defaultValue = "10") Integer pageSize,
                         @RequestParam(defaultValue = "") Integer search
    ) {
        Result result = cabService.select(currentPage, pageSize, search);
        return Result.success(result);
    }
}
