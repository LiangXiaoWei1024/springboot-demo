package com.imooc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.imooc.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * session拦截器
 */
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

       /* User sysUser = (User) request.getSession().getAttribute("user");
        if (sysUser == null) {
            String requestWithHeader = request.getHeader("x-requested-with");
            //处理ajax请求超时的情况
            if (requestWithHeader != null && requestWithHeader.equalsIgnoreCase("xmlhttprequest")) {
                response.setHeader("sessionstatus", "timeout");
                return false;
            }
            response.sendRedirect("/sys/login");
            return false;
        }*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
    	/*String requestURI = request.getRequestURI();
    		if(requestURI != null){
    		int strStartIndex = requestURI.indexOf("//");
    		if(strStartIndex != -1){
    			String substring = requestURI.substring(strStartIndex+1,requestURI.length());
    			request.getSession().setAttribute("servicePAth", substring);
    		}
    	}*/
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
