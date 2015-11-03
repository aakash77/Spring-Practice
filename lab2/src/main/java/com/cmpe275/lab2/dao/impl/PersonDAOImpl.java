/**
 * 
 */
package com.cmpe275.lab2.dao.impl;

import java.io.Serializable;

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

	public Person update(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	public Person delete(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
