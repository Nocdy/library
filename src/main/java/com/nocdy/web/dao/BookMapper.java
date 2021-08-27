package com.nocdy.web.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nocdy.web.entity.Book;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
}
