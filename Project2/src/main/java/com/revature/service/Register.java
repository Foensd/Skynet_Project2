package com.revature.service;

import com.revature.dao.RoleDao;
import com.revature.dao.RoleDaoImp;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;

public class Register {
	
	public boolean createUser(String name){
		UserDao dao = new UserDaoImpl();
		
		if(dao.createUser(name, null)){
			
			return true;
		}
		
		return false;
	}

}
