package com.revature.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.bean.Roles;
import com.revature.bean.Status;
import com.revature.bean.Users;
import com.revature.util.HibernateUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public void createUser(String name, Roles role) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		Users user = new Users(name, role , new Status("Alive"));
		session.save(user.getStatus());
		session.save(user.getRole());
		session.save(user);
		
		tx.commit();

		session.close();
	}

}
