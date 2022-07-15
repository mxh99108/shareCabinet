package com.mxh.share.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mxh.share.common.Result;
import com.mxh.share.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * author:erhui
 * version:1.0
 **/

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
