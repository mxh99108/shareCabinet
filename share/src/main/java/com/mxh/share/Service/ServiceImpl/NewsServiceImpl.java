package com.mxh.share.Service.ServiceImpl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mxh.share.Service.NewsService;
import com.mxh.share.Service.UserService;
import com.mxh.share.common.Result;
import com.mxh.share.entity.News;
import com.mxh.share.entity.User;
import com.mxh.share.mapper.NewsMapper;
import com.mxh.share.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * author:erhui
 * version:1.0
 **/
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsMapper newsMapper;

    @Override
    public Result<?> save(News news) {
        news.setTime(new Date());
        newsMapper.insert(news);
        return Result.success();
    }

    @Override
    public Result update(News news) {
        newsMapper.updateById(news);
        return Result.success();
    }

    @Override
    public Result delete(Long id) {
        newsMapper.deleteById(id);
        return Result.success();
    }

}
