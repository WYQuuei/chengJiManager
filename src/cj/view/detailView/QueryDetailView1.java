package cj.view.detailView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import cj.encapsulation.ChengJi;
import cj.encapsulation.ChengJiDetail;
import cj.tool.FileTool;
import cj.view.operation;

// ����id����
public class QueryDetailView1 implements operation {
	List<ChengJiDetail > cjDetailList = null;
	ChengJi cj = null;
    Scanner scan = new Scanner(System.in);
    private final static String zhuangtai1="׼������id";
	private final static String zhuangtai2="׼������,ɾ��,�޸ĳɼ�������";
	
	String flag = zhuangtai1;
	int id;
    @Override
    public String operation() throws InputMismatchException {
    	
        while(true){
        	if(zhuangtai1.equals(flag)) {
            	//׼������id��ѯ�ɼ�������
            		System.out.println("������[�ɼ���]��[id]���鿴[�ɼ�������],������һ���˵�������[0]");
            		id = scan.nextInt();
            		if(0==id)
                        return "��ѯ�ɼ�������������";
            		else if(checkByID(id)) {
            			cjDetailList=chengJiDetailService.queryChengJiDetailByID(id);
                        System.out.println(showChengJiDetailByID());
                        flag=zhuangtai2;
            		}else
            			System.out.println("��������");
            	}
        	if(zhuangtai2.equals(flag)) {
            	System.out.println("��ѡ��������Ĳ���:");
                System.out.println("1.�½��ɼ�������	2.ɾ���ɼ�������	3.�޸ĳɼ�������	"
                    		+ "4.�������鵽�ļ�	5.�����ɼ���id	0.������һ���˵�");
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
	
    //�鿴�Ƿ��ܸ���id�ѵ�
    public boolean checkByID(int id) {
    	//��ѯ�ɼ�����
        cj=chengJiService.queryChengJiById(id);
        if(cj==null) {
        	System.out.println("id����");
        	return false;
        }
        return true;
    }
    
	//չʾ����[�ɼ���id]��ѯ�õ��� [�ɼ�������]��Ϣ
	public String showChengJiDetailByID() {
		StringBuilder sb = new StringBuilder(); 
		ChengJi cj =null;
		//�趨�����ʽ
        DecimalFormat df = new DecimalFormat("0.00");
        SimpleDateFormat sdf=new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
        //ȡ���ܷ�,ƽ����,�ɼ������Ƶ���Ϣ;
        if(cjDetailList!=null&&cjDetailList.size()!=0) {
         cj = cjDetailList.get(0).getChengJi();
    	//�������title
        sb.append("-----------------\t["+cj.getName()+"]\t�ĳɼ�������-----------------------------\r\n");                   
        sb.append("|	id	|	��Ŀ	|	����	|	����ʱ��	|\r\n");                    
        sb.append("-----------------------------------------------------------------------\r\n");
        //����ɼ������������
        for (ChengJiDetail c : cjDetailList) {
        	sb.append("|	"+c.getId() + "\t|\t");
            sb.append(c.getKemu()+ "\t|\t");
            sb.append(df.format(c.getScore())+ "\t|\t");
            sb.append(sdf.format(c.getTime())+"|\r\n");
        }
        //����ɼ����ܷ�,ƽ���ֵ�����
        sb.append("---�ܷ�: "+df.format(cj.getTotal())+"--------------------------");
        sb.append("---ƽ����: "+df.format(cj.getAverage())+"-----------------------");
        }else {
        	//�������title
            sb.append("-----------------\t[]\t�ĳɼ�������-----------------------------\r\n");                   
            sb.append("|	id	|	��Ŀ	|	����	|	����ʱ��	|\r\n");                    
            sb.append("-----------------------------------------------------------------------\r\n");
            sb.append("---�ܷ�: --------------------------");
            sb.append("---ƽ����: -----------------------");
            
        }
        return sb.toString();
    }
    
	//���������ļ�
	public FileTool returnParam() {
		FileTool ft = new FileTool();
		ft.setFileName("�ҵ�"+cjDetailList.get(0).getChengJi().getName()+"�ɼ�.txt");
		ft.setFileText(showChengJiDetailByID());
		return ft;
	}
	
	//��������,�޸ĳɼ�����
	public ChengJi returnChengJi() {
		return cj;
		}
}
