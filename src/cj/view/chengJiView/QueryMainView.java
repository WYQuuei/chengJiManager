package cj.view.chengJiView;

import cj.controller.ChengJiController;
import cj.encapsulation.ChengJi;
import cj.encapsulation.ChengJiDetail;
import cj.service.ChengJiService;
import cj.view.operation;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// 查找
public class QueryMainView implements operation {
	List<ChengJiDetail > cjDetailList = null;
    Scanner scan = new Scanner(System.in);
	
    @Override
    public String operation() throws InputMismatchException {
    	
        while(true){
        	System.out.println("开始查询成绩单详情,请选择你喜欢的查询方式:");
        	System.out.println("1.根据id查询成绩单详情	2.根据科目查询成绩单详情	0.返回上一层菜单 ");
        	String query = scan.nextLine();
        	if("0".equals(query))
                return "登录成功状态";
        	else if("1".equals(query))
        		return "根据id查询成绩单详情";
        	else if("2".equals(query))
        		return "根据科目查询成绩单详情";
        	else
        		System.out.println("输入有误");
        }
    }
}
