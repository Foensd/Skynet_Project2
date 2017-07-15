package com.revature.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="users")
public class ListUsers {
	@XmlElement(required = true)
	public List<Users> users;

	public ListUsers() {
	} // JAXB needs this

	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}


}
