package com.imooc.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.imooc.pojo.User;
import com.imooc.service.UserService;



@RestController
@RequestMapping("/user")
public class UserController {
    
	@Autowired
	private UserService userService;
	
	@RequestMapping("/aa")
	public ModelAndView aa() {
		ModelAndView model = new ModelAndView();
		model.setViewName("/user");
	}
	
	@RequestMapping("/save")
	public void save(User user) {
		int i = userService.save(user); 
	}
	
}
