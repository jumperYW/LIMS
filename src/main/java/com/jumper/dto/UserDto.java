package com.jumper.dto;

public class UserDto {
	
	private Integer id;
	private String userid;
	private String name;
	private String password;
	private byte authority;
	private String tel;
	private byte sex;
	private String department;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public byte getAuthority() {
		return authority;
	}
	public void setAuthority(byte authority) {
		this.authority = authority;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public byte isSex() {
		return sex;
	}
	public void setSex(byte sex) {
		this.sex = sex;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public UserDto() {}
	
	public UserDto(Integer id, String userid, String name, String password, byte authority, String tel, byte sex,
			String department) {
		super();
		this.id = id;
		this.userid = userid;
		this.name = name;
		this.password = password;
		this.authority = authority;
		this.tel = tel;
		this.sex = sex;
		this.department = department;
	}
	
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", userid=" + userid + ", name=" + name + ", password=" + password + ", authority="
				+ authority + ", tel=" + tel + ", sex=" + sex + ", department=" + department + "]";
	}
	
}
