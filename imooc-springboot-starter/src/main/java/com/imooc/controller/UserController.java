package com.imooc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.pojo.IMoocJSONResult;
import com.imooc.pojo.User;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@RequestMapping("/getUser")
	public IMoocJSONResult getUser(){
		User user = new User();
		user.setName("李四");
		user.setPassword("123");
		user.setAge(18);
		user.setBirthday(new Date());
		return new IMoocJSONResult().ok(user);
	}
	
}
