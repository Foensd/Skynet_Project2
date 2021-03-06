package com.revature.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;
//import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Users")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "myAwesomeCache")
@XmlRootElement
public class Users {

	

	@Id
	@Column(name = "U_Id")
	@SequenceGenerator(name = "UID_SEQ", sequenceName = "UID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UID_SEQ")
	private int userid;
	
	@NotEmpty(message="Username cannot be empty(1-20 characters)")
	@Size(min=1,max=20)
	@Column(name = "Username")
	private String username;
	

	public Users() {
		super();
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Role_ID")
	private Roles role;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Status_ID")
	private Status status;
	
	@Column
	private String targetUser; 

	public String getTargetUser() {
		return targetUser;
	}

	public void setTargetUser(String targetUser) {
		this.targetUser = targetUser;
	}

	public Users(String username) {
		super();
		this.username = username;
	}
	
	public Users(Users user) {
		super();
		this.userid = user.getUserid();
		this.username = user.getUsername();
		this.role = user.getRole();
		this.status = user.getStatus();
		this.targetUser = user.getTargetUser();
	}
	
	public Users(int userid, String username, Roles role, Status status, String targetUser) {
		super();
		this.userid = userid;
		this.username = username;
		this.role = role;
		this.status = status;
		this.targetUser = targetUser;
	}

	public Users(int userid, String username, Roles roleId, Status statusId) {
		super();
		this.userid = userid;
		this.username = username;
		this.role = roleId;
		this.status = statusId;
	}

	public Users(String username, Roles roleId, Status statusId) {
		super();
		this.username = username;
		this.role = roleId;
		this.status = statusId;
	}
	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Status getStatusId() {
		return status;
	}

	public void setStatusId(Status status) {
		this.status = status;
	}
	
	

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Users [userid=" + userid + ", username=" + username + ", role=" + role + ", status=" + status
				+ ", targetUser=" + targetUser + "]";
	}

}
