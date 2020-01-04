package cj.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import cj.encapsulation.ChengJi;
import cj.service.ChengJiService;
import cj.tool.FileTool;
import cj.view.*;
import cj.view.chengJiView.AddView;
import cj.view.chengJiView.DeleteView;
import cj.view.chengJiView.ModifyView;
import cj.view.chengJiView.QueryMainView;
import cj.view.detailView.AddDetailView;
import cj.view.detailView.DeleteDetailView;
import cj.view.detailView.ModifyDetailView;
import cj.view.detailView.QueryDetailView1;
import cj.view.detailView.QueryDetailView2;

public class DetailController {

	//�趨ϵͳ��״̬��
//	private final static String zhuangtai1="��ѯ�ɼ�������������";
//	private final static String zhuangtai2="�˳��������";
//	private final static String zhuangtai4="�½��ɼ�������";
//	private final static String zhuangtai5="ɾ���ɼ�������";
//	private final static String zhuangtai6="�޸ĳɼ�������";
//	private final static String zhuangtai8="�������鵽�ļ�";
    Scanner scan = new Scanner(System.in);
    String flag = "";
    operation o = null;
    //���ݴ����ļ���Ϣ�Ĺ�����
    FileTool ft = null;
    //����[�ɼ���]��Ϣ.����,�޸�[�ɼ�������]ʱʹ��
    ChengJi cj = null;
    //���캯������״̬��flag
    public  DetailController(String flag) {
    	this.flag = flag;
    }
    
    public String controller() throws InputMismatchException{
        while (true) {
        	
        	//�ɹ������ѯ�ɼ�������[������],׼���������Ĳ���
        	if("��ѯ�ɼ�������������".equals(flag)) {
        		//����QueryMainѡ�����
        		o = new QueryMainView();
        		flag = o.operation();
        		if("��¼�ɹ�״̬".equals(flag))
        			return "��¼�ɹ�״̬";
        	}
        	
        	//����[id]��ѯ[�ɼ�������]
        	if("����id��ѯ�ɼ�������".equals(flag)) {
        		o = new QueryDetailView1();
        		flag = o.operation();
        		//����
        		if("�������鵽�ļ�".equals(flag))
        			ft=((QueryDetailView1)o).returnParam();
        		else if("�½��ɼ�������".equals(flag))
        			cj=((QueryDetailView1)o).returnChengJi();
        		else if("�޸ĳɼ�������".equals(flag))
        			cj=((QueryDetailView1)o).returnChengJi();
        	}
        	
        	//����[��Ŀ]��ѯ[�ɼ�������]
        	if("���ݿ�Ŀ��ѯ�ɼ�������".equals(flag)) {
        		o = new QueryDetailView2();
        		flag = o.operation();
        		//����
        		if("�������鵽�ļ�".equals(flag))
        			ft=((QueryDetailView2)o).returnParam();
        	}
        	
        	//����[�½��ɼ�������]ģ��
        	if("�½��ɼ�������".equals(flag)) {
        		o = new AddDetailView(cj);
        		flag = o.operation();
        	}
        	
        	//����[ɾ���ɼ�������]ģ��
        	if("ɾ���ɼ�������".equals(flag)) {
        		o = new DeleteDetailView();
        		flag = o.operation();
        	}
        	
        	//����[�޸ĳɼ�������]ģ��
        	if("�޸ĳɼ�������".equals(flag)) {
        		o = new ModifyDetailView(cj);
        		flag = o.operation();
        	}
        	
        	//����[�������鵽�ļ�]ģ��
        	if("�������鵽�ļ�".equals(flag)) {
        		o = new FileView(ft);
        		flag = o.operation();
        	}

        	//"��¼�ɹ�״̬"
        	if("��¼�ɹ�״̬".equals(flag))
        		flag = "��ѯ�ɼ�������������";
        }
    }
}
