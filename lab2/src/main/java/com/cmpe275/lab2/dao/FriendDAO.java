/**
 * 
 */
package com.cmpe275.lab2.dao;

import org.springframework.stereotype.Repository;


@Repository
public interface FriendDAO {
	/**
	 * create friend
	 */
	public void create();
	
	/**
	 * delete friend
	 */
	public void delete();

}
