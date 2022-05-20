package com.ssafy.happyhouse.model.dto;

public class UserDto {
	
	private String id;
	private String pass;
	private String name;
	private String email;
	private String phone;
	private int isAdmin;
	
	public UserDto() {
		super();
	}
	
	public UserDto(String id, String pass, String name, String email, String phone, int isAdmin) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.isAdmin = isAdmin;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", pass=" + pass + ", name=" + name + ", email=" + email + ", phone=" + phone
				+ ", isAdmin=" + isAdmin + "]";
	}
	
}
