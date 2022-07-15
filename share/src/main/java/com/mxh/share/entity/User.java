package com.mxh.share.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * author:erhui
 * version:1.0
 **/

@TableName("tb_user")
@Data   //自动生成get,set
public class User {
    // 自增
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private Integer rid;
    private String name;
    private String username;
    private String password;
    private String phone;
    private String email;
}
