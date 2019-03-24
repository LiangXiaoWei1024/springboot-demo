package com.imooc.service;


import java.util.List;

import com.imooc.pojo.User;
import com.imooc.rest.UserRest;

public interface UserService {

	int save(User user);

	User login(User user);

	List<User> selectUserList();

	UserRest loginORsave(String phone, String code);


}
