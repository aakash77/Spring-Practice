/**
 * 
 */
package com.cmpe275.lab2.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.cmpe275.lab2.dao.OrganizationDAO;
import com.cmpe275.lab2.model.Organization;

public class OrganizationDAOImpl implements OrganizationDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	/** 
	 * 
	 */
	public Organization create(Organization organization) {
		return organization;
		// TODO Auto-generated method stub

	}

	/** 
	 * 
	 */
	public Organization read(long id) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		Organization organization = null;
		try{
			organization = (Organization) session.get(Organization.class, id);
			tx.commit();
		}catch(HibernateException h){
			tx.rollback();
		}
		finally{
			session.close();
		}
		return organization;
	}

	/** 
	 * 
	 */
	public Organization update(Organization organization) {
		// TODO Auto-generated method stub
		return null;
	}

	/** 
	 * 
	 */
	public Organization delete(Organization organization) {
		// TODO Auto-generated method stub
		return null;
	}

}
