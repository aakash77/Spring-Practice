/**
 * 
 */
package com.cmpe275.lab2.service;

import org.springframework.stereotype.Service;


@Service
public interface FriendService {
	/**
	 * create friend
	 */
	public String create(long id1,long id2);
	
	/**
	 * delete friend
	 */
	public String delete(long id1,long id2);

}
