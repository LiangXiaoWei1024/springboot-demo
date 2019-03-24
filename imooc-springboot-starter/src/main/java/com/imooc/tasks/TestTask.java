package com.imooc.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestTask {
	
	//获取当前时间
	private static final SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss");
	
	//指定多少毫秒执行一次
	//@Scheduled(fixedRate = 3000)
	//cron表达式
	//@Scheduled(cron = "1/1 * * * * * ")
	public void startTask(){
		System.out.println("当前时间:"+date.format(new Date()));
	}
}
