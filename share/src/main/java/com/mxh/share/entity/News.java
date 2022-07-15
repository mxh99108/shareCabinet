package com.mxh.share.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.lang.reflect.Parameter;
import java.util.Date;

/**
 * author:erhui
 * version:1.0
 **/
@TableName("tb_news")
@Data   //自动生成get,set
public class News {
    // 自增
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String title;
    private String content;
    private String author;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss", timezone = "GMT + 8")
    private Date time;
}
