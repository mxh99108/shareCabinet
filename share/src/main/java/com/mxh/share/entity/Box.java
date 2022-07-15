package com.mxh.share.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.beans.IntrospectionException;
import java.util.Date;

/**
 * author:erhui
 * version:1.0
 **/

@TableName("tb_box")
@Data   //自动生成get,set
public class Box {
    // 自增
    @TableId(value = "id",type = IdType.AUTO)
    private Integer boxId;
    private Integer cabId;
    private Integer isUsed;
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Integer uid;
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm:ss", timezone = "GMT + 8")
    @TableField(updateStrategy = FieldStrategy.IGNORED)
    private Date time;
//    @TableField(updateStrategy = FieldStrategy.IGNORED)
//    private Integer oid;
//    @TableField(updateStrategy = FieldStrategy.IGNORED)
//    private Integer finished;
}
