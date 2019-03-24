package com.imooc.controller;




import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.imooc.pojo.User;
import com.imooc.rest.UserCode;
import com.imooc.rest.UserRest;
import com.imooc.service.UserService;
import com.imooc.service.impl.WebSocketServer;
import com.imooc.utils.Result;
import com.imooc.utils.StatusCode;
import com.imooc.utils.StringUtils;
import com.imooc.utils.WeatherReportByCity;




@RestController
@RequestMapping("/user")
public class UserController {
    
	public static final Map<String, HttpSession> USER_SESSION=new HashMap<String, HttpSession>(); 
	private static  String id = "";
	private static final Logger log = LoggerFactory.getLogger(UserController.class);


	@Autowired
	JavaMailSender jms;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private WebSocketServer webSocketServer;
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@RequestMapping("/")
	public ModelAndView aa() {
		webSocketServer.onClose();
		ModelAndView model = new ModelAndView();
		model.setViewName("/user");
		return model;
	}
	
	@RequestMapping("/login")
	public Result login(User user,HttpServletRequest request,HttpServletResponse response) {
		Result result = Result.getError();
		if(StringUtils.isNull(user.getName()) || StringUtils.isNull(user.getPassword())){
			return Result.getError(StatusCode.PARAM_MISSING);
		}
		try {
			
			User u = userService.login(user);
			if( u == null) {
				return Result.getError(StatusCode.USER_PASSWORD_ERROR);
			}
			/*Cookie cookie = new Cookie("userName", u.getName());
			cookie.setPath("/");
			cookie.setMaxAge(1800);
			
			 
			Cookie cookie1 = new Cookie("userPassword", u.getPassword());
			cookie1.setPath("/");
			cookie1.setMaxAge(1800);
			response.addCookie(cookie1);
			response.addCookie(cookie);*/
			HttpSession session = request.getSession();
			session.setAttribute("user", u);
			//同时只能一个用户登录
			//id = session.getId().toString();
			//System.err.println(session.getId().toString());
			//.setAttribute("user", u);
			//USER_SESSION.put(id, session);
			///HttpSession session2 = USER_SESSION.get("E3CBC462BF9B0983C05AFEF8BAD122DF");
			//session2.removeAttribute("user");
			
			result = Result.getSuccess(u);
		} catch (Exception e) {
			log.error(e.getMessage());
			result = Result.getError(StatusCode.SYSTEM_ERROR);
		}
		return result;
	}
	
	
	@RequestMapping("/loginOut")
	public ModelAndView loginOut(HttpServletRequest request) {
		request.getSession().removeAttribute("user");
		ModelAndView model = new ModelAndView();
		model.setViewName("/login");
		return model;
	}
	
	@RequestMapping("/selectUserList")
	public Result selectUserList() {
		Result result = Result.getError();
		List<User> users = userService.selectUserList();
		result = Result.getSuccess(users);
		return result;
	}
	@RequestMapping("/aaa")
	public Result aaa(HttpServletRequest request) {
		return Result.getSuccess(request.getSession().getAttribute("user"));
	}
	
	
	@RequestMapping("/save")
	public void save(User user) {
		int i = userService.save(user); 
	}
	
	
	@RequestMapping("/loginAndVerification")
	public boolean loginAndVerification(User user,HttpServletRequest request) throws Exception {
		request.getSession().setAttribute(user.getPhone(),"742626");
		String url = "http://v.juhe.cn/sms/send";
		Map<String, String> params = new HashMap<String, String>();//请求参数
        params.put("mobile",user.getPhone());//接收短信的手机号码
        params.put("tpl_id","106560");//短信模板ID，请参考个人中心短信模板设置
        params.put("tpl_value","#code#=742626");//变量名和变量值对。如果你的变量名或者变量值中带有#&=中的任意一个特殊符号，请先分别进行urlencode编码后再传递，<a href="http://www.juhe.cn/news/index/id/50" target="_blank">详细说明></a>
        params.put("key","063baa07e7e40c49494ebbc38045d5a7");//应用APPKEY(应用详细页查询)
        params.put("dtype","json");//返回数据的格式,xml或json，默认json
        boolean sendGETRequest = WeatherReportByCity.sendGETRequest(url,params,"utf8");
		return sendGETRequest; 
	}
	
