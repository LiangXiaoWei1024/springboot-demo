package com.imooc.exception;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.imooc.interceptor.SessionInterceptor;
import com.imooc.interceptor.TokenInterceptor;

@Configuration
@ConfigurationProperties("interceotor")
public class WebConfig implements WebMvcConfigurer {
	

	/*@Value("${upload.dir}")
	private String dirPath;*/
	
	@Bean
	public TokenInterceptor tokenInterceptor() {
	    return new TokenInterceptor();
	}
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
	    
        //注册拦截器
    	registry.addInterceptor(new SessionInterceptor()).addPathPatterns("/**")
    													 .excludePathPatterns("/sys/login")
    													 .excludePathPatterns("/static/**")
    													 .excludePathPatterns("/user/login");
    	registry.addInterceptor(tokenInterceptor()).addPathPatterns("/**")
													 .excludePathPatterns("/sys/login")
													 .excludePathPatterns("/static/**")
													 .excludePathPatterns("/user/sendSMS")
													 .excludePathPatterns("/user/loginORsave")
													 .excludePathPatterns("/user/login");
    }
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**").addResourceLocations("file:C:/aaa");
	}
}