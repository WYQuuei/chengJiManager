package cj.view.detailView;

import cj.controller.ChengJiController;
import cj.encapsulation.ChengJi;
import cj.encapsulation.ChengJiDetail;
import cj.service.ChengJiService;
import cj.tool.FileTool;
import cj.view.operation;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// ���ݿ�Ŀ����
public class QueryDetailView2 implements operation {
	List<ChengJiDetail > cjDetailList = null;
    Scanner scan = new Scanner(System.in);
    private final static String zhuangtai1="׼�������Ŀ";
	private final static String zhuangtai2="׼������,ɾ��,�޸ĳɼ�������";
	
	String flag = zhuangtai1;
    @Override
    public String operation() throws InputMismatchException {
    	
        while(true){
        	if(zhuangtai1.equals(flag)) {
        	//׼������kemu��ѯ�ɼ�������
        		System.out.println("������[�ɼ���]��[��Ŀ]���鿴[�ɼ�������],������һ���˵�������[0]");
        		String kemu = scan.next();
        		if("0".equals(kemu))
        			return "��ѯ�ɼ�������������";
        		else if(checkByKemu(kemu)) {
                    System.out.println(showChengJiDetailByKemu());
                    flag=zhuangtai2;
        		}else
        			System.out.println("��������");
        	}
        	if(zhuangtai2.equals(flag)) {
        		System.out.println("��ѡ��������Ĳ���:");
                System.out.println("1.�½��ɼ�������	2.ɾ���ɼ�������	3.�޸ĳɼ�������	"
                		+ "4.�������鵽�ļ�	5.������Ŀ	0.������һ���˵�");
      			int caozuo = scan.nextInt();
      			if(1==caozuo)
      				return "�½��ɼ�������";
      			else if(2==caozuo)
      				return "ɾ���ɼ�������";
      			else if(3==caozuo)
      				return "�޸ĳɼ�������";
      			else if(4==caozuo)
      				return "�������鵽�ļ�";
      			else if(5==caozuo) {
      				flag=zhuangtai1;
      				continue;
      			}else if(0==caozuo)
      				return "��ѯ�ɼ�������������";
      			else
      				System.out.println("��������,����������1-5����0");
        	}
        }
    }
    //�鿴�Ƿ��ܸ���kemu�ѵ�
    public boolean checkByKemu(String kemu) {
    	//��ѯ�ɼ�����
        cjDetailList = chengJiDetailService.queryChengJiDetailByKemu(kemu);
        if(cjDetailList==null||cjDetailList.size()==0) {
        	System.out.println("û���ҵ������Ŀ��Ӧ��[�ɼ�������]��Ϣ");
        	return false;
        }
        return true;
    }
	
	//չʾ����[��Ŀ]��ѯ�õ��� [�ɼ�������]��Ϣ
		public String showChengJiDetailByKemu() {
			//�ܷ�,������ƽ����
			double allScore = 0D;
			StringBuilder sb = new StringBuilder(); 
			//�趨�����ʽ
	        DecimalFormat df = new DecimalFormat("0.00");
	        SimpleDateFormat sdf=new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
	        //ȡ���ܷ�,ƽ����,�ɼ������Ƶ���Ϣ;
	        ChengJi cj = cjDetailList.get(0).getChengJi();
	    	//�������title
	        sb.append("-------------\t��ĿΪ:["+cj.getName()+"]\t�ĳɼ�������---------------------\r\n");                   
	        sb.append("|	id	|	�����ɼ���	|	����	|	����ʱ��	|\r\n");                    
	        sb.append("-------------------------------------------------------------------\r\n");
	        //����ɼ������������
	        for (ChengJiDetail c : cjDetailList) {
	        	sb.append("|	"+c.getId() + "\t|\t");
	            sb.append(c.getChengJi().getName()+ "\t|\t");
	            sb.append(df.format(c.getScore())+ "\t|\t");
	            sb.append(sdf.format(c.getTime())+"|\r\n");
	            //ͳ���ܷ�
	            allScore += c.getScore();
	        }
	        //����ɼ���,�����ƽ���ֵ�����
	        sb.append("---��ƽ����: "+df.format(allScore/(double)cjDetailList.size())+"--------------");
	        return sb.toString();
	    }
    
		//���������ļ�
		public FileTool returnParam() {
			FileTool ft = new FileTool();
			ft.setFileName("�ҵ�"+cjDetailList.get(0).getKemu()+"�ɼ�.txt");
			ft.setFileText(showChengJiDetailByKemu());
			return ft;
		}
}
