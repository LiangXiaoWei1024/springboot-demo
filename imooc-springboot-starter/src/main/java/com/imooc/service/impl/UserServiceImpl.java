package com.imooc.service.impl;


import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import com.imooc.mapper.UserMapper;
import com.imooc.pojo.User;
import com.imooc.rest.UserCode;
import com.imooc.rest.UserRest;
import com.imooc.service.UserService;
import com.imooc.utils.MD5Util;

import net.sf.json.JSONObject;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	

	@Autowired
	private StringRedisTemplate redisTemplate;
	
	
	
	@Override
	public int save(User user) {
		user.setCarateTime(new Date());
		return userMapper.insertSelective(user);
	}



	@Override
	public User login(User user) {
		Example example = new Example(User.class);
		Criteria criteria = example.createCriteria();
		
		criteria.andEqualTo("name",user.getName());
		criteria.andEqualTo("password",user.getPassword());
		
		return userMapper.selectOneByExample(example);
	}



	@Override
	public List<User> selectUserList() {
		return userMapper.selectAll();
	}



	@Override
	public UserRest loginORsave(String phone, String code) {
		String redisCode = redisTemplate.opsForValue().get(phone);
		
		if(!code.equals(redisCode)) {
			return new UserRest(UserCode.USER_CODE_FULL, null);
		}
		redisTemplate.delete(phone);
		 User user = userMapper.getUserByPhone(phone);
		 if(user == null) {
			 user= new User();
			 user.setPhone(phone);
			 user.setName(phone);
			 user.setCarateTime(new Date());
			 userMapper.insert(user);
		 }
		 String token;
		try {
			 token = MD5Util.md5(phone, "--//**");
			 JSONObject json = JSONObject.fromObject(user);
			 redisTemplate.opsForValue().set(token, json.toString(),30,TimeUnit.DAYS);
			 user.setToken(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new UserRest(UserCode.USER_SUCCESS,user);
	}
 
}
