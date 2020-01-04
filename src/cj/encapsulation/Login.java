package cj.encapsulation;

import cj.tool.Safe;

//用来装[登入信息]的容器
public class Login {
	//加密前的参数
	private String username;
	private String password;
	//加密后的参数
	private String usernameMi;
	private String passwordMi;
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username){
		this.username = username;
		//自动加密
		this.usernameMi = Safe.jiaMi(username);
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
		//自动加密
		this.passwordMi = Safe.jiaMi(password);
	}

	public String getUsernameMi() {
		return usernameMi;
	}

	public void setUsernameMi(String usernameMi) {
		this.usernameMi = usernameMi;
		//自动解密
		this.username = Safe.jieMi(usernameMi);
	}

	public String getPasswordMi() {
		return passwordMi;
	}

	public void setPasswordMi(String passwordMi) {
		this.passwordMi = passwordMi;
		//自动解密
		this.password = Safe.jieMi(passwordMi);
	}
	
}
