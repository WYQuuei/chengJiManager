package cj.view;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import cj.encapsulation.ChengJi;
import cj.service.ChengJiService;
import cj.tool.FileTool;

public class MainView implements operation{

	List<ChengJi> cjList;
	Scanner scan = new Scanner(System.in);
	@Override
	public String operation() throws InputMismatchException {
		//查询全部的成绩单信息
		cjList = chengJiService.queryChengJi();
		//输出成绩单信息
		System.out.println(showChengJi());
		System.out.println("please select your operation:");
		System.out.println("1.新建成绩单	2.删除成绩单	3.修改成绩单	4.查询成绩单详情"
    		+ "	5.导出到文件中	9.切换账号	0.退出系统");
		while (true) {
		      //选择操作
			int caozuo = scan.nextInt();
			if(1==caozuo)
				return "新建成绩单";
			else if(2==caozuo)
				return "删除成绩单";
			else if(3==caozuo)
				return "修改成绩单";
			else if(4==caozuo)
				return "查询成绩单详情主界面";
			else if(5==caozuo)
				return "导出到文件";
			else if(9==caozuo)
				return "切换账号";
			else if(0==caozuo)
				return "退出系统";
			else
				System.out.println("输入有误,请重新输入1-5或者，9，0");
		}
	}
	
	//展示成绩单列表
	public String showChengJi() {
		StringBuilder sb = new StringBuilder();
		//设定输出格式
        DecimalFormat df = new DecimalFormat("0.00");
        SimpleDateFormat sdf=new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
        //输出 title
        sb.append("------------------我的成绩单列表----------------------------------------------------------------------\r\n");
        sb.append("|	id	|	成绩单名		|	总分	|	平均分	|	创建时间		|\r\n");
    	sb.append("---------------------------------------------------------------------------------------------------\r\n");
    	//输出 成绩单
    	for(ChengJi c:cjList) {
    		sb.append("|	"+c.getId() + "\t|\t");
    		sb.append(c.getName()+ "\t\t|\t");
    		sb.append(df.format(c.getTotal())+"\t|\t");
    		sb.append(df.format(c.getAverage())+ "\t|\t");
    		sb.append(sdf.format(c.getTime())+"\t|\r\n");
    	}
    	return sb.toString();
	}
	
	//用于生成文件
	public FileTool returnParam() {
		FileTool ft = new FileTool();
		ft.setFileName("我的成绩单列表.txt");
		ft.setFileText(showChengJi());
		return ft;
	}

}
