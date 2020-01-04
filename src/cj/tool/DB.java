package cj.tool;
import java.sql.*;

//这是一个用于连接MySQL的工具类
public class DB {
	
	private Connection con;
	private PreparedStatement pre;
	private ResultSet rs;
	private static DB db;
	
	// l数据库连�?
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private DB(){

	}
	

	public static DB getInstance(){
		if(db==null){
			db = new DB();
		}
		return db;
	}
	
	private void buildConnection(){
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useUnicode=true&characterEncoding=UTF-8", "root", "123");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ResultSet executeQueryByParam(String sql, Object...objects){
		
		this.buildConnection();
		try {
			pre = con.prepareStatement(sql);
			for(int i = 0; i<objects.length; i++){
				pre.setObject(i+1, objects[i]);
			}
			rs = pre.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return rs;
		
	}
	
	public ResultSet executeQueryNoParam(String sql){
		this.buildConnection();
		try {
			pre = con.prepareStatement(sql);
			rs = pre.executeQuery();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
		
	}
	
	public int executeUpdate(String sql, Object...objects){
		this.buildConnection();
		try {
			pre = con.prepareStatement(sql);
			for(int i =0; i<objects.length; i++){
				pre.setObject(i+1, objects[i]);
			}
			int count = pre.executeUpdate();
			return count;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();		
			return 0;
		}
	}
	// 关闭数据库释放内�?
	public void close(){
			try {
				if(rs!=null)
					rs.close();
				pre.close();
				con.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	
	
	
}
