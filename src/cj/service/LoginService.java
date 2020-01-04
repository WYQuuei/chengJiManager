package cj.service;

import java.sql.*;

import cj.encapsulation.Login;
import cj.tool.DB;

// 登入表的处理
public class LoginService {

	private DB db;
	private static LoginService service;
	
	private LoginService(){
		db = DB.getInstance();
	}
	
	public static LoginService getInstantnce(){
		if(service == null){
			service = new LoginService();
		}
		
		return service;
		
	}
	
	//登录校验
	public boolean login(Login login){
		String sql = "select * from t_login where username = ? and password = ?";
	    ResultSet rs = db.executeQueryByParam(sql,login.getUsernameMi(),login.getPasswordMi());
		try {
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return false;
    }
	
	//注册
	public boolean register(Login register) {
        String sql = "insert into t_login(username,password) values(?,?) ";
        int count = db.executeUpdate(sql, register.getUsernameMi(),register.getPasswordMi());
        db.close();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

	//注册时用加密的username检查用户名是否已经被占用.
	public boolean queryUserByName(String username){
		String sql = "select username from t_login where username = ? ";
		ResultSet rs = db.executeQueryByParam(sql,username);
		try {
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return false;
    }

}
