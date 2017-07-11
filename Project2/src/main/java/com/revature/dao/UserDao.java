package com.revature.dao;



import com.revature.bean.Roles;
import com.revature.bean.Status;

public interface UserDao {

	public void createUser(String name, Roles role);
}
