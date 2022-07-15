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
public class Orderings {
    // 自增
    @TableId(value = "oid",type = IdType.AUTO)
    private Integer oid;
    private Integer cabId;
    private Integer boxId;
    private Double cost;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss", timezone = "GMT + 8")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Date time;
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Integer openPw;
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Integer uid;
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Integer finished;
}
