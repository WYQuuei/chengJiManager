package cj.tool;

//����һ�����ڸ��ַ������ܵĹ�����
//������,�����ܷ���ֻ������Ӣ�ĺ�����,������������
public class Safe {
	
	//���о����ּ��ܲ��������Գ���5,ֻ��ȡ1-5.�������ܲ���������Ϊ�ǻ���һ�ּ��ܷ�ʽ.
	public final static int jiaMiCanShu = 5;

	//���ڼ��ܵķ���
	public static String jiaMi(String yuanWen){
		byte[] yuanWenArr = yuanWen.getBytes();
		StringBuilder miWen = new StringBuilder();//���׸ı���ַ�����
		
		for(int i=0;i<yuanWenArr.length;i++) {
			miWen.append((char)(yuanWenArr[i]+jiaMiCanShu));
		}
		return miWen.toString();
	}
	//���ڽ��ܵķ���
	public static String jieMi(String miWen){
		byte[] miWenArr = miWen.getBytes();
		StringBuilder yuanWen = new StringBuilder();
		
		for(int i=0;i<miWenArr.length;i++) {
			yuanWen.append((char)(miWenArr[i]-jiaMiCanShu));
		}
		return yuanWen.toString();
	}
	
	//���Լ����㷨
	public static void main(String[] args) {
		String test1 = "qwertyuiopasdfghjklzxcvbnm1234567890_+-*/";
		String test2 = jiaMi(test1);
		String test3 = jieMi(test2);
		System.out.println(test2);
		System.out.println(test3);
	}
}
