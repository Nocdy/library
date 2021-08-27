package com.nocdy.web.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;


/**
 * @author Nocdy
 */

@Repository
@Data
@TableName(value = "book")
public class Book {

    @TableId(value = "book_id",type = IdType.AUTO)
    Integer id;

    @TableField(value = "book_name",jdbcType = JdbcType.VARCHAR)
    String name;

    @TableField(value="book_type",jdbcType = JdbcType.VARCHAR)
    String type;

    @TableField(value = "book_info",jdbcType = JdbcType.VARCHAR)
    String information;

    @TableField(value = "book_count",jdbcType = JdbcType.INTEGER)
    Integer count;

}
