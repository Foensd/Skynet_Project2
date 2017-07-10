package com.revature.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import com.revature.bean.Roles;
import com.revature.util.HibernateUtil;

public class RoleDaoImp implements RoleDao {

	@Override
	public Roles selectRoleById(int id) {
		
		Session session = HibernateUtil.getSession();
		Query query;
		String hql;
		
		hql = "FROM com.revature.bean.Roles WHERE R_Id = :id";
		query = session.createQuery(hql);
		query.setParameter("id", id);
		
		Roles r =  (Roles) query.uniqueResult();
		session.close();
		return r;
		

		
	}

}
