/**
 * 
 */
package com.cmpe275.lab2.dao;

import org.springframework.stereotype.Repository;

import com.cmpe275.lab2.model.Person;


@Repository
public interface FriendDAO {
	/**
	 * create friend
	 */
	public void create(Person person);
	
	/**
	 * delete friend
	 */
	public void delete(Person person);

}
