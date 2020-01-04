package cj.view.detailView;

import java.util.InputMismatchException;
import java.util.Scanner;

import cj.service.ChengJiService;
import cj.view.operation;

// 根据id进行删除成绩单明细
public class DeleteDetailView implements operation {
	
    @Override
    public String operation() throws InputMismatchException {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入需要删除的成绩单明细的id，返回上一层菜单请输入0:");
        while(true){
            int id = scan.nextInt();
            if(0==id )
            	return "查询成绩单详情主界面";
            
            //开始删除成绩单明细
            boolean flag = chengJiDetailService.deleteChengJiDetailById(id);
            if(flag){
                System.out.println("删除成功~~~~~~(^ω^)~~~~~~");
            	System.out.println("继续删除请输入下一个成绩单明细id,返回上一层菜单请输入0");
            	//重新统计总分和平均分
            	chengJiService.updateChengJiTotalAndAvg();
            }else{
                System.out.println("删除失败~~~~~~(╯︿╰)~~~~~~");
                System.out.println("继续删除请输入下一个成绩单明细id,返回上一层菜单请输入0");
            }
        }
    }
}
