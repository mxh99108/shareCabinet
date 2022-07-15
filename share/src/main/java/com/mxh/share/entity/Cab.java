package com.mxh.share.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.print.attribute.standard.PrinterURI;

/**
 * author:erhui
 * version:1.0
 **/
@TableName("tb_cabinet")
@Data   //自动生成get,set
public class Cab {
    // 自增
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer sum;
    private Integer onUsing;
    private Integer notUsing;
//    private Integer ownerId;

}
