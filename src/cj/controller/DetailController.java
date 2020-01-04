package cj.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import cj.encapsulation.ChengJi;
import cj.service.ChengJiService;
import cj.tool.FileTool;
import cj.view.*;
import cj.view.chengJiView.AddView;
import cj.view.chengJiView.DeleteView;
import cj.view.chengJiView.ModifyView;
import cj.view.chengJiView.QueryMainView;
import cj.view.detailView.AddDetailView;
import cj.view.detailView.DeleteDetailView;
import cj.view.detailView.ModifyDetailView;
import cj.view.detailView.QueryDetailView1;
import cj.view.detailView.QueryDetailView2;

public class DetailController {

	//设定系统的状态码
//	private final static String zhuangtai1="查询成绩单详情主界面";
//	private final static String zhuangtai2="退出详情界面";
//	private final static String zhuangtai4="新建成绩单详情";
//	private final static String zhuangtai5="删除成绩单详情";
//	private final static String zhuangtai6="修改成绩单详情";
//	private final static String zhuangtai8="导出详情到文件";
    Scanner scan = new Scanner(System.in);
    String flag = "";
    operation o = null;
    //传递处理文件信息的工具类
    FileTool ft = null;
    //传递[成绩单]信息.新增,修改[成绩单详情]时使用
    ChengJi cj = null;
    //构造函数传入状态码flag
    public  DetailController(String flag) {
    	this.flag = flag;
    }
    
    public String controller() throws InputMismatchException{
        while (true) {
        	
        	//成功进入查询成绩单详情[主界面],准备接下来的操作
        	if("查询成绩单详情主界面".equals(flag)) {
        		//进入QueryMain选择界面
        		o = new QueryMainView();
        		flag = o.operation();
        		if("登录成功状态".equals(flag))
        			return "登录成功状态";
        	}
        	
        	//根据[id]查询[成绩单详情]
        	if("根据id查询成绩单详情".equals(flag)) {
        		o = new QueryDetailView1();
        		flag = o.operation();
        		//传参
        		if("导出详情到文件".equals(flag))
        			ft=((QueryDetailView1)o).returnParam();
        		else if("新建成绩单详情".equals(flag))
        			cj=((QueryDetailView1)o).returnChengJi();
        		else if("修改成绩单详情".equals(flag))
        			cj=((QueryDetailView1)o).returnChengJi();
        	}
        	
        	//根据[科目]查询[成绩单详情]
        	if("根据科目查询成绩单详情".equals(flag)) {
        		o = new QueryDetailView2();
        		flag = o.operation();
        		//传参
        		if("导出详情到文件".equals(flag))
        			ft=((QueryDetailView2)o).returnParam();
        	}
        	
        	//进入[新建成绩单详情]模块
        	if("新建成绩单详情".equals(flag)) {
        		o = new AddDetailView(cj);
        		flag = o.operation();
        	}
        	
        	//进入[删除成绩单详情]模块
        	if("删除成绩单详情".equals(flag)) {
        		o = new DeleteDetailView();
        		flag = o.operation();
        	}
        	
        	//进入[修改成绩单详情]模块
        	if("修改成绩单详情".equals(flag)) {
        		o = new ModifyDetailView(cj);
        		flag = o.operation();
        	}
        	
        	//进入[导出详情到文件]模块
        	if("导出详情到文件".equals(flag)) {
        		o = new FileView(ft);
        		flag = o.operation();
        	}

        	//"登录成功状态"
        	if("登录成功状态".equals(flag))
        		flag = "查询成绩单详情主界面";
        }
    }
}
