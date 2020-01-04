package cj.view;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import cj.encapsulation.ChengJi;
import cj.service.ChengJiService;
import cj.tool.FileTool;

public class MainView implements operation{

	List<ChengJi> cjList;
	Scanner scan = new Scanner(System.in);
	@Override
	public String operation() throws InputMismatchException {
		//��ѯȫ���ĳɼ�����Ϣ
		cjList = chengJiService.queryChengJi();
		//����ɼ�����Ϣ
		System.out.println(showChengJi());
		System.out.println("please select your operation:");
		System.out.println("1.�½��ɼ���	2.ɾ���ɼ���	3.�޸ĳɼ���	4.��ѯ�ɼ�������"
    		+ "	5.�������ļ���	9.�л��˺�	0.�˳�ϵͳ");
		while (true) {
		      //ѡ�����
			int caozuo = scan.nextInt();
			if(1==caozuo)
				return "�½��ɼ���";
			else if(2==caozuo)
				return "ɾ���ɼ���";
			else if(3==caozuo)
				return "�޸ĳɼ���";
			else if(4==caozuo)
				return "��ѯ�ɼ�������������";
			else if(5==caozuo)
				return "�������ļ�";
			else if(9==caozuo)
				return "�л��˺�";
			else if(0==caozuo)
				return "�˳�ϵͳ";
			else
				System.out.println("��������,����������1-5���ߣ�9��0");
		}
	}
	
	//չʾ�ɼ����б�
	public String showChengJi() {
		StringBuilder sb = new StringBuilder();
		//�趨�����ʽ
        DecimalFormat df = new DecimalFormat("0.00");
        SimpleDateFormat sdf=new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
        //��� title
        sb.append("------------------�ҵĳɼ����б�----------------------------------------------------------------------\r\n");
        sb.append("|	id	|	�ɼ�����		|	�ܷ�	|	ƽ����	|	����ʱ��		|\r\n");
    	sb.append("---------------------------------------------------------------------------------------------------\r\n");
    	//��� �ɼ���
    	for(ChengJi c:cjList) {
    		sb.append("|	"+c.getId() + "\t|\t");
    		sb.append(c.getName()+ "\t\t|\t");
    		sb.append(df.format(c.getTotal())+"\t|\t");
    		sb.append(df.format(c.getAverage())+ "\t|\t");
    		sb.append(sdf.format(c.getTime())+"\t|\r\n");
    	}
    	return sb.toString();
	}
	
	//���������ļ�
	public FileTool returnParam() {
		FileTool ft = new FileTool();
		ft.setFileName("�ҵĳɼ����б�.txt");
		ft.setFileText(showChengJi());
		return ft;
	}

}
