package cj.view.chengJiView;

import cj.controller.ChengJiController;
import cj.encapsulation.ChengJi;
import cj.service.ChengJiService;
import cj.view.operation;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ModifyView implements operation{

	private final static String zhuangtai1="׼������id";
	private final static String zhuangtai2="׼������name";
	
	String flag = zhuangtai1;
	List<ChengJi> cjList= null;
	ChengJi cj = null;
	Scanner scan = new Scanner(System.in);
    @Override
    public String operation() throws InputMismatchException {
       
        while(true){
        	if(zhuangtai1.equals(flag)) {
        		System.out.println("������Ҫ�޸ĵĳɼ���id,������һ��˵�������0:");
        		int id = scan.nextInt();
        		if (id==0) 
        			return "��¼�ɹ�״̬";
        		//����id���ҳ�������ɼ�������Ϣ
        		cj = chengJiService.queryChengJiById(id);
        		if(cj==null) {
        			System.out.println("idΪ"+id+"�ĳɼ���������");
        			continue;
        		}
        		flag=zhuangtai2;
        	}
        	if(zhuangtai2.equals(flag)) {
        		//�޸�name����
        		System.out.println("������Ҫ�޸ĵĳɼ�����name,������һ��˵�������0:");
            	String name = scan.next();
            	if ("0".equals(name))
            		return "��¼�ɹ�״̬";
            	if (!check(name))
            			continue;
            	//���� name
            	cj.setName(name);
            	boolean rs=chengJiService.updateChengJi(cj);
            	if(rs) {
            		System.out.println("�޸ĳɹ�~~~~~~(^��^)~~~~~~");
            		System.out.println("��һ��");
            		flag=zhuangtai1;
            	}else {
            		System.out.println("���ʧ��~~~~~~(�s��t)~~~~~~");
            		System.out.println("������");
            		flag=zhuangtai1;
            	}
        	}
        }
    }

    //�淶����
	public boolean check(String name) {
		if(name.isEmpty()) {
			System.out.println("���벻��Ϊ��");
			return false;
		}
		else if(name.length()>20) {
			System.out.println("�����name�����Գ���20���ַ�");
			return false;
		}else if(name.length()<2) {
			System.out.println("�����name������С��2���ַ�");
			return false;
		}else {
        //����name��ѯ����ɼ��������ݿ������Ƿ��Ѿ�����
        cjList=chengJiService.queryChengJiByName(name);
        if(cjList==null||cjList.size()==0)
        	return true;
        	else {
            	System.out.println("nameΪ"+name+"�ĳɼ����Ѿ�����,�뻻�����֣�������һ��˵�������0");
            	return false;
            }
        }
	}
	
}
