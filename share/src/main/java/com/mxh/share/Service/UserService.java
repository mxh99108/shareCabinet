package com.mxh.share.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mxh.share.common.Result;
import com.mxh.share.entity.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * author:erhui
 * version:1.0
 **/
public interface UserService {

    Result save(@RequestBody User user);

    Result update(User user);

    Result delete(Long id);

    Result login(User user);

    Result register(User user);

    Result findAdmin();

}
