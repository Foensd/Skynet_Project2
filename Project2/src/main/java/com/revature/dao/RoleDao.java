package com.revature.dao;

import com.revature.bean.Roles;

public interface RoleDao {
	
	public Roles selectRoleById(int id);
	public int selectRoleId(String role);
}
