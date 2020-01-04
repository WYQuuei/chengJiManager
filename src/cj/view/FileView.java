package cj.view;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

import cj.tool.FileTool;

public class FileView implements operation {

	private final static String zhuangtai1="准备输入文件名";
	private final static String zhuangtai2="准备输入文件路径";
	private final static String zhuangtai3="准备生成文件";
	
	String flag = zhuangtai1;
	Scanner scan = new Scanner(System.in);
	//默认路径
	private String defaultPath = "C:\\chengji\\file\\";
	private String defaultName = "";
	private FileTool ft;
	
	//用构造函数来将[默认文件名],[文件正文]传进来.
	public FileView (FileTool ft) {
		this.ft=ft;
	}
	
	@Override
	public String operation() throws InputMismatchException {
		
        while(true){
        	//输入文件名
        	if(zhuangtai1.equals(flag)) {
        		System.out.println("请输入新文件名，使用系统默认的文件名请输入1,返回上一层菜单请输入0:");
        		String name = scan.next();
        		if("0".equals(name))
        			return "登录成功状态";
        		else if("1".equals(name))
        			flag=zhuangtai2;
        		else if(checkFileName(name)) {
        			ft.setFileName(name);
        			flag=zhuangtai2;
        		}
        	}
        	
        	//输入路径
        	if(zhuangtai2.equals(flag)) {
        		System.out.println("请输入文件路径，使用系统默认的路径(C:\\chengji\\file\\)请输入1,返回上一层菜单请输入0:");
        		String path = scan.next();
        		if("0".equals(path))
        			return "登录成功状态";
        		else if("1".equals(path))
        			flag=zhuangtai3;
        		else if(checkPath(path)) {
        			ft.setPath(path);
        			flag=zhuangtai3;
        		}
        		
        	}
        	
        	//生成文件
        	if(zhuangtai3.equals(flag)) {
        		boolean jieguo=ft.outFile();
        		if(jieguo)
        			System.out.println("生成文件成功~~~~~~(^ω^)~~~~~~");
            	else
            		System.out.println("生成文件失败~~~~~~(╯︿╰)~~~~~~");
    			flag=zhuangtai1;
    			return "登录成功状态";
        	}
		}
	}
	
	public boolean checkFileName(String fileName) {
		if(fileName ==null||fileName.length()==0) {
			System.out.println("文件名不能为空");
			return false;
		}else if(fileName.length()<4) {
			System.out.println("文件名不能小于4个字符");
			return false;
		}else if(fileName.length()>20) {
			System.out.println("文件名不能大于20个字符");
			return false;
		}
		return true;
	}
	
	//检查路径输入是否正确
	public boolean checkPath(String path) {
		try {
			//判断路径是否存在
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