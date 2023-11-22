package com.demo.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.ElementCollection;

@Entity
@Table
public class User implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7223245362061138198L;
	
	@Id
	@GeneratedValue
	private Long userId;
	
	private String userName;
	
	//@ManyToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER)
	//private Set<Role> roles;
	
	@ElementCollection(targetClass = Long.class, fetch = FetchType.EAGER)
	private List<Long> roleIdList;
	
	private String password;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/*
	 * public Set<Role> getRoles() { return roles; }
	 * 
	 * public void setRoles(Set<Role> roles) { this.roles = roles; }
	 */

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Long> getRoleIdList() {
		return roleIdList;
	}

	public void setRoleIdList(List<Long> roleIdList) {
		this.roleIdList = roleIdList;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", roleIdList=" + roleIdList + ", password="
				+ password + "]";
	}


}
