package cj.controller;

import java.util.InputMismatchException;

import cj.encapsulation.ChengJi;
import cj.tool.FileTool;
import cj.view.FileView;
import cj.view.LoginView;
import cj.view.MainView;
import cj.view.operation;
import cj.view.chengJiView.AddView;
import cj.view.chengJiView.DeleteView;
import cj.view.chengJiView.ModifyView;
import cj.view.chengJiView.QueryMainView;
import cj.view.detailView.AddDetailView;

public class ChengJiController {
	
	//设定系统的状态码
	private final static String zhuangtai1="初始状态";
//	private final static String zhuangtai2="退出系统";
//	private final static String zhuangtai3="登录成功状态";
//	private final static String zhuangtai4="新建成绩单";
//	private final static String zhuangtai5="删除成绩单";
//	private final static String zhuangtai6="修改成绩单";
//	private final static String zhuangtai7="查询成绩单详情主界面";
//	private final static String zhuangtai8="导出到文件";
	String zhuflag = zhuangtai1;
    operation o = null;
    //传递[成绩单]信息.新增,修改[成绩单详情]时使用
    ChengJi cj = null;
    //传递处理文件信息的工具类
    FileTool ft = null;
    public void controller() throws InputMismatchException{
        while (true) {
        	
    		//进入login登录界面
        	if("初始状态".equals(zhuflag)) {
        		o = new LoginView();
        		zhuflag = o.operation();
        	}
        	
        	//退出当前账号,重新登录
        	if("切换账号".equals(zhuflag)) {
        		System.out.println("成功退出当前账号!");
        		zhuflag = "初始状态";
        	}
        	
    		//退出系统
        	if("退出系统".equals(zhuflag)) {
        		System.out.println("再见!欢迎下次再来!bye~~~~~~");
        		return;
        	}
        	
        	//登录成功,准备接下来的操作
        	if("登录成功状态".equals(zhuflag)) {
        		try {
        			//让系统休眠1.5秒钟,这样前面的输出语句就能让用看见了,这段删了也行.
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        		//进入Main选择界面
        		o = new MainView();
        		zhuflag = o.operation();
        		//传参
        		if("导出到文件".equals(zhuflag))
        			ft=((MainView)o).returnParam();
        	}
        	
        	//进入[新建成绩单]模块
        	if("新建成绩单".equals(zhuflag)) {
        		o = new AddView();
        		zhuflag = o.operation();
        	}
        	
        	//进入[新建成绩单详情]模块
        	if("新建成绩单详情".equals(zhuflag)) {
        		o = new AddDetailView(cj);
        		zhuflag = o.operation();
        	}
        	
        	//进入[删除成绩单]模块
        	if("删除成绩单".equals(zhuflag)) {
        		o = new DeleteView();
        		zhuflag = o.operation();
        	}
        	
        	//进入[修改成绩单]模块
        	if("修改成绩单".equals(zhuflag)) {
        		o = new ModifyView();
        		zhuflag = o.operation();
        	}
        	
        	//这是[成绩单详情]模块的入口
        	//开始进入[成绩单详情]的控制层,这个方法是连接[成绩单]和[成绩单详情]的唯一方法.
        	if("查询成绩单详情主界面".equals(zhuflag)) {
        		DetailController details=new DetailController(zhuflag);
        		zhuflag = details.controller();
        	}
        	
        	//进入[导出到文件]模块
        	if("导出到文件".equals(zhuflag)) {
        		o = new FileView(ft);
        		zhuflag = o.operation();
        	}
        }
    }
}
