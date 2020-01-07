package cj.view.detailView;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import cj.encapsulation.ChengJi;
import cj.encapsulation.ChengJiDetail;
import cj.view.operation;

// ��ӳɼ���Ϣ
public class AddDetailView implements operation {

	final static String zhuangtai1="׼������kemu";
	final static String zhuangtai2="׼������score";
	
	String flag = zhuangtai1;
	List<ChengJiDetail> cjList= null;
	ChengJiDetail cj = null;
	
	//��ʼ���ɼ�������Ϣ
	public  AddDetailView(ChengJi chengJi) {
		cj = new ChengJiDetail();
		cj.setChengJi(chengJi);
	}
	
	@Override
    public String operation() throws InputMismatchException {
        Scanner scan = new Scanner(System.in);
        
        while(true){
        	//��ӿ�Ŀ�ɼ�
        	if(zhuangtai1.equals(flag)) {
        		System.out.println("������Ҫ��ӵĿ�Ŀ������(����:��ѧӢ��)��������һ��˵�������0:");
        		String kemu = scan.next();
        		if("0".equals(kemu))
        			return "����id��ѯ�ɼ�������";
        		//���һ�������Ƿ���Ϲ淶,����������������
        		if(checkKemu(kemu)) {
            		cj.setKemu(kemu);
            		flag=zhuangtai2;
        		}else
        			continue;
        	}
        	//��ӷ����ɼ�
        	if(zhuangtai2.equals(flag)) {
        		System.out.println("������Ҫ��ӵĿ�Ŀ��Ӧ�ķ���(����:60)��������һ��˵�������0:");
        		int score = scan.nextInt();
        		if(0==score)
        			return "����id��ѯ�ɼ�������";
        		//���һ�������Ƿ���Ϲ淶,����������������
        		if(checkScore(score))
        			cj.setScore(score);
        		else
        			continue;
        	}
        	//��ʼ����һ����Ŀ�ɼ�
            boolean jieguo = chengJiDetailService.insertChengJiDetail(cj);
            if(jieguo) {
            	System.out.println("��ӳɹ�~~~~~~(^��^)~~~~~~");
            	System.out.println("���������������һ��kemu,ȡ���뷵����һ��˵�������0");
            	//����ͳ���ֺܷ�ƽ����
            	chengJiService.updateChengJiTotalAndAvg();
            	flag=zhuangtai1;
            }else {
            	System.out.println("���ʧ��~~~~~~(�s��t)~~~~~~");
            	System.out.println("���������������һ��kemu,ȡ���뷵����һ��˵�������0");
            }
        	
        }
    }

	//����Ŀ�������Ƿ�淶
	public boolean checkKemu(String kemu) {
		if(kemu.isEmpty()) {
			System.out.println("���벻��Ϊ��");
			return false;
		}else if(kemu.length()>20) {
			System.out.println("�����kemu�����Գ���20���ַ�");
			return false;
		}else if(kemu.length()<2) {
			System.out.println("�����kemu������С��2���ַ�");
			return false;
		}else {
			cj.setKemu(kemu);
        //����kemu��ѯ����ɼ��������ݿ������Ƿ��Ѿ�����
        cjList=chengJiDetailService.queryChengJiDetailByKemu(cj);
        if(cjList==null||cjList.size()==0) {
        	return true;
        }else {
        	System.out.println("kemuΪ"+kemu+"�ĳɼ����Ѿ�����,�뻻������");
        	return false;
        }
        }
	}
	
	//�������������Ƿ�淶
	public boolean checkScore(int score) {
		if(score<0||score>100) {
			System.out.println("�����ķ�ΧΪ0-100��.");
			return false;
		}
		return true;
	}
}
