package cj.view.chengJiView;

import java.util.InputMismatchException;
import java.util.Scanner;

import cj.service.ChengJiService;
import cj.view.operation;

// ����id����ɾ���û�
public class DeleteView implements operation {
	
    @Override
    public String operation() throws InputMismatchException {
        Scanner scan = new Scanner(System.in);
        while(true){
            System.out.println("��������Ҫɾ����id��������һ��˵�������0:");
            int id = scan.nextInt();
            if("0".equals(id) ){
                return "��¼�ɹ�״̬";
            }
            System.out.println("���ɾ������ɼ���,��ô�������ȫ����Ϣ(���Ƴɼ�)�Ķ��ᱻɾ��,��ȷ��Ҫ��ô����?");
            System.out.println("���ɾ��������1,������һ��˵�������0,����ɾ����ĳɼ��������������ַ�");
            
            String xuanze = scan.next();
            if("1".equals(xuanze))
            	System.out.println("���������");
            else if("0".equals(xuanze)) {
            	System.out.println("�������");
            	return "��¼�ɹ�״̬";
            }else
            	continue;
            
            //��ʼɾ���ɼ���
            boolean flag = chengJiService.deleteChengJiById(id);
            if(flag){
            	//��ʼɾ���ɼ�����ϸ
            	chengJiDetailService.deleteChengJiDetailById(id);
                System.out.println("ɾ���ɹ�~~~~~~(^��^)~~~~~~");
            	System.out.println("��һ��");
            }else{
                System.out.println("ɾ��ʧ��~~~~~~(�s��t)~~~~~~");
                System.out.println("������");
            }
        }
    }
}
