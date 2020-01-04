package cj.encapsulation;

import cj.tool.Safe;

//����װ[������Ϣ]������
public class Login {
	//����ǰ�Ĳ���
	private String username;
	private String password;
	//���ܺ�Ĳ���
	private String usernameMi;
	private String passwordMi;
	
	public String getUsername(){
		return username;
	}
	
	public void setUsername(String username){
		this.username = username;
		//�Զ�����
		this.usernameMi = Safe.jiaMi(username);
	}
	
	public String getPassword(){
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
		//�Զ�����
		this.passwordMi = Safe.jiaMi(password);
	}

	public String getUsernameMi() {
		return usernameMi;
	}

	public void setUsernameMi(String usernameMi) {
		this.usernameMi = usernameMi;
		//�Զ�����
		this.username = Safe.jieMi(usernameMi);
	}

	public String getPasswordMi() {
		return passwordMi;
	}

	public void setPasswordMi(String passwordMi) {
		this.passwordMi = passwordMi;
		//�Զ�����
		this.password = Safe.jieMi(passwordMi);
	}
	
}
