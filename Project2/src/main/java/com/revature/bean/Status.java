package com.revature.bean;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Status")
@XmlRootElement
public class Status {

	@Id
	@Column(name = "S_Id")
	@SequenceGenerator(name = "SID_SEQ", sequenceName = "SID_SEQ")
	private int statusId;

	@Column(name = "Status")
	private String status;

	
	
	public Status() {
		super();
	}

	public Status(int statusId, String status) {
		super();
		this.statusId = statusId;
		this.status = status;
	}
	
	public Status(int statusId) {
		super();
		this.statusId = statusId;
		}
	

	public Status(String status) {
		super();
		this.status = status;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Status [statusId=" + statusId + ", status=" + status + "]";
	}

}
