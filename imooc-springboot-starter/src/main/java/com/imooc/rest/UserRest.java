package com.imooc.rest;

import com.imooc.pojo.User;

public class UserRest extends ResponseResult  {
	 User user;
	 public UserRest(ResultCode resultCode,User user) {
		super(resultCode);
		this.user = user;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
   

    
}
