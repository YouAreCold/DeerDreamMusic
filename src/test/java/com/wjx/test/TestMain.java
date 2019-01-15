package com.wjx.test;

import java.util.Date;

import com.xky.roll.music_api.quartz.main;
/**
 * 
 * code is far away from bug with the animal protecting
 * ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 * 　　┃　　　┃神兽保佑
 * 　　┃　　　┃代码无BUG！
 * 　　┃　　　┗━━━┓
 * 　　┃　　　　　　　┣┓
 * 　　┃　　　　　　　┏┛
 * 　　┗┓┓┏━┳┓┏┛
 * 　　　┃┫┫　┃┫┫
 * 　　　┗┻┛　┗┻┛
 *
 * @Description : 测试主程序
 * ---------------------------------
 * @Author : Kris.wjx
 * @Date :
 */
public class TestMain {
	public static void main(String[] args) {
		Student studentWjx = new Student("19","wjx");
		System.out.println(studentWjx);
		Student studentMMM = new Student("www");
		System.out.println(studentMMM);
		
		System.out.println("学校:"+Student.school);
		
		while(true){
			System.out.println("死循环时间:"+new Date());
		}
			
	}
}
