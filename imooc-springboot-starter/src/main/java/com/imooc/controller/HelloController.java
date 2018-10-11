package com.imooc.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imooc.pojo.Resource;

@RestController
@RequestMapping("/hello")
public class HelloController {
 
	@Autowired
	private Resource res;
	
	@RequestMapping("/getHello")
	public Object hello(){
		return "hello springboot!~!";
		
	}
	
	@RequestMapping("/getRes")
	public Resource getRes(){
		Resource resource = new Resource();
		BeanUtils.copyProperties(res, resource);
		return resource;
	}
}
