package cj.view.detailView;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import cj.encapsulation.ChengJi;
import cj.encapsulation.ChengJiDetail;
import cj.view.operation;

public class ModifyDetailView implements operation{

	final static String zhuangtai1="准备输入id";
	final static String zhuangtai2="准备输入kemu";
	final static String zhuangtai3="准备输入score";
	final static String zhuangtai4="准备更新成绩详情";
	String flag = zhuangtai1;
	List<ChengJiDetail> cjdList= null;
	ChengJiDetail cjd = new ChengJiDetail();
	
	//初始化
	public ModifyDetailView(ChengJi cj) {
		cjd.setChengJi(cj);
	}
	
    @Override
    public String operation() throws InputMismatchException {
        Scanner scan = new Scanner(System.in);
        while(true){
        	if(zhuangtai1.equals(flag)) {
        		System.out.println("请输入要修改的成绩单id,返回上一层菜单请输入0:");
        		int id = scan.nextInt();
        		if (id==0) 
        			return "根据id查询成绩单详情";
        		else if(checkId(id))
            		flag=zhuangtai2;
        	}
    		//修改kemu属性
        	if(zhuangtai2.equals(flag)) {
        		System.out.println("请输入这个id下要修改的的科目名字,不修改请输入9,返回上一层菜单请输入0:");
            	String kemu = scan.next();
            	if ("0".equals(kemu))
            		return "根据id查询成绩单详情";
            	else if("9".equals(kemu))
            		flag=zhuangtai3;
            	else {
            		if (checkKemu(kemu)) {
                		//更新 kemu
                		cjd.setKemu(kemu);
                		flag=zhuangtai3;
            		}else
            			continue;
            	}
        	}
        	//修改score属性
        	if(zhuangtai3.equals(flag)) {
        		System.out.println("请输入要修改的成绩单的分数,返回上一层菜单请输入0:");
            	int score = scan.nextInt();
            	if (0==score)
            		return "根据id查询成绩单详情";
            	else if("9".equals(score))
            		flag = zhuangtai4;
            	else if (checkScore(score)) {
                	//更新 score
                	cjd.setScore(score);
                	flag = zhuangtai4;
            	}else
            		continue;
        	}
        	
            //执行service的sql
            if(zhuangtai4.equals(flag)) {
            	boolean rs=chengJiDetailService.updateChengJiDetail(cjd);
            	if(rs) {
            		System.out.println("修改成功~~~~~~(^ω^)~~~~~~");
                	//重新统计总分和平均分
                	chengJiService.updateChengJiTotalAndAvg();
            		flag=zhuangtai1;
            	}else {
            		System.out.println("修改失败~~~~~~(╯︿╰)~~~~~~");
            		flag=zhuangtai1;
            	}
        	}
        }
    }

    //规范id的输入
  	public boolean checkId(int id) {
  		ChengJiDetail cjdTemp = null;
        //根据id查询这个成绩单在数据库的信息
  		cjdTemp=chengJiDetailService.queryOneChengJiDetailByID(id);
          if(cjdTemp==null) {
        	System.out.println("id为"+id+"的成绩详情不存在");
          	return false;
          }else {
        	  //如果存在这个id的[成绩详情]信息,放入默认科目分数等信息
        	  cjd.setId(id);
          	  cjd.setKemu(cjdTemp.getKemu());
          	  cjd.setScore(cjdTemp.getScore());
          	return true;
          }
  	}
  	
    //规范科目的输入
	public boolean checkKemu(String kemu) {
		if(kemu.isEmpty()) {
			System.out.println("输入不能为空");
			return false;
		}
		else if(kemu.length()>20) {
			System.out.println("输入的kemu不可以超过20个字符");
			return false;
		}else if(kemu.length()<2) {
			System.out.println("输入的kemu不可以小于2个字符");
			return false;
		}else {
			cjd.setKemu(kemu);
        //根据kemu查询这个成绩单在数据库里面是否已经有了
        cjdList=chengJiDetailService.queryChengJiDetailByKemu(cjd);
        if(cjdList==null||cjdList.size()==0) {
        	return true;
        }else {
        	System.out.println("kemu为"+kemu+"的科目成绩已经存在,请换个科目名字");
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
