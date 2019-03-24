package com.imooc.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Run {
	
	//获取当前时间
	private static final SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss");
	
	//指定多少毫秒执行一次
	//@Scheduled(fixedRate = 3000)
	//cron表达式
	@Async
	public static void startTask1() throws InterruptedException{
		Thread.sleep(1000);
		System.out.println("当前时间1:"+date.format(new Date()));
	}
	
	@Async
	public static void startTask2() throws InterruptedException{
		Thread.sleep(700);
		System.out.println("当前时间2:"+date.format(new Date()));
	}
	
	@Async
	public static void startTask3() throws InterruptedException{
		Thread.sleep(200);
		System.out.println("当前时间3:"+date.format(new Date()));
	}
}
