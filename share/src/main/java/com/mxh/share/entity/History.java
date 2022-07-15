package com.mxh.share.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * author:erhui
 * version:1.0
 **/

@TableName("tb_orderings")
@Data   //自动生成get,set
public class History {
    // 自增
    @TableId(value = "oid",type = IdType.AUTO)
    private Integer oid;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss", timezone = "GMT + 8")
    private Date time;
    private Integer cabId;
    private Integer boxId;
    private Double cost;
    private Integer openPw;
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Integer uid;
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Integer finished;

}
