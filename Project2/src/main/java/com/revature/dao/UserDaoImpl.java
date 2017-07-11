package com.revature.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.bean.Roles;
import com.revature.bean.Status;
import com.revature.bean.Users;
import com.revature.util.HibernateUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean createUser(String name, Roles role) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();

			StatusDao daos = new StatusDaoImpl();

			Users user = new Users(name, role, daos.selectStatusById(1));
			session.save(user);

			System.out.println(user);

			tx.commit();

			session.close();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<Users> getUsers() {
		Session session = HibernateUtil.getSession();
		Query query;
		String hql;
		Transaction tx;
		
		hql = "FROM com.example.bean.Users";
		query = session.createQuery(hql);
		List<Users> users = query.list(); //list executes the query and returns results
		return users;
	}

	/*@Override
	public boolean updateRoleById(int id, String role) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();

			StatusDao daos = new StatusDaoImpl();

			Users user = new Users(name, role, daos.selectStatusById(1));
			session.save(user);

			System.out.println(user);

			tx.commit();

			session.close();

			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}*/

}
