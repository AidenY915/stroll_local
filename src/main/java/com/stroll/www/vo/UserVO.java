package com.stroll.www.vo;

import java.io.Serializable;

public class UserVO implements Serializable{
	private static final long serialVersionUID = -8799908554156269389L;
	private String id;
	private String password;
	private String nickname;
	private String email;
	public UserVO() {}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "{id = " + id + ", password = " + password + " }";
	}
}
