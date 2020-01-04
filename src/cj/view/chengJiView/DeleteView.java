package cj.view.chengJiView;

import java.util.InputMismatchException;
import java.util.Scanner;

import cj.service.ChengJiService;
import cj.view.operation;

// 根据id进行删除用户
public class DeleteView implements operation {
	
    @Override
    public String operation() throws InputMismatchException {
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("请输入需要删除的id，返回上一层菜单请输入0:");
            int id = scan.nextInt();
            if("0".equals(id) ){
                return "登录成功状态";
            }
            System.out.println("如果删除这个成绩单,那么它下面的全部信息(各科成绩)的都会被删除,你确定要这么做吗?");
            System.out.println("坚持删除请输入1,返回上一层菜单请输入0,决定删除别的成绩单请输入任意字符");
            
            String xuanze = scan.next();
            if("1".equals(xuanze))
            	System.out.println("你很有主见");
            else if("0".equals(xuanze)) {
            	System.out.println("你很理智");
            	return "登录成功状态";
            }else
            	continue;
            
            //开始删除成绩单
            boolean flag = chengJiService.deleteChengJiById(id);
            if(flag){
            	//开始删除成绩单明细
            	chengJiDetailService.deleteChengJiDetailById(id);
                System.out.println("删除成功~~~~~~(^ω^)~~~~~~");
            	System.out.println("下一个");
            }else{
                System.out.println("删除失败~~~~~~(╯︿╰)~~~~~~");
                System.out.println("重新来");
            }
        }
    }
}
