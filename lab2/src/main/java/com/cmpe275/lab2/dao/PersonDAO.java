/**
 * 
 */
package com.cmpe275.lab2.dao;

import com.cmpe275.lab2.model.Organization;
import com.cmpe275.lab2.model.Person;

public interface PersonDAO {
	/**
	 * create a new person entry
	 * @param person
	 * @return person 
	 */
	public Person create(Person person);
	
	/**
	 * read person's details
	 * @param id
	 * @return person 
	 */
	public Person read(long id);
	
	
	/**
	 * update person's details
	 * @param person
	 * @return person
	 */
	public Person update(Person person);
	
	/**
	 * delete person entry
	 * @param person
	 * @return person
	 */
	public Person delete(Person person);
	
	/**
	 * get person by organization
	 * @param organization
	 * @return person
	 */
	public Person getPersonByOrganization(Organization organization);

}
