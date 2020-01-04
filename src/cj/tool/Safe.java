package cj.tool;

//这是一个用于给字符串加密的工具类
//经测试,本加密方法只可用于英文和数字,不可用于中文
public class Safe {
	
	//经研究发现加密参数不可以超过5,只能取1-5.更换加密参数可以认为是换了一种加密方式.
	public final static int jiaMiCanShu = 5;

	//用于加密的方法
	public static String jiaMi(String yuanWen){
		byte[] yuanWenArr = yuanWen.getBytes();
		StringBuilder miWen = new StringBuilder();//容易改变的字符串；
		
		for(int i=0;i<yuanWenArr.length;i++) {
			miWen.append((char)(yuanWenArr[i]+jiaMiCanShu));
		}
		return miWen.toString();
	}
	//用于解密的方法
	public static String jieMi(String miWen){
		byte[] miWenArr = miWen.getBytes();
		StringBuilder yuanWen = new StringBuilder();
		
		for(int i=0;i<miWenArr.length;i++) {
			yuanWen.append((char)(miWenArr[i]-jiaMiCanShu));
		}
		return yuanWen.toString();
	}
	
	//测试加密算法
	public static void main(String[] args) {
		String test1 = "qwertyuiopasdfghjklzxcvbnm1234567890_+-*/";
		String test2 = jiaMi(test1);
		String test3 = jieMi(test2);
		System.out.println(test2);
		System.out.println(test3);
	}
}
