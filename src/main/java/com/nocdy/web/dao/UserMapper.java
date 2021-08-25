package com.nocdy.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nocdy.web.entity.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper extends BaseMapper<User> {
}
