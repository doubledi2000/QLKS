package com.domain;

public class LoginForm {
	private String account, password;
	private boolean remember;
	
	public LoginForm() {
	}
	public LoginForm(String account, String password, boolean remember) {
		this.account = account;
		this.password = password;
		this.remember = remember;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isRemember() {
		return remember;
	}
	public void setRemember(boolean remember) {
		this.remember = remember;
	}
	
	
	
	
}
