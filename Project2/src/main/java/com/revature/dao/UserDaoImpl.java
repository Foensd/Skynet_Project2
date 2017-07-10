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
		
		StatusDao daos = new StatusDaoImpl();
		
		Users user = new Users(name, role , daos.selectStatusById(1));
		session.save(user);
		
		tx.commit();

		session.close();
	}

}
