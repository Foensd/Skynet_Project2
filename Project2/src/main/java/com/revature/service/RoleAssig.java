package com.revature.service;

import java.util.List;

import com.revature.bean.Users;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;

public class RoleAssig {
	
	public void assignRandomRoles() {
		UserDao dao = new UserDaoImpl();
		List<Users> users = dao.getUsers();
		
		for( Users u : users){
			System.out.println(u);
		}
		
        if(users.size() < 5) {
            System.out.println("Not enough players");
        } else {
            int numberOfUsers = users.size();
            int index = (int)(Math.random() * users.size());
            dao.updateRoleById(users.get(index).getUserid(), "Trainer");
            users.remove(index);
            
            index = (int)(Math.random() * users.size());
            dao.updateRoleById(users.get(index).getUserid(), "HR");
            users.remove(index);
            
            index = (int)(Math.random() * users.size());
            dao.updateRoleById(users.get(index).getUserid(), "Hacker");
            users.remove(index);
            
            if(numberOfUsers > 6) {
                for(int i=4; i<numberOfUsers; i = i+4) {
                    index = (int)(Math.random() * users.size());
                    dao.updateRoleById(users.get(index).getUserid(), "Hacker");
                    users.remove(index);
                }
            }
            for(int i = 0; i<users.size(); i++) {
                dao.updateRoleById(users.get(i).getUserid(), "Employee");
            }
        }
    }

}
