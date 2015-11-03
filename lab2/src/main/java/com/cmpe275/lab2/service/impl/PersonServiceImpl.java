package com.cmpe275.lab2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cmpe275.lab2.dao.PersonDAO;
import com.cmpe275.lab2.model.Person;
import com.cmpe275.lab2.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService {

	@Autowired
	PersonDAO personDAO;
	
	public Person create(Person person) {
		return personDAO.create(person);
	}

	public Person read(long id) {
		return personDAO.read(id);
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
