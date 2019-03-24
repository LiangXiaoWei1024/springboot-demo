package com.imooc.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.imooc.pojo.User;
import com.imooc.rest.Code;
import com.imooc.rest.ResponseResult;
import com.imooc.rest.ResultCode;

import net.sf.json.JSONObject;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * session拦截器
 */
public class TokenInterceptor implements HandlerInterceptor {
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    	System.out.println("进入token拦截。。。。。。。。。。");
    	String token = request.getHeader("token");
    	String objectStr =  redisTemplate.opsForValue().get(token);
    	JSONObject jsonObject=JSONObject.fromObject(objectStr);
    	User user=(User)JSONObject.toBean(jsonObject, User.class);
    	if(user != null) {
    		return true;
    	}else{
    		ResponseResult result = new ResponseResult(Code.USER_SOLR);
        	response.setCharacterEncoding("utf-8");
        	response.setContentType("application/json; charset=utf-8");
        	PrintWriter writer = response.getWriter();
        	Map<String, String> map = new HashMap<>();
        	map.put("code", "-200");
        	JSONObject json = JSONObject.fromObject(result);
        	writer.write(json.toString());
        	return false;
    	}
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    	
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
