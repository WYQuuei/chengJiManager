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

// ����
public class QueryMainView implements operation {
	List<ChengJiDetail > cjDetailList = null;
    Scanner scan = new Scanner(System.in);
	
    @Override
    public String operation() throws InputMismatchException {
    	
        while(true){
        	System.out.println("��ʼ��ѯ�ɼ�������,��ѡ����ϲ���Ĳ�ѯ��ʽ:");
        	System.out.println("1.����id��ѯ�ɼ�������	2.���ݿ�Ŀ��ѯ�ɼ�������	0.������һ��˵� ");
        	String query = scan.nextLine();
        	if("0".equals(query))
                return "��¼�ɹ�״̬";
        	else if("1".equals(query))
        		return "����id��ѯ�ɼ�������";
        	else if("2".equals(query))
        		return "���ݿ�Ŀ��ѯ�ɼ�������";
        	else
        		System.out.println("��������");
        }
    }
}
