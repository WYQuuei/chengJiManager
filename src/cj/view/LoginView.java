package cj.view;

import cj.controller.ChengJiController;
import cj.encapsulation.*;
import cj.service.*;
import cj.tool.Safe;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.logging.SimpleFormatter;

public class LoginView implements operation{
	//设定登录系统的状态码
	private final static String zhuangtai1="初始状态,准备选择直接登陆(输入username)还是注册新账号(输入1)";
	private final static String zhuangtai2="注册状态,准备输入username";
	private final static String zhuangtai3="注册状态,准备输入password";
	private final static String zhuangtai4="注册状态,准备加密录入到数据库中";
	private final static String zhuangtai5="登录状态,准备输入password";
	private final static String zhuangtai6="登录状态,准备到数据库中核验";
    private final static String zhuangtai7="登录成功状态";
	private final static String zhuangtai8="退出系统";
	String flag = zhuangtai1;
	//用于注册的对象
	Login register = new Login();
	//用于登录的对象
	Login login = new Login();
	
    public String operation(){
        Scanner scan = new Scanner(System.in);
        System.out.println("欢迎进入 【成绩单管理系统2.0】 (＞﹏＜)");
        System.out.println("请输入您的'帐户名'(不支持中文)来完成登录,如果没有账户请输入'1'注册一个新用户,退出系统请输入'0' ");
        while (true) {
        	if(zhuangtai1.equals(flag)) {
        		String username = scan.next();
        		if("0".equals(username)) {
        			flag = zhuangtai8;
        			return flag;
        		}else if("1".equals(username))
        			flag=zhuangtai2;
        		else {
        			login.setUsername(username);
        			flag=zhuangtai5;
        		}
        	}
            if(zhuangtai2.equals(flag)) {
            	System.out.println("请输入你要注册的用户名(不支持中文): 退出系统请输入'0' ");
                String newUsername = scan.next();
                //退出系统
                if("0".equals(newUsername)) {
                	flag = zhuangtai8;
                	return flag;
                }
                //检查用户名是否输入规范
                if(checkUser(newUsername)) {
                	register.setUsername(newUsername);
                	flag=zhuangtai3;
                }else
                	 continue;
            	}
            if(zhuangtai3.equals(flag)) {
            	System.out.println("请输入密码:");
            	String newPwd = scan.next();
            	//检查第一次输入的密码是否正确.
            	if(!checkPwd(newPwd))
            		continue;
                System.out.println("请再次输入的密码:");
                String confirmPwd = scan.next();
                if (newPwd.equals(confirmPwd)) {
                	register.setPassword(newPwd);
                	flag=zhuangtai4;
                }else
                    System.out.println("两次输入的密码不一致,请核对");
            }
            if(zhuangtai4.equals(flag)) {
            	//开始注册
                boolean f = loginService.register(register);
                if(f){
                    System.out.println("注册成功！~(^ω^)~ 已自动帮你登录.");
                    flag = zhuangtai7;
                    return flag;//返回到控制模块ChengJiController的flag
                }else{
                    System.out.println("注册失败!~(╯︿╰)~ 再试一次吧~~~ 退出系统请输入'0'");
                    flag=zhuangtai2;
                    continue;
                }
            }
            if(zhuangtai5.equals(flag)) {
            	System.out.println("请输入密码：");
            	String password = scan.next();
            	login.setPassword(password);
            	flag=zhuangtai6;
            }
            if(zhuangtai6.equals(flag)) {
            	boolean jieGuo = loginService.login(login);
                if (jieGuo) {
                	System.out.println("登录成功!~(^ω^)~");
                	flag = zhuangtai7;
                	return flag;
                } else {
                	flag=zhuangtai1;
                    System.out.println("登录失败！~(╯︿╰)~");
                    System.out.println("请重新输入账户名,退出系统请输入'0',注册新账号请输入'1' ");
                }
            }
        }
    }
	//检查用户名是否规范
    public boolean checkUser(String username) {
    	if(username==null||username.isEmpty()) {
    		System.out.println("输入不能为空!");
    		return false;
    	}else if(username.length()>20){
    		System.out.println("用户名的长度不可以超过20个字符!");
    		return false;
    	}else if(username.length()<2){
    		System.out.println("用户名的字符长度不可以低于2个字符");
    		return false;
    	//去数据库搜索,看用户名是否已经被占用
    	}else if(loginService.queryUserByName(Safe.jiaMi(username))){
    		System.out.println("这个用户名已经被占用了,请换个名字!");
    		return false;
    	}
    	return true;
    }
    
    //检查密码是否规范
    public boolean checkPwd(String password) {
    	if(password==null||password.isEmpty()) {
    		System.out.println("输入不能为空!");
    		return false;
    	}else if(password.length()>20){
    		System.out.println("密码的长度不可以超过20个字符!");
    		return false;
    	}else if(password.length()<2){
    		System.out.println("密码的字符长度不可以低于2个字符");
    		return false;
    	}
    	
    	return true;
    }
}