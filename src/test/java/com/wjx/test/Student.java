package com.wjx.test;
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
 * @Description : 学生类
 * ---------------------------------
 * @Author : Kris.wjx
 * @Date :
 */
public class Student {
	
	private String age;		// 年龄
	private String name;	// 姓名
	
	public static String school="海滨";
	
	public Student() {
		System.out.println("我是无惨");
	}
	
	
	public Student(String age, String name) {
		System.out.println("我是有参");
		
		this.age = age;
		this.name = name;
	}
	
	public Student(String name) {
		System.out.println("我是有参");
		this.name = name;
	}


	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
