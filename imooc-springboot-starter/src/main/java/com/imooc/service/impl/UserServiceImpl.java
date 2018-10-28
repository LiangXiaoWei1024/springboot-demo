package com.imooc.service.impl;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.mapper.UserMapper;
import com.imooc.pojo.User;
import com.imooc.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	
	
	@Override
	public int save(User user) {
		user.setCarateTime(new Date());
		return userMapper.insertSelective(user);
	}
 
}
