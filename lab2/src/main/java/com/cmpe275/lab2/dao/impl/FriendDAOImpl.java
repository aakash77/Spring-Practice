package com.cmpe275.lab2.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import com.cmpe275.lab2.dao.FriendDAO;
import com.cmpe275.lab2.model.Person;

public class FriendDAOImpl implements FriendDAO {
	
	
	@Autowired
	SessionFactory sessionFactory;
	
	
	/**
	 *Method to update person object with new added friend 
	 */
	public void create(Person person) {
		Session session = sessionFactory.openSession();
		Transaction tx =  session.beginTransaction();
		try{
			session.update(person);
			tx.commit();
		}
		catch(Exception h){
			tx.rollback();
		}finally{
			session.close();
		}
	}
	
	/**
	 * Method to update person in-order to delete friends 
	 */
	public void delete(Person person) {
		
		Session session = sessionFactory.openSession();
		Transaction tx =  session.beginTransaction();
		try{
			session.update(person);
			tx.commit();
		}
		catch(Exception h){
			tx.rollback();
		}finally{
			session.close();
		}
	}
}