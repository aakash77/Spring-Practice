/**
 * 
 */
package com.cmpe275.lab2.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cmpe275.lab2.dao.PersonDAO;
import com.cmpe275.lab2.model.Person;

@Repository
public class PersonDAOImpl implements PersonDAO {

	
	@Autowired
	SessionFactory sessionFactory;
	
	/**
	 * Create new person DAO implementation
	 */
	public Person create(Person person) {
		
		Session session = sessionFactory.openSession();
		Transaction tx =  session.beginTransaction();
		try{
			session.save(person);
			tx.commit();
			//Serializable id = session.getIdentifier(person);
			/*return person;*/
		}
		catch(HibernateException h){
			tx.rollback();
		}finally{
			session.close();
		}
		return person;
	}
	
	/**
	 * Read person details DAO implementation
	 */
	public Person read(long id) {
		Session session = sessionFactory.openSession();
		Transaction tx =  session.beginTransaction();
		Person person = null;
		try{
			person = (Person)session.get(Person.class,id);
			tx.commit();
		}
		catch(HibernateException h){
			tx.rollback();
		}finally{
			session.close();
		}
		return person;
	}
	
	/**
	 * Update person DAO implementation
	 */
	public Person update(Person person) {
		Session session = sessionFactory.openSession();
		Transaction tx =  session.beginTransaction();
		try{
			session.update(person);
			tx.commit();
		}
		catch(HibernateException h){
			tx.rollback();
		}finally{
			session.close();
		}
		return person;
	}
	
	/**
	 * Delete person DAO implementation
	 */
	public Person delete(Person person) {
		Session session = sessionFactory.openSession();
		Transaction tx =  session.beginTransaction();
		try{
			session.delete(person);
			tx.commit();
		}
		catch(HibernateException h){
			tx.rollback();
		}finally{
			session.close();
		}
		return person;
	}

	

}
