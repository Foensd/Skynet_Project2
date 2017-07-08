package com.revature.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name = "Users")
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY, region = "myAwesomeCache")
public class Users {

	@Id
	@Column(name = "U_Id")
	@SequenceGenerator(name = "UID_SEQ", sequenceName = "UID_SEQ")
	private int userid;

	@Column(name = "Username")
	private String username;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "Role_ID", nullable = false, insertable = false, updatable = false)
	private Roles role;

	@OneToOne(fetch = FetchType.EAGER, optional= false)
	@JoinColumn(name = "Status_ID", nullable = false, insertable = false, updatable = false)
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
