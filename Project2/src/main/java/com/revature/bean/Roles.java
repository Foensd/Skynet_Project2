package com.revature.bean;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;


@Component
@Entity
@Table(name ="ROLES")
@XmlRootElement
public class Roles {
	
	@Id
	@Column(name="R_Id")
	@SequenceGenerator(name = "RID_SEQ", sequenceName = "RID_SEQ") 
	private int roleId;
	
	@Column(name="R_Desc")
	private String description;
	

	public Roles() {
		super();
	}
	public Roles(int roleId, String description) {
		super();
		this.roleId = roleId;
		this.description = description;
	}
	public Roles(int roleId) {
		super();
		this.roleId = roleId;	}
	public Roles(String description) {
		super();
		this.description = description;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

	@Override
	public String toString() {
		return "Roles [roleId=" + roleId + ", description=" + description + "]";
	}
	
	
	
	

}
