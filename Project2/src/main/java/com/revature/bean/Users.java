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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Users")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "myAwesomeCache")
public class Users {

	@Id
	@Column(name = "U_Id")
	@SequenceGenerator(name = "UID_SEQ", sequenceName = "UID_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UID_SEQ")
	private int userid;
	
	@NotEmpty(message="Username cannot be empty")
	@Size(min=4,max=20)
	@Column(name = "Username")
	private String username;
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Role_ID")
	private Roles role;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Status_ID")
	private Status status;

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
		return "Users [userid=" + userid + ", username=" + username + ", roleId=" + role + ", statusId=" + status
				+ "]";
	}

}
