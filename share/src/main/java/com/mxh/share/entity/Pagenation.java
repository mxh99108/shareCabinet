package com.mxh.share.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * author:erhui
 * version:1.0
 **/

@Data   //自动生成get,set
public class Pagenation {
    private List<?> data;
    private Long total;
}
