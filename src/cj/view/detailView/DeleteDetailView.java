package cj.view.detailView;

import java.util.InputMismatchException;
import java.util.Scanner;

import cj.service.ChengJiService;
import cj.view.operation;

// ����id����ɾ���ɼ�����ϸ
public class DeleteDetailView implements operation {
	
    @Override
    public String operation() throws InputMismatchException {
        Scanner scan = new Scanner(System.in);
        System.out.println("��������Ҫɾ���ĳɼ�����ϸ��id��������һ��˵�������0:");
        while(true){
            int id = scan.nextInt();
            if(0==id )
            	return "��ѯ�ɼ�������������";
            
            //��ʼɾ���ɼ�����ϸ
            boolean flag = chengJiDetailService.deleteChengJiDetailById(id);
            if(flag){
                System.out.println("ɾ���ɹ�~~~~~~(^��^)~~~~~~");
            	System.out.println("����ɾ����������һ���ɼ�����ϸid,������һ��˵�������0");
            	//����ͳ���ֺܷ�ƽ����
            	chengJiService.updateChengJiTotalAndAvg();
            }else{
                System.out.println("ɾ��ʧ��~~~~~~(�s��t)~~~~~~");
                System.out.println("����ɾ����������һ���ɼ�����ϸid,������һ��˵�������0");
            }
        }
    }
}
