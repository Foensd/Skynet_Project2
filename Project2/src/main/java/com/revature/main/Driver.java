package com.revature.main;



import com.revature.bean.Status;
import com.revature.dao.RoleDao;
import com.revature.dao.RoleDaoImp;
import com.revature.dao.StatusDao;
import com.revature.dao.StatusDaoImpl;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;

public class Driver {

	public static void main(String[] args) {
		UserDao dao = new UserDaoImpl();
		RoleDao daor = new RoleDaoImp();
		StatusDao daos = new StatusDaoImpl();
		
		
		/*Roles r = daor.selectRoleById(1);
		
		System.out.println(r);
		*/
		
		/*Status s = daos.selectStatusById(1);
		System.out.println(s);*/
		
		dao.createUser("Jonathan", daor.selectRoleById(3));
		
	}

}
