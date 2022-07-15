package com.mxh.share.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mxh.share.Service.HistoryService;
import com.mxh.share.Service.UserService;
import com.mxh.share.common.Result;
import com.mxh.share.entity.History;
import com.mxh.share.entity.Orderings;
import com.mxh.share.entity.User;
import com.mxh.share.mapper.HistoryMapper;
import com.mxh.share.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * author:erhui
 * version:1.0
 **/
@RestController
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    HistoryService historyService;

    @Autowired
    HistoryMapper historyMapper;

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id){
        historyService.delete(id);
        return Result.success();
    }

    @GetMapping
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search,
                              @RequestParam Integer uid
    ){
        new Page<>(pageNum,pageSize);
//        LambdaQueryWrapper<Orderings> wrapper = Wrappers.<Orderings>lambdaQuery().eq(Orderings::getUid, uid);
        // 如果在前端展示数据为NULL 也会渲染到页面
        LambdaQueryWrapper<History> wrapper = Wrappers.<History>lambdaQuery().eq(History::getUid,uid);
        if (StrUtil.isNotBlank(search)){
            wrapper.like(History::getOpenPw,search);
        }
        Page<History> historyPage = historyMapper.selectPage(new Page<>(pageNum, pageSize),wrapper);
        return Result.success(historyPage);
    }

}
