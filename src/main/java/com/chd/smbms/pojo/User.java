package com.chd.smbms.pojo;

import java.io.Serializable;
import java.util.Date;
/**
 * 用户实体类
 * @author LEMon
 */
public class User implements Serializable{
	
	private Integer id;//用户id
	 private String userCode;//用户编码
	 private String userName;//用户名称
	 private String userPassword;//用户密码
	 private Integer gender;//性别（1:女，2:男）
	 private String address;//地址
	 private Date birthday;//出生日期
	 private String phone;//手机号码
	 private Integer createdBy;//创建者
	 private Date creationDate;//创建时间
	 private Integer modifyBy;//修改者
	 private Date modifyDate;//修改时间
	 private Integer userRole;//用户角色（取自角色表-角色id）
	 
	 private String userRoleName;
	 private Integer age;
	 
	public String getUserRoleName() {
		return userRoleName;
	}
	public void setUserRoleName(String userRoleName) {
		this.userRoleName = userRoleName;
	}
	public Integer getAge() {
		if (this.birthday == null) {
			return 0;
		}
		return (int) ((System.currentTimeMillis()-this.birthday.getTime())/1000/60/60/24/365);
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Integer getUserRole() {
		return userRole;
	}
	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
	}
	 
	 

}
