package cj.view.chengJiView;

import cj.controller.ChengJiController;
import cj.encapsulation.ChengJi;
import cj.service.ChengJiService;
import cj.view.operation;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ModifyView implements operation{

	private final static String zhuangtai1="准备输入id";
	private final static String zhuangtai2="准备输入name";
	
	String flag = zhuangtai1;
	List<ChengJi> cjList= null;
	ChengJi cj = null;
	Scanner scan = new Scanner(System.in);
    @Override
    public String operation() throws InputMismatchException {
       
        while(true){
        	if(zhuangtai1.equals(flag)) {
        		System.out.println("请输入要修改的成绩单id,返回上一层菜单请输入0:");
        		int id = scan.nextInt();
        		if (id==0) 
        			return "登录成功状态";
        		//根据id查找出来这个成绩单的信息
        		cj = chengJiService.queryChengJiById(id);
        		if(cj==null) {
        			System.out.println("id为"+id+"的成绩单不存在");
        			continue;
        		}
        		flag=zhuangtai2;
        	}
        	if(zhuangtai2.equals(flag)) {
        		//修改name属性
        		System.out.println("请输入要修改的成绩单的name,返回上一层菜单请输入0:");
            	String name = scan.next();
            	if ("0".equals(name))
            		return "登录成功状态";
            	if (!check(name))
            			continue;
            	//更新 name
            	cj.setName(name);
            	boolean rs=chengJiService.updateChengJi(cj);
            	if(rs) {
            		System.out.println("修改成功~~~~~~(^ω^)~~~~~~");
            		System.out.println("下一个");
            		flag=zhuangtai1;
            	}else {
            		System.out.println("添加失败~~~~~~(╯︿╰)~~~~~~");
            		System.out.println("重新来");
            		flag=zhuangtai1;
            	}
        	}
        }
    }

    //规范输入
	public boolean check(String name) {
		if(name.isEmpty()) {
			System.out.println("输入不能为空");
			return false;
		}
		else if(name.length()>20) {
			System.out.println("输入的name不可以超过20个字符");
			return false;
		}else if(name.length()<2) {
			System.out.println("输入的name不可以小于2个字符");
			return false;
		}else {
        //根据name查询这个成绩单在数据库里面是否已经有了
        cjList=chengJiService.queryChengJiByName(name);
        if(cjList==null||cjList.size()==0)
        	return true;
        	else {
            	System.out.println("name为"+name+"的成绩单已经存在,请换个名字，返回上一层菜单请输入0");
            	return false;
            }
        }
	}
	
}