	@RequestMapping("/sendEM")
	public String sendEM() throws Exception {
	    long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
		try {
			
			//建立邮件消息
			SimpleMailMessage mainMessage = new SimpleMailMessage();
			//发送者
			mainMessage.setFrom("xiaowei8192@163.com");
			//接收者
			mainMessage.setTo("Selina_sq@163.com");
			//发送的标题
			mainMessage.setSubject("嗨喽美女");
			//发送的内容
			mainMessage.setText("hello world");
			for (int i = 0; i < 10; i++) {
				jms.send(mainMessage);
			}
			return "1";
		} catch (Exception e) {
			return "0";		
		}
	}
	
	
	@RequestMapping("/sendMsg")
	public void sendMessage(String  sendUserName, String sendeeUserId,String message,String time) throws IOException {
		message = sendUserName+"　　　"+message+"　　"+time;
		WebSocketServer.sendInfo(sendeeUserId,message);
	}
	
	
	
	
	
	
	 @RequestMapping(value = "/hello")
	    public void sendMQ() throws Exception, IOException{
	      /*  System.out.println("准备发送消息。。。");
	        sender.send();
	        return "success";*/
		 
			// 1. 创建HttpSolrServer，传入接口地址
			//HttpSolrServer httpSolrServer = new HttpSolrServer("http://47.106.229.94:8081/solr/");
			
			
			

//添加和删除
			// 2. 创建SolrInputDocument对象，调用add方法构建文档内容
//			SolrInputDocument document = new SolrInputDocument();
//			document.addField("id", "45454588888tttttrrrrr");
//			document.addField("name", "你们好，我是来自中国湖南耒阳的耒阳人");
			// 3. 通过HttpSolrServer调用add方法，把SolrInputDocument对象添加到索引库中
			//httpSolrServer.add(document);
			// 4. 提交内容
			
			
			
			
			//查询
			/*SolrQuery query = new SolrQuery();
			query.setQuery("name:大中国");
			QueryResponse response = httpSolrServer.query(query);
			SolrDocumentList results = response.getResults();
			
			// 6. 输出总条数(results.getNumFound)
			System.out.println(results.getNumFound());
			
			//size是当前查询到的集合总记录数
			System.out.println(results.size());

			for (SolrDocument doc : results) {
				System.out.println(doc.get("id") + "-->" + doc.get("name"));
			}*/
			
		//	httpSolrServer.commit();

	    }
	 
		
		@RequestMapping("/sendSMS")
		public UserRest sendSMS(String phone)  {
			try {
				String url = "http://v.juhe.cn/sms/send";
				 Map<String,String> params = new HashMap<>();//请求参数
		         params.put("mobile",phone);//接收短信的手机号码
		         params.put("tpl_id","106560");//短信模板ID，请参考个人中心短信模板设置
		         params.put("tpl_value","#code#=520520");//变量名和变量值对。如果你的变量名或者变量值中带有#&=中的任意一个特殊符号，请先分别进行urlencode编码后再传递，<a href="http://www.juhe.cn/news/index/id/50" target="_blank">详细说明></a>
		         params.put("key","063baa07e7e40c49494ebbc38045d5a7");//应用APPKEY(应用详细页查询)
		         params.put("dtype","json");//返回数据的格式,xml或json，默认json
		         //WeatherReportByCity.sendGETRequest(url,params,"utf8");
		         
		         redisTemplate.opsForValue().set(phone, "520520",30,TimeUnit.MINUTES);

		         
		         return new UserRest(UserCode.USER_SUCCESS, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			 return new UserRest(UserCode.USER_FULL, null);
		}
		
		@RequestMapping("/loginORsave")
		public UserRest loginORsave(String phone,String code)  {
				UserRest userRest = userService.loginORsave(phone,code);
		         return userRest;
		}

}
