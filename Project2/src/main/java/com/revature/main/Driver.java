package com.revature.main;

import java.util.ArrayList;
import java.util.List;

import com.revature.bean.Users;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.service.Register;

//import java.util.List;

//import com.revature.bean.Roles;
//import com.revature.bean.Users;
//import com.revature.dao.RoleDao;
//import com.revature.dao.RoleDaoImp;
//import com.revature.dao.StatusDao;
//import com.revature.dao.StatusDaoImpl;
//import com.revature.dao.UserDao;
//import com.revature.dao.UserDaoImpl;

public class Driver {

	public static void main(String[] args) {
		/*UserDao dao = new UserDaoImpl();
		RoleDao daor = new RoleDaoImp();
		StatusDao daos = new StatusDaoImpl();*/
		
		String jsonObject = null;
		/*int r = daor.selectRoleId("HR");
		
		System.out.println(r);*/
		
		
		/*Status s = daos.selectStatusById(1);
		System.out.printsln(s);*/
		
		
		
		/*RoleAssig r = new RoleAssig();
		r.assignRandomRoles();*/
//		Register r = new Register();
//		UserDao dao = new UserDaoImpl();
//		List<Users> users = dao.getUsers();
//		List<String> usernames = new ArrayList<String>();
//		for(Users user: users) {
//			if(user.getRole().getDescription().equals("Hacker")) {
//				usernames.add(user.getTarget());
//			}
//		}
//		int chosen = (int)(Math.random() * usernames.size());
//		dao.changeStatusByUsername(2, usernames.get(chosen));
//		
//		for(Users user: users) {
//			if(user.getRole().getDescription().equals("Trainer")) {
//				if(dao.getUserByUsername(user.getTarget()).getRole().getDescription().equals("Hacker")) {
//					//Message Trainer "You fired a hacker"
//					dao.changeStatusByUsername(2, user.getTarget());
//				} else {
//					//Message Trainer "Your target wasn't a hacker"
//				}
//			}
//		}
//		
//		for(Users user: users) {
//			if(user.getRole().getDescription().equals("HR")) {
//				if(dao.getUserByUsername(user.getTarget()).getStatus().getDescription().equals("Fired")) {
//					//Message HR that they saved somebody
//					dao.changeStatusByUsername(1, user.getTarget());
//				} else {
//					//HR's target wasn't fired
//				}
//			}
//		}
//		
//		
	}

}
