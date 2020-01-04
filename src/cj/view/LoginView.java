package cj.view;

import cj.controller.ChengJiController;
import cj.encapsulation.*;
import cj.service.*;
import cj.tool.Safe;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.logging.SimpleFormatter;

public class LoginView implements operation{
	//�趨��¼ϵͳ��״̬��
	private final static String zhuangtai1="��ʼ״̬,׼��ѡ��ֱ�ӵ�½(����username)����ע�����˺�(����1)";
	private final static String zhuangtai2="ע��״̬,׼������username";
	private final static String zhuangtai3="ע��״̬,׼������password";
	private final static String zhuangtai4="ע��״̬,׼������¼�뵽���ݿ���";
	private final static String zhuangtai5="��¼״̬,׼������password";
	private final static String zhuangtai6="��¼״̬,׼�������ݿ��к���";
    private final static String zhuangtai7="��¼�ɹ�״̬";
	private final static String zhuangtai8="�˳�ϵͳ";
	String flag = zhuangtai1;
	//����ע��Ķ���
	Login register = new Login();
	//���ڵ�¼�Ķ���
	Login login = new Login();
	
    public String operation(){
        Scanner scan = new Scanner(System.in);
        System.out.println("��ӭ���� ���ɼ�������ϵͳ2.0�� (���n��)");
        System.out.println("����������'�ʻ���'(��֧������)����ɵ�¼,���û���˻�������'1'ע��һ�����û�,�˳�ϵͳ������'0' ");
        while (true) {
        	if(zhuangtai1.equals(flag)) {
        		String username = scan.next();
        		if("0".equals(username)) {
        			flag = zhuangtai8;
        			return flag;
        		}else if("1".equals(username))
        			flag=zhuangtai2;
        		else {
        			login.setUsername(username);
        			flag=zhuangtai5;
        		}
        	}
            if(zhuangtai2.equals(flag)) {
            	System.out.println("��������Ҫע����û���(��֧������): �˳�ϵͳ������'0' ");
                String newUsername = scan.next();
                //�˳�ϵͳ
                if("0".equals(newUsername)) {
                	flag = zhuangtai8;
                	return flag;
                }
                //����û����Ƿ�����淶
                if(checkUser(newUsername)) {
                	register.setUsername(newUsername);
                	flag=zhuangtai3;
                }else
                	 continue;
            	}
            if(zhuangtai3.equals(flag)) {
            	System.out.println("����������:");
            	String newPwd = scan.next();
            	//����һ������������Ƿ���ȷ.
            	if(!checkPwd(newPwd))
            		continue;
                System.out.println("���ٴ����������:");
                String confirmPwd = scan.next();
                if (newPwd.equals(confirmPwd)) {
                	register.setPassword(newPwd);
                	flag=zhuangtai4;
                }else
                    System.out.println("������������벻һ��,��˶�");
            }
            if(zhuangtai4.equals(flag)) {
            	//��ʼע��
                boolean f = loginService.register(register);
                if(f){
                    System.out.println("ע��ɹ���~(^��^)~ ���Զ������¼.");
                    flag = zhuangtai7;
                    return flag;//���ص�����ģ��ChengJiController��flag
                }else{
                    System.out.println("ע��ʧ��!~(�s��t)~ ����һ�ΰ�~~~ �˳�ϵͳ������'0'");
                    flag=zhuangtai2;
                    continue;
                }
            }
            if(zhuangtai5.equals(flag)) {
            	System.out.println("���������룺");
            	String password = scan.next();
            	login.setPassword(password);
            	flag=zhuangtai6;
            }
            if(zhuangtai6.equals(flag)) {
            	boolean jieGuo = loginService.login(login);
                if (jieGuo) {
                	System.out.println("��¼�ɹ�!~(^��^)~");
                	flag = zhuangtai7;
                	return flag;
                } else {
                	flag=zhuangtai1;
                    System.out.println("��¼ʧ�ܣ�~(�s��t)~");
                    System.out.println("�����������˻���,�˳�ϵͳ������'0',ע�����˺�������'1' ");
                }
            }
        }
    }
	//����û����Ƿ�淶
    public boolean checkUser(String username) {
    	if(username==null||username.isEmpty()) {
    		System.out.println("���벻��Ϊ��!");
    		return false;
    	}else if(username.length()>20){
    		System.out.println("�û����ĳ��Ȳ����Գ���20���ַ�!");
    		return false;
    	}else if(username.length()<2){
    		System.out.println("�û������ַ����Ȳ����Ե���2���ַ�");
    		return false;
    	//ȥ���ݿ�����,���û����Ƿ��Ѿ���ռ��
    	}else if(loginService.queryUserByName(Safe.jiaMi(username))){
    		System.out.println("����û����Ѿ���ռ����,�뻻������!");
    		return false;
    	}
    	return true;
    }
    
    //��������Ƿ�淶
    public boolean checkPwd(String password) {
    	if(password==null||password.isEmpty()) {
    		System.out.println("���벻��Ϊ��!");
    		return false;
    	}else if(password.length()>20){
    		System.out.println("����ĳ��Ȳ����Գ���20���ַ�!");
    		return false;
    	}else if(password.length()<2){
    		System.out.println("������ַ����Ȳ����Ե���2���ַ�");
    		return false;
    	}
    	
    	return true;
    }
}