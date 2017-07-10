package com.revature.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.bean.Status;
import com.revature.util.HibernateUtil;

public class StatusDaoImpl implements StatusDao{

	@Override
	public Status selectStatusById(int id) {
		Session session = HibernateUtil.getSession();
		Query query;
		String hql;
		
		hql = "FROM com.revature.bean.Status WHERE S_ID = :id";
		query = session.createQuery(hql);
		query.setParameter("id", id);
		
		Status s =  (Status) query.uniqueResult();
		session.close();
		
		return s;
	}

}
