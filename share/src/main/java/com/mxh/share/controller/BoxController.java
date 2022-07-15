package com.mxh.share.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mxh.share.Service.BoxService;
import com.mxh.share.Service.CabService;
import com.mxh.share.common.Result;
import com.mxh.share.entity.Box;
import com.mxh.share.entity.Orderings;
import com.mxh.share.mapper.BoxMapper;
import com.mxh.share.mapper.CabMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * author:erhui
 * version:1.0
 **/
@RestController
@RequestMapping("/box")
public class BoxController {

    @Autowired
    BoxService boxService;

    @Autowired
    BoxMapper boxMapper;

    @Autowired
    CabService cabService;

    @PostMapping("/toPut")
    public Result put(@RequestBody Box box){
        Result select = boxService.put(box);
        return select;
    }

    @PostMapping("/toTake")
    public Result take(@RequestBody Orderings orderings){
        Result select = boxService.take(orderings);
        return select;
    }

    @PostMapping
    public Result save(@RequestBody Box box){
        Result result = boxService.save(box);
        return result;
    }

    @PutMapping
    public Result update(@RequestBody Box box){
        Result result = boxService.update(box);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        boxService.delete(id);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer currentPage,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        new Page<>(currentPage,pageSize);
        // 如果在前端展示数据为NULL 也会渲染到页面
        LambdaQueryWrapper<Box> wrapper = Wrappers.<Box>lambdaQuery();
        if (StrUtil.isNotBlank(search)){
            wrapper.like(Box::getBoxId,search);
        }
        Page<Box> boxPage = boxMapper.selectPage(new Page<>(currentPage, pageSize),wrapper);
        return Result.success(boxPage);
    }

}
