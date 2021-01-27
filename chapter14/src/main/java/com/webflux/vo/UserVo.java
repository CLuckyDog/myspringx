package com.webflux.vo;

public class UserVo {
	private String id;
	private String userName;
	private int sexCode;
	private String sexName;
	private String note;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getSexCode() {
		return sexCode;
	}

	public void setSexCode(int sexCode) {
		this.sexCode = sexCode;
	}

	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}