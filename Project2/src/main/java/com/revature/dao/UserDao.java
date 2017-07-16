package com.revature.dao;



import java.util.List;

import com.revature.bean.Roles;
import com.revature.bean.Users;

public interface UserDao {

	public boolean createUser(String name, Roles role);
	public List<Users> getUsers();
	public boolean updateRoleById(int id, String role);
	public void changeStatusByUsername(int status, String username);
	public Users getUserByUsername(String username);
	public void updateUserTarget(String username, String target);
	public List<Users> getImportantUsers();
	public void deleteTargets();
	public List<Users> getActiveUsers();
}
