package cj.tool;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileTool {

	private String fileName;
	private String path = "C:\\chengji\\file\\";
	private String fileText;
	
	public  boolean outFile() {
		BufferedWriter bufferedWriter = null;
    	try{
    		File file = new File(path+fileName);
            //判断目标文件是否存在.
    		if (!file.getParentFile().exists()) {
    		file.getParentFile().mkdirs();
    		}
    		file.createNewFile();
            // 获取该文件的缓冲输出流
            bufferedWriter = new BufferedWriter
            		(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            // 写入信息
            bufferedWriter.write(fileText);//genjuzhe
    		return true;
    	}catch (IOException e) {
    		e.printStackTrace();
    		return false;
    	}finally {
    		try {
				bufferedWriter.flush();// 清空缓冲区
				bufferedWriter.close();// 关闭输出流
			} catch (IOException e) {
				e.printStackTrace();
			}
    		
    	}
		
	}
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getFileText() {
		return fileText;
	}
	public void setFileText(String fileText) {
		this.fileText = fileText;
	}
	
}
