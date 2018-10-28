package com.imooc.controller;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.imooc.tasks.Run;
import com.imooc.utils.WeatherReportByCity;


@RestController
@RequestMapping("sys")
public class SysUserController {
 
	@Autowired
	private StringRedisTemplate strRedis;
	@Autowired
	private Run run;
	
	@RequestMapping("/index")
	public ModelAndView aa()  {
		//String url = "http://v.juhe.cn/sms/send";
		 //Map params = new HashMap();//请求参数
        // params.put("mobile","13530263029");//接收短信的手机号码
        // params.put("tpl_id","106560");//短信模板ID，请参考个人中心短信模板设置
        // params.put("tpl_value","#code#=520520");//变量名和变量值对。如果你的变量名或者变量值中带有#&=中的任意一个特殊符号，请先分别进行urlencode编码后再传递，<a href="http://www.juhe.cn/news/index/id/50" target="_blank">详细说明></a>
        // params.put("key","063baa07e7e40c49494ebbc38045d5a7");//应用APPKEY(应用详细页查询)
         //params.put("dtype","json");//返回数据的格式,xml或json，默认json
       //  WeatherReportByCity.sendGETRequest(url,params,"utf8");
        ModelAndView model = new ModelAndView();
        model.setViewName("/landing");
		return model;
		
		//return WeatherReportByCity.excute("深圳");
	}
	@RequestMapping("/aa")
	public String aaa()  {
		return WeatherReportByCity.excute("深圳");
	}
	
	@RequestMapping("/testRedis")
	public String testRedis()  {
		strRedis.opsForValue().set("hh", WeatherReportByCity.excute("深圳"));	
		return strRedis.opsForValue().get("hh");
	}
	
}
