package cj.view.detailView;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import cj.encapsulation.ChengJi;
import cj.encapsulation.ChengJiDetail;
import cj.view.operation;

// 添加成绩信息
public class AddDetailView implements operation {

	final static String zhuangtai1="准备输入kemu";
	final static String zhuangtai2="准备输入score";
	
	String flag = zhuangtai1;
	List<ChengJiDetail> cjList= null;
	ChengJiDetail cj = null;
	
	//初始化成绩单的信息
	public  AddDetailView(ChengJi chengJi) {
		cj = new ChengJiDetail();
		cj.setChengJi(chengJi);
	}
	
	@Override
    public String operation() throws InputMismatchException {
        Scanner scan = new Scanner(System.in);
        
        while(true){
        	//添加科目成绩
        	if(zhuangtai1.equals(flag)) {
        		System.out.println("请输入要添加的科目的名称(比如:大学英语)，返回上一层菜单请输入0:");
        		String kemu = scan.next();
        		if("0".equals(kemu))
        			return "根据id查询成绩单详情";
        		//检查一下输入是否符合规范,不符合请重新输入
        		if(checkKemu(kemu)) {
            		cj.setKemu(kemu);
            		flag=zhuangtai2;
        		}else
        			continue;
        	}
        	//添加分数成绩
        	if(zhuangtai2.equals(flag)) {
        		System.out.println("请输入要添加的科目对应的分数(比如:60)，返回上一层菜单请输入0:");
        		int score = scan.nextInt();
        		if(0==score)
        			return "根据id查询成绩单详情";
        		//检查一下输入是否符合规范,不符合请重新输入
        		if(checkScore(score))
        			cj.setScore(score);
        		else
        			continue;
        	}
        	//开始插入一条科目成绩
            boolean jieguo = chengJiDetailService.insertChengJiDetail(cj);
            if(jieguo) {
            	System.out.println("添加成功~~~~~~(^ω^)~~~~~~");
            	System.out.println("继续添加请输入下一个kemu,取消请返回上一层菜单请输入0");
            	//重新统计总分和平均分
            	chengJiService.updateChengJiTotalAndAvg();
            	flag=zhuangtai1;
            }else {
            	System.out.println("添加失败~~~~~~(╯︿╰)~~~~~~");
            	System.out.println("继续添加请输入下一个kemu,取消请返回上一层菜单请输入0");
            }
        	
        }
    }

	//检查科目的输入是否规范
	public boolean checkKemu(String kemu) {
		if(kemu.isEmpty()) {
			System.out.println("输入不能为空");
			return false;
		}else if(kemu.length()>20) {
			System.out.println("输入的kemu不可以超过20个字符");
			return false;
		}else if(kemu.length()<2) {
			System.out.println("输入的kemu不可以小于2个字符");
			return false;
		}else {
			cj.setKemu(kemu);
        //根据kemu查询这个成绩单在数据库里面是否已经有了
        cjList=chengJiDetailService.queryChengJiDetailByKemu(cj);
        if(cjList==null||cjList.size()==0) {
        	return true;
        }else {
        	System.out.println("kemu为"+kemu+"的成绩单已经存在,请换个名字");
        	return false;
        }
        }
	}
	
	//检查分数的输入是否规范
	public boolean checkScore(int score) {
		if(score<0||score>100) {
			System.out.println("分数的范围为0-100分.");
			return false;
		}
		return true;
	}
}
