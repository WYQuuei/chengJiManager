package cj.view.chengJiView;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import cj.encapsulation.ChengJi;
import cj.view.operation;

// ��ӳɼ���Ϣ
public class AddView implements operation {
	
	private final static String zhuangtai1="׼����ӳɼ���";
	private final static String zhuangtai2="׼����ӳɼ�������";
	
	String flag = zhuangtai1;
	Scanner scan = new Scanner(System.in);
	List<ChengJi> cjList= null;
	@Override
    public String operation() throws InputMismatchException{
        
        System.out.println("������Ҫ��ӵĳɼ���������(����:��ĩ����)��������һ��˵�������0:");
        while(true){
        	if(zhuangtai1.equals(flag)) {
        		String name = scan.next();
        		if("0".equals(name)){
        			return "��¼�ɹ�״̬";
        		}else if("9".equals(name))
        			return "��¼�ɹ�״̬";
            
        		//���һ�������Ƿ���Ϲ淶,����������������
        		if(!check(name))continue;
            
        		//��ʼ����ɼ���
        		ChengJi cj = new ChengJi(name);
        		boolean flag = chengJiService.insertChengJi(cj);
        		if(flag) {
        			System.out.println("��ӳɹ�~~~~~~(^��^)~~~~~~");
        			System.out.println("�������[�ɼ���]��������һ��name,׼�����["+name+"]������������9,ȡ���뷵����һ��˵�������0");
        		}else {
        			System.out.println("���ʧ��~~~~~~(�s��t)~~~~~~");
        			System.out.println("���������������һ��name,ȡ���뷵����һ��˵�������0");
        		}
        	}
        }
    }

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
