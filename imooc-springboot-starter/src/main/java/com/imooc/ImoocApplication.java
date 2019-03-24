package com.imooc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
//扫描mybatis mapper包路径
@MapperScan(basePackages="com.imooc.mapper")
//扫描 所需要的包，包含一些自用的工具类包 所在路径
//@ComponentScan(basePackages={"com.imooc","org.n3r.idworker"})
//开启定时任务
//@EnableScheduling
//开启异步任务
//@EnableAsync
public class ImoocApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ImoocApplication.class, args);
	}
}
