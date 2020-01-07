package cj.view.detailView;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import cj.encapsulation.ChengJi;
import cj.encapsulation.ChengJiDetail;
import cj.view.operation;

public class ModifyDetailView implements operation{

	final static String zhuangtai1="׼������id";
	final static String zhuangtai2="׼������kemu";
	final static String zhuangtai3="׼������score";
	final static String zhuangtai4="׼�����³ɼ�����";
	String flag = zhuangtai1;
	List<ChengJiDetail> cjdList= null;
	ChengJiDetail cjd = new ChengJiDetail();
	
	//��ʼ��
	public ModifyDetailView(ChengJi cj) {
		cjd.setChengJi(cj);
	}
	
    @Override
    public String operation() throws InputMismatchException {
        Scanner scan = new Scanner(System.in);
        while(true){
        	if(zhuangtai1.equals(flag)) {
        		System.out.println("������Ҫ�޸ĵĳɼ���id,������һ��˵�������0:");
        		int id = scan.nextInt();
        		if (id==0) 
        			return "����id��ѯ�ɼ�������";
        		else if(checkId(id))
            		flag=zhuangtai2;
        	}
    		//�޸�kemu����
        	if(zhuangtai2.equals(flag)) {
        		System.out.println("���������id��Ҫ�޸ĵĵĿ�Ŀ����,���޸�������9,������һ��˵�������0:");
            	String kemu = scan.next();
            	if ("0".equals(kemu))
            		return "����id��ѯ�ɼ�������";
            	else if("9".equals(kemu))
            		flag=zhuangtai3;
            	else {
            		if (checkKemu(kemu)) {
                		//���� kemu
                		cjd.setKemu(kemu);
                		flag=zhuangtai3;
            		}else
            			continue;
            	}
        	}
        	//�޸�score����
        	if(zhuangtai3.equals(flag)) {
        		System.out.println("������Ҫ�޸ĵĳɼ����ķ���,������һ��˵�������0:");
            	int score = scan.nextInt();
            	if (0==score)
            		return "����id��ѯ�ɼ�������";
            	else if("9".equals(score))
            		flag = zhuangtai4;
            	else if (checkScore(score)) {
                	//���� score
                	cjd.setScore(score);
                	flag = zhuangtai4;
            	}else
            		continue;
        	}
        	
            //ִ��service��sql
            if(zhuangtai4.equals(flag)) {
            	boolean rs=chengJiDetailService.updateChengJiDetail(cjd);
            	if(rs) {
            		System.out.println("�޸ĳɹ�~~~~~~(^��^)~~~~~~");
                	//����ͳ���ֺܷ�ƽ����
                	chengJiService.updateChengJiTotalAndAvg();
            		flag=zhuangtai1;
            	}else {
            		System.out.println("�޸�ʧ��~~~~~~(�s��t)~~~~~~");
            		flag=zhuangtai1;
            	}
        	}
        }
    }

    //�淶id������
  	public boolean checkId(int id) {
  		ChengJiDetail cjdTemp = null;
        //����id��ѯ����ɼ��������ݿ����Ϣ
  		cjdTemp=chengJiDetailService.queryOneChengJiDetailByID(id);
          if(cjdTemp==null) {
        	System.out.println("idΪ"+id+"�ĳɼ����鲻����");
          	return false;
          }else {
        	  //����������id��[�ɼ�����]��Ϣ,����Ĭ�Ͽ�Ŀ��������Ϣ
        	  cjd.setId(id);
          	  cjd.setKemu(cjdTemp.getKemu());
          	  cjd.setScore(cjdTemp.getScore());
          	return true;
          }
  	}
  	
    //�淶��Ŀ������
	public boolean checkKemu(String kemu) {
		if(kemu.isEmpty()) {
			System.out.println("���벻��Ϊ��");
			return false;
		}
		else if(kemu.length()>20) {
			System.out.println("�����kemu�����Գ���20���ַ�");
			return false;
		}else if(kemu.length()<2) {
			System.out.println("�����kemu������С��2���ַ�");
			return false;
		}else {
			cjd.setKemu(kemu);
        //����kemu��ѯ����ɼ��������ݿ������Ƿ��Ѿ�����
        cjdList=chengJiDetailService.queryChengJiDetailByKemu(cjd);
        if(cjdList==null||cjdList.size()==0) {
        	return true;
        }else {
        	System.out.println("kemuΪ"+kemu+"�Ŀ�Ŀ�ɼ��Ѿ�����,�뻻����Ŀ����");
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
