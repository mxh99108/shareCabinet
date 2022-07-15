package com.mxh.share.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mxh.share.common.Result;
import com.mxh.share.entity.Cab;
import com.mxh.share.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * author:erhui
 * version:1.0
 **/

@Mapper
public interface CabMapper extends BaseMapper<Cab> {

    List<Map<String,Object>> selectCab(Integer currentPage,Integer pageSize);

    List<Map<String,Object>> selectCabWithId(Integer currentPage,Integer pageSize,Integer cab_id);

    List selectAll();
}
