package cj.view.detailView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import cj.encapsulation.ChengJi;
import cj.encapsulation.ChengJiDetail;
import cj.tool.FileTool;
import cj.view.operation;

// 根据id查找
public class QueryDetailView1 implements operation {
	List<ChengJiDetail > cjDetailList = null;
	ChengJi cj = null;
    Scanner scan = new Scanner(System.in);
    private final static String zhuangtai1="准备输入id";
	private final static String zhuangtai2="准备新增,删除,修改成绩单详情";
	
	String flag = zhuangtai1;
	int id;
    @Override
    public String operation() throws InputMismatchException {
    	
        while(true){
        	if(zhuangtai1.equals(flag)) {
            	//准备根据id查询成绩单详情
            		System.out.println("请输入[成绩单]的[id]来查看[成绩单详情],返回上一级菜单请输入[0]");
            		id = scan.nextInt();
            		if(0==id)
                        return "查询成绩单详情主界面";
            		else if(checkByID(id)) {
            			cjDetailList=chengJiDetailService.queryChengJiDetailByID(id);
                        System.out.println(showChengJiDetailByID());
                        flag=zhuangtai2;
            		}else
            			System.out.println("输入有误");
            	}
        	if(zhuangtai2.equals(flag)) {
            	System.out.println("请选择接下来的操作:");
                System.out.println("1.新建成绩单详情	2.删除成绩单详情	3.修改成绩单详情	"
                    		+ "4.导出详情到文件	5.换个成绩单id	0.返回上一级菜单");
          			int caozuo = scan.nextInt();
          			if(1==caozuo)
          				return "新建成绩单详情";
          			else if(2==caozuo)
          				return "删除成绩单详情";
          			else if(3==caozuo)
          				return "修改成绩单详情";
          			else if(4==caozuo)
          				return "导出详情到文件";
          			else if(5==caozuo) {
          				flag=zhuangtai1;
          				continue;
          			}else if(0==caozuo)
          				return "查询成绩单详情主界面";
          			else
          				System.out.println("输入有误,请重新输入1-5或者0");
            }
        }
    }
	
    //查看是否能根据id搜到
    public boolean checkByID(int id) {
    	//查询成绩详情
        cj=chengJiService.queryChengJiById(id);
        if(cj==null) {
        	System.out.println("id有误");
        	return false;
        }
        return true;
    }
    
	//展示按照[成绩单id]查询得到的 [成绩单详情]信息
	public String showChengJiDetailByID() {
		StringBuilder sb = new StringBuilder(); 
		ChengJi cj =null;
		//设定输出格式
        DecimalFormat df = new DecimalFormat("0.00");
        SimpleDateFormat sdf=new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
        //取得总分,平均分,成绩单名称等信息;
        if(cjDetailList!=null&&cjDetailList.size()!=0) {
         cj = cjDetailList.get(0).getChengJi();
    	//输出标题title
        sb.append("-----------------\t["+cj.getName()+"]\t的成绩单详情-----------------------------\r\n");                   
        sb.append("|	id	|	科目	|	分数	|	创建时间	|\r\n");                    
        sb.append("-----------------------------------------------------------------------\r\n");
        //输出成绩单详情的正文
        for (ChengJiDetail c : cjDetailList) {
        	sb.append("|	"+c.getId() + "\t|\t");
            sb.append(c.getKemu()+ "\t|\t");
            sb.append(df.format(c.getScore())+ "\t|\t");
            sb.append(sdf.format(c.getTime())+"|\r\n");
        }
        //输出成绩单总分,平均分的正文
        sb.append("---总分: "+df.format(cj.getTotal())+"--------------------------");
        sb.append("---平均分: "+df.format(cj.getAverage())+"-----------------------");
        }else {
        	//输出标题title
            sb.append("-----------------\t[]\t的成绩单详情-----------------------------\r\n");                   
            sb.append("|	id	|	科目	|	分数	|	创建时间	|\r\n");                    
            sb.append("-----------------------------------------------------------------------\r\n");
            sb.append("---总分: --------------------------");
            sb.append("---平均分: -----------------------");
            
        }
        return sb.toString();
    }
    
	//用于生成文件
	public FileTool returnParam() {
		FileTool ft = new FileTool();
		ft.setFileName("我的"+cjDetailList.get(0).getChengJi().getName()+"成绩.txt");
		ft.setFileText(showChengJiDetailByID());
		return ft;
	}
	
	//用于新增,修改成绩详情
	public ChengJi returnChengJi() {
		return cj;
		}
}
