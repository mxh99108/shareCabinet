package com.mxh.share.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mxh.share.Service.NewsService;
import com.mxh.share.Service.UserService;
import com.mxh.share.common.Result;
import com.mxh.share.entity.News;
import com.mxh.share.entity.User;
import com.mxh.share.mapper.NewsMapper;
import com.mxh.share.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * author:erhui
 * version:1.0
 **/
@RestController
@RequestMapping("/news")
public class newsController {

    @Autowired
    NewsService newsService;

    @Autowired
    NewsMapper newsMapper;

    @PostMapping
    public Result save(@RequestBody News news){
        System.out.println(news);
        newsService.save(news);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody News user){
        newsService.update(user);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        newsService.delete(id);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        new Page<>(pageNum,pageSize);
        // 如果在前端展示数据为NULL 也会渲染到页面
        LambdaQueryWrapper<News> wrapper = Wrappers.<News>lambdaQuery();
        if (StrUtil.isNotBlank(search)){
            wrapper.like(News::getTitle,search);
        }
        Page<News> newsPage = newsMapper.selectPage(new Page<>(pageNum, pageSize),wrapper);
        return Result.success(newsPage);
    }

}
