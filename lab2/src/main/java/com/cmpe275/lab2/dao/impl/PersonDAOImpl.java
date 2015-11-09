/**
 * 
 */
package com.cmpe275.lab2.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cmpe275.lab2.dao.PersonDAO;
import com.cmpe275.lab2.model.Organization;
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
		catch(Exception h){
			tx.rollback();
			person=null;
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
			String sql = "DELETE FROM FRIENDSHIP WHERE friend_id = :friend_id";
			Query query = session.createSQLQuery(sql);
			query.setParameter("friend_id", person.getId());
			query.executeUpdate();
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
	 * check if any person connected to the organization
	 */
	public Person getPersonByOrganization(Organization organization) {
		Session session = sessionFactory.openSession();
		Transaction tx =  session.beginTransaction();
		List<Person> personList;
		Person person=null;
		try{
			String hql = "FROM com.cmpe275.lab2.model.Person as person WHERE person.organization = :organization";
			Query query = session.createQuery(hql);
			query.setEntity("organization", organization);
			query.setMaxResults(1);
			
			personList = query.list();
			
			if(personList.size()!=0)
				person = personList.get(0);
			
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