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
	 * Create new organization DAO implementation
	 */
	public Organization create(Organization organization) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			session.save(organization);
			tx.commit();
		}catch(HibernateException e){
			tx.rollback();
		}finally{
			session.close();
		}
		return organization;
	}

	/**
	 * Read organization details DAO implementation
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
	 * Update organization DAO implementation
	 */
	public Organization update(Organization organization) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			session.update(organization);
			tx.commit();
		}catch(HibernateException h){
			tx.rollback();
			organization=null;
		}finally{
			session.close();
		}
		return organization;
	}

	/** 
	 * Delete organization DAO implementation
	 */
	public Organization delete(Organization organization) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		try{
			session.delete(organization);
			tx.commit();
		}catch(HibernateException h){
			tx.rollback();
			organization=null;
		}finally{
			session.close();
		}
		return organization;
	}

}
