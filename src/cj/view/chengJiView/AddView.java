package cj.view.chengJiView;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import cj.encapsulation.ChengJi;
import cj.view.operation;

// 添加成绩信息
public class AddView implements operation {
	
	private final static String zhuangtai1="准备添加成绩单";
	private final static String zhuangtai2="准备添加成绩单详情";
	
	String flag = zhuangtai1;
	Scanner scan = new Scanner(System.in);
	List<ChengJi> cjList= null;
	@Override
    public String operation() throws InputMismatchException{
        
        System.out.println("请输入要添加的成绩单的名称(比如:期末考试)，返回上一层菜单请输入0:");
        while(true){
        	if(zhuangtai1.equals(flag)) {
        		String name = scan.next();
        		if("0".equals(name)){
        			return "登录成功状态";
        		}else if("9".equals(name))
        			return "登录成功状态";
            
        		//检查一下输入是否符合规范,不符合请重新输入
        		if(!check(name))continue;
            
        		//开始插入成绩单
        		ChengJi cj = new ChengJi(name);
        		boolean flag = chengJiService.insertChengJi(cj);
        		if(flag) {
        			System.out.println("添加成功~~~~~~(^ω^)~~~~~~");
        			System.out.println("继续添加[成绩单]请输入下一个name,准备添加["+name+"]的详情请输入9,取消请返回上一层菜单请输入0");
        		}else {
        			System.out.println("添加失败~~~~~~(╯︿╰)~~~~~~");
        			System.out.println("继续添加请输入下一个name,取消请返回上一层菜单请输入0");
        		}
        	}
        }
    }

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
