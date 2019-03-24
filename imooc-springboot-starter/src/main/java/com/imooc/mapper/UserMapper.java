package com.imooc.mapper;

import org.apache.ibatis.annotations.Param;

import com.imooc.pojo.User;
import com.imooc.utils.MyMapper;

public interface UserMapper extends MyMapper<User> {

	User getUserByPhone(@Param("phone")String phone);
}