package com.nocdy.web.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

@Repository
@Data
@TableName("user")
public class User {

    @TableId(value = "user_id",type = IdType.AUTO)
    Integer id;

    @TableField(value = "user_name",jdbcType = JdbcType.VARCHAR)
    String name;

    @TableField(value = "user_password",jdbcType = JdbcType.VARCHAR)
    String password;

    @TableField(value = "user_identity",jdbcType = JdbcType.VARCHAR)
    String identity;

    @TableField(exist = false)
    String confirmPassword;
}
