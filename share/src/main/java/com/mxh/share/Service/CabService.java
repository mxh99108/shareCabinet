package com.mxh.share.Service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.mxh.share.common.Result;
import com.mxh.share.entity.Cab;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * author:erhui
 * version:1.0
 **/
public interface CabService{

    Result save(@RequestBody Cab cab);

    Result update(Cab cab);

    Result delete(Long id);

    Result select(Integer currentPage,Integer pageSize,Integer cabId);

    Integer selectAll();

}
