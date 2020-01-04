package cj.controller;

import java.util.InputMismatchException;

import cj.encapsulation.ChengJi;
import cj.tool.FileTool;
import cj.view.FileView;
import cj.view.LoginView;
import cj.view.MainView;
import cj.view.operation;
import cj.view.chengJiView.AddView;
import cj.view.chengJiView.DeleteView;
import cj.view.chengJiView.ModifyView;
import cj.view.chengJiView.QueryMainView;
import cj.view.detailView.AddDetailView;

public class ChengJiController {
	
	//�趨ϵͳ��״̬��
	private final static String zhuangtai1="��ʼ״̬";
//	private final static String zhuangtai2="�˳�ϵͳ";
//	private final static String zhuangtai3="��¼�ɹ�״̬";
//	private final static String zhuangtai4="�½��ɼ���";
//	private final static String zhuangtai5="ɾ���ɼ���";
//	private final static String zhuangtai6="�޸ĳɼ���";
//	private final static String zhuangtai7="��ѯ�ɼ�������������";
//	private final static String zhuangtai8="�������ļ�";
	String zhuflag = zhuangtai1;
    operation o = null;
    //����[�ɼ���]��Ϣ.����,�޸�[�ɼ�������]ʱʹ��
    ChengJi cj = null;
    //���ݴ����ļ���Ϣ�Ĺ�����
    FileTool ft = null;
    public void controller() throws InputMismatchException{
        while (true) {
        	
    		//����login��¼����
        	if("��ʼ״̬".equals(zhuflag)) {
        		o = new LoginView();
        		zhuflag = o.operation();
        	}
        	
        	//�˳���ǰ�˺�,���µ�¼
        	if("�л��˺�".equals(zhuflag)) {
        		System.out.println("�ɹ��˳���ǰ�˺�!");
        		zhuflag = "��ʼ״̬";
        	}
        	
    		//�˳�ϵͳ
        	if("�˳�ϵͳ".equals(zhuflag)) {
        		System.out.println("�ټ�!��ӭ�´�����!bye~~~~~~");
        		return;
        	}
        	
        	//��¼�ɹ�,׼���������Ĳ���
        	if("��¼�ɹ�״̬".equals(zhuflag)) {
        		try {
        			//��ϵͳ����1.5����,����ǰ���������������ÿ�����,���ɾ��Ҳ��.
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        		//����Mainѡ�����
        		o = new MainView();
        		zhuflag = o.operation();
        		//����
        		if("�������ļ�".equals(zhuflag))
        			ft=((MainView)o).returnParam();
        	}
        	
        	//����[�½��ɼ���]ģ��
        	if("�½��ɼ���".equals(zhuflag)) {
        		o = new AddView();
        		zhuflag = o.operation();
        	}
        	
        	//����[�½��ɼ�������]ģ��
        	if("�½��ɼ�������".equals(zhuflag)) {
        		o = new AddDetailView(cj);
        		zhuflag = o.operation();
        	}
        	
        	//����[ɾ���ɼ���]ģ��
        	if("ɾ���ɼ���".equals(zhuflag)) {
        		o = new DeleteView();
        		zhuflag = o.operation();
        	}
        	
        	//����[�޸ĳɼ���]ģ��
        	if("�޸ĳɼ���".equals(zhuflag)) {
        		o = new ModifyView();
        		zhuflag = o.operation();
        	}
        	
        	//����[�ɼ�������]ģ������
        	//��ʼ����[�ɼ�������]�Ŀ��Ʋ�,�������������[�ɼ���]��[�ɼ�������]��Ψһ����.
        	if("��ѯ�ɼ�������������".equals(zhuflag)) {
        		DetailController details=new DetailController(zhuflag);
        		zhuflag = details.controller();
        	}
        	
        	//����[�������ļ�]ģ��
        	if("�������ļ�".equals(zhuflag)) {
        		o = new FileView(ft);
        		zhuflag = o.operation();
        	}
        }
    }
}
