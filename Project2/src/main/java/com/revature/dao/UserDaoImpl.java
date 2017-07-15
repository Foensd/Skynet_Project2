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

			//StatusDao daos = new StatusDaoImpl();

			Users user = new Users(name, role, null);
			session.save(user);


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
		//Transaction tx;
		
		hql = "FROM com.revature.bean.Users";
		query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Users> users = query.list(); //list executes the query and returns results
		session.close();
		return users;
	}

	@Override
	public boolean updateRoleById(int id, String role) {
		try {
			Session session = HibernateUtil.getSession();
			Query query;
			String hql;
			Transaction tx = session.beginTransaction();
			RoleDao r = new RoleDaoImp();
			
			hql = "FROM com.revature.bean.Users WHERE U_ID = :id";
			query = session.createQuery(hql);
			query.setParameter("id", id);
			
			Users user =  (Users)query.uniqueResult();
			System.out.println("before " + user);
			user.setRole(r.selectRoleByDesc(role));
			System.out.println("after " + user);
			session.update(user);
			System.out.println("after update " + user);
			tx.commit();
			session.close();
	
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void changeStatusByUsername(int stat, String username) {
		Session session = HibernateUtil.getSession();
		Query query;
		String hql;
		Transaction tx = session.beginTransaction();
		
		StatusDao dao = new StatusDaoImpl();
		Status status = dao.selectStatusById(stat);
		
		hql = "FROM com.revature.bean.Users WHERE Username  = :user";
		query = session.createQuery(hql);
		query.setParameter("user", username);
		
		Users user =  (Users)query.uniqueResult();
		user.setStatus(status);
		session.update(user);
		tx.commit();
		session.close();
		
	}

	@Override
	public Users getUserByUsername(String username) {
		Session session = HibernateUtil.getSession();
		Query query;
		String hql;
		
		hql = "FROM com.revature.bean.Users WHERE Username = :user";
		query = session.createQuery(hql);
		query.setParameter("user", username);
		
		Users user =  (Users) query.uniqueResult();
		session.close();
		return user;
	}

	@Override
	public void updateUserTarget(String username, String target) {
		Session session = HibernateUtil.getSession();
		Query query;
		String hql;
		Transaction tx = session.beginTransaction();
		
		hql = "FROM com.revature.bean.Users WHERE Username = :user";
		query = session.createQuery(hql);
		query.setParameter("user", username);
		
		Users user =  (Users)query.uniqueResult();
		user.setTargetUser(target);
		session.update(user);
		tx.commit();
		session.close();
		
	}

	@Override
	public List<Users> getImportantUsers() {
		Session session = HibernateUtil.getSession();
		Query query;
		String hql;
		
		
		hql = "FROM com.revature.bean.Users WHERE ROLE_ID <> 1";
		query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Users> users = query.list(); //list executes the query and returns results
		session.close();
		return users;
	}

}
