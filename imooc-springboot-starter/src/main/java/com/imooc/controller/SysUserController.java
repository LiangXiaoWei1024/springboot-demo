package com.imooc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.pojo.IMoocJSONResult;
import com.imooc.pojo.SysUser;
import com.imooc.service.UserService;


@RestController
@RequestMapping("sys")
public class SysUserController {
 
	@Autowired
	private UserService userService;
	
	@RequestMapping("/getSysUser")
	public SysUser getSysUser(){
		SysUser queryUserByIdCustom = userService.queryUserByIdCustom("1001");
		return queryUserByIdCustom;
	}
	
	@RequestMapping("/aa")
	public String aa(){
		
		return "hello老何!";
	}
	
	@RequestMapping("/insert")
	public IMoocJSONResult z(){
		try {
		SysUser sysUser = new SysUser();
		sysUser.setId("1002");
		sysUser.setUsername("张三");
		sysUser.setPassword("123");
		sysUser.setNickname("可爱的倩倩");
		userService.saveUser(sysUser);
		} catch (Exception e) {
			e.printStackTrace();
			return IMoocJSONResult.errorMsg(e.getMessage());
		}
		return IMoocJSONResult.ok("插入成功！");
	}
	
	@RequestMapping("/delete")
	public IMoocJSONResult d(){
		try {
		userService.deleteUser("1001");
		} catch (Exception e) {
			e.printStackTrace();
			return IMoocJSONResult.errorMsg(e.getMessage());
		}
		return IMoocJSONResult.ok("删除成功！");
	}
	
	@RequestMapping("/update")
	public IMoocJSONResult u(){
		try {
			SysUser user = userService.queryUserByIdCustom("1002");
			user.setUsername("可爱人倩倩");
			userService.updateUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return IMoocJSONResult.errorMsg(e.getMessage());
		}
		return IMoocJSONResult.ok("修改成功！");
	}
}
