package com.mxh.share.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mxh.share.Service.UserService;
import com.mxh.share.common.Result;

import com.mxh.share.entity.User;
import com.mxh.share.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * author:erhui
 * version:1.0
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @GetMapping("/admin")
    public Result findAdmin(){
        return userService.findAdmin();
    }


    @PostMapping("/login")
    public Result login(@RequestBody User user){
        return userService.login(user);
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user){
        return userService.register(user);
    }

    @PostMapping
    public Result save(@RequestBody User user){
        userService.save(user);
        return Result.success();
    }

    @PutMapping
    public Result update(@RequestBody User user){
        userService.update(user);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        userService.delete(id);
        return Result.success();
    }

    @GetMapping()
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer currentPage,
                              @RequestParam(defaultValue = "") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        new Page<>(currentPage,pageSize);
        // 如果在前端展示数据为NULL 也会渲染到页面
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery().eq(User::getRid,3);
        if (StrUtil.isNotBlank(search)){
            wrapper.like(User::getName,search);
        }
        Page<User> userPage = userMapper.selectPage(new Page<>(currentPage, pageSize),wrapper);
        return Result.success(userPage);
    }

}
