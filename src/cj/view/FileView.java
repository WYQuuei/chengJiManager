package cj.view;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

import cj.tool.FileTool;

public class FileView implements operation {

	private final static String zhuangtai1="׼�������ļ���";
	private final static String zhuangtai2="׼�������ļ�·��";
	private final static String zhuangtai3="׼�������ļ�";
	
	String flag = zhuangtai1;
	Scanner scan = new Scanner(System.in);
	//Ĭ��·��
	private String defaultPath = "C:\\chengji\\file\\";
	private String defaultName = "";
	private FileTool ft;
	
	//�ù��캯������[Ĭ���ļ���],[�ļ�����]������.
	public FileView (FileTool ft) {
		this.ft=ft;
	}
	
	@Override
	public String operation() throws InputMismatchException {
		
        while(true){
        	//�����ļ���
        	if(zhuangtai1.equals(flag)) {
        		System.out.println("���������ļ�����ʹ��ϵͳĬ�ϵ��ļ���������1,������һ��˵�������0:");
        		String name = scan.next();
        		if("0".equals(name))
        			return "��¼�ɹ�״̬";
        		else if("1".equals(name))
        			flag=zhuangtai2;
        		else if(checkFileName(name)) {
        			ft.setFileName(name);
        			flag=zhuangtai2;
        		}
        	}
        	
        	//����·��
        	if(zhuangtai2.equals(flag)) {
        		System.out.println("�������ļ�·����ʹ��ϵͳĬ�ϵ�·��(C:\\chengji\\file\\)������1,������һ��˵�������0:");
        		String path = scan.next();
        		if("0".equals(path))
        			return "��¼�ɹ�״̬";
        		else if("1".equals(path))
        			flag=zhuangtai3;
        		else if(checkPath(path)) {
        			ft.setPath(path);
        			flag=zhuangtai3;
        		}
        		
        	}
        	
        	//�����ļ�
        	if(zhuangtai3.equals(flag)) {
        		boolean jieguo=ft.outFile();
        		if(jieguo)
        			System.out.println("�����ļ��ɹ�~~~~~~(^��^)~~~~~~");
            	else
            		System.out.println("�����ļ�ʧ��~~~~~~(�s��t)~~~~~~");
    			flag=zhuangtai1;
    			return "��¼�ɹ�״̬";
        	}
		}
	}
	
	public boolean checkFileName(String fileName) {
		if(fileName ==null||fileName.length()==0) {
			System.out.println("�ļ�������Ϊ��");
			return false;
		}else if(fileName.length()<4) {
			System.out.println("�ļ�������С��4���ַ�");
			return false;
		}else if(fileName.length()>20) {
			System.out.println("�ļ������ܴ���20���ַ�");
			return false;
		}
		return true;
	}
	
	//���·�������Ƿ���ȷ
	public boolean checkPath(String path) {
		try {
			//�ж�·���Ƿ����
			File targetFile = new File(path);
			if (!targetFile.exists())
				targetFile.mkdirs();
			return true;
		}catch(Exception e) {
			//e.printStackTrace();
			return false;
		}
	}
}