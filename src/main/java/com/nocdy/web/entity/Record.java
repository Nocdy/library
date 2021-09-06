package com.nocdy.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;


/**
 * @author Nocdy
 * @date 2021年 09月06日 11:25:30
 */
@Repository
@Data
@TableName(value = "record")
public class Record implements Serializable {

    @TableId(value = "record_id",type = IdType.AUTO)
    Integer recordId;

    @TableField(value = "borrow_time",jdbcType = JdbcType.DATE)
    Date borrowTime;

    @TableField(value = "return_time",jdbcType = JdbcType.DATE)
    Date returnTime;

    @TableField(value = "user",jdbcType = JdbcType.INTEGER)
    Integer user;

    @TableField(value = "book",jdbcType = JdbcType.INTEGER)
    Integer book;

    @TableField(value="status",jdbcType = JdbcType.BOOLEAN)
    Boolean status;

    @TableField(value="out_of_time",jdbcType = JdbcType.BOOLEAN)
    Boolean outOfTime;


}
