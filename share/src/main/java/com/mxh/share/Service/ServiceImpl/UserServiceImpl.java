package com.mxh.share.Service.ServiceImpl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.mxh.share.Service.UserService;
import com.mxh.share.common.Result;
import com.mxh.share.entity.User;
import com.mxh.share.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * author:erhui
 * version:1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Result<?> save(User user) {
        if(user.getPassword()==null){
            user.setPassword("123456");
        }
        user.setRid(3);
        userMapper.insert(user);
        return Result.success();
    }

    @Override
    public Result update(User user) {
        userMapper.updateById(user);
        return Result.success();
    }

    @Override
    public Result delete(Long id) {
        userMapper.deleteById(id);
        return Result.success();
    }

    @Override
    public Result login(User user) {
        User result = userMapper.selectOne(Wrappers.<User>lambdaQuery()
                .eq(User::getUsername, user.getUsername())
                .eq(User::getPassword, user.getPassword()));
        if (result == null){
            return Result.error("-1","用户名或密码错误");
        }
        return Result.success(result);
    }

    @Override
    public Result register(User user) {
        User result = userMapper.selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if (result == null){
            if (user.getPassword() == null){
                user.setPassword("123456");
            }
            user.setRid(3);
            userMapper.insert(user);
            return Result.success("注册成功！");
        }
        return Result.error("-1","该账号已注册！");
    }

    @Override
    public Result findAdmin() {
        List<Map<String, Object>> maps = userMapper.selectMaps(Wrappers.<User>lambdaQuery().lt(User::getRid, 3));
        return Result.success(maps);

    }

//    @Override
//    public Result findUser() {
//        List<Map<String, Object>> maps = userMapper.selectMaps(Wrappers.<User>lambdaQuery().eq(User::getRid, 3));
//        return Result.success(maps);
//    }

}
