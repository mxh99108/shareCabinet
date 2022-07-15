package com.mxh.share.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mxh.share.common.Result;
import com.mxh.share.entity.Cab;
import com.mxh.share.entity.Orderings;
import com.mxh.share.entity.Price;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

/**
 * author:erhui
 * version:1.0
 **/

@Mapper
public interface OrderingsMapper extends BaseMapper<Orderings> {

    Integer calTime(Integer oid);

}
