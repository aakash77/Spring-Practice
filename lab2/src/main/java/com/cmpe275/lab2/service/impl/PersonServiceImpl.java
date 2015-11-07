package com.cmpe275.lab2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmpe275.lab2.dao.PersonDAO;
import com.cmpe275.lab2.model.Person;
import com.cmpe275.lab2.service.PersonService;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonDAO personDAO;
	
	/**
	 * create person service method 
	 */
	public Person create(Person person) {
		return personDAO.create(person);
	}
	
	/**
	 * read person service method
	 */
	public Person read(long id) {
		return personDAO.read(id);
	}
	
	/**
	 * update person service method
	 */
	public Person update(Person person) {
		return personDAO.update(person);
	}
	
	/**
	 * delete person service method
	 */
	public Person delete(long id) {
		Person person = personDAO.read(id);
		if(person==null)
			return null;
		return personDAO.delete(person);
	}
}