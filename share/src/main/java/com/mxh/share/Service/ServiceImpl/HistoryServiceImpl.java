package com.mxh.share.Service.ServiceImpl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mxh.share.Service.HistoryService;
import com.mxh.share.Service.UserService;
import com.mxh.share.common.Result;
import com.mxh.share.entity.History;
import com.mxh.share.entity.User;
import com.mxh.share.mapper.HistoryMapper;
import com.mxh.share.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author:erhui
 * version:1.0
 **/
@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    HistoryMapper historyMapper;

    @Override
    public Result delete(Long id) {
        historyMapper.deleteById(id);
        return Result.success();
    }

}
