package com.cmpe275.lab2.service.impl;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmpe275.lab2.dao.FriendDAO;
import com.cmpe275.lab2.dao.PersonDAO;
import com.cmpe275.lab2.model.Person;
import com.cmpe275.lab2.service.FriendService;

@Service
@Transactional
public class FriendServiceImpl implements FriendService {

	@Autowired
	PersonDAO personDAO;

	@Autowired
	FriendDAO friendDAO;


	/**
	 * Service for adding friends
	 */ 
	public String create(long id1, long id2) {
		Person p1 = personDAO.read(id1);
		Person p2 = personDAO.read(id2);
		if(p1==null || p2==null)
			return "404#"+"Bad Request : Person does not exist with given ID";

		boolean isAlreadyFriend = false;

		Iterator iterator = p1.getFriends().iterator();
		while(iterator.hasNext()){	
			Person p = (Person) iterator.next();
			if(p.getId()==id2){
				isAlreadyFriend=true;
				break;
			}		
		}

		if(!isAlreadyFriend){
			iterator = p2.getFriends().iterator();
			while(iterator.hasNext()){	
				Person p = (Person) iterator.next();
				if(p.getId()==id1){
					isAlreadyFriend=true;
					break;
				}		
			}
		}

		if(isAlreadyFriend)
			return "200#"+p1.getFirstname()+" "+p1.getLastname()+" and "+p2.getFirstname()+" "+p2.getLastname()+" are already friends";

		p1.getFriends().add(p2);
		friendDAO.create(p1);
		return "200#"+p1.getFirstname()+" "+p1.getLastname()+" and "+p2.getFirstname()+" "+p2.getLastname()+" are friends now !!!!";

	}
	
	/**
	 * Service method for removing friends
	 */
	public String delete(long id1, long id2) {

		Person p1 = personDAO.read(id1);
		Person p2 = personDAO.read(id2);
		if(p1==null || p2==null)
			return "404#"+"Bad Request : Person does not exist with given ID";
		
		Person person;
		boolean isFriend = false;

		Iterator iterator = p1.getFriends().iterator();

		while(iterator.hasNext()){	
			person = (Person) iterator.next();
			if(person.getId()==id2){
				iterator.remove();
				isFriend = true;
				break;
			}
		}
		person=p1;
		
		if(!isFriend){			
			iterator = p2.getFriends().iterator();

			while(iterator.hasNext()){
				person = (Person) iterator.next();
				if(person.getId()==id1){
					iterator.remove();
					isFriend = true;
					break;
				}		
			}
			person=p2;
		}
		
		if(!isFriend)
			return "404#"+p1.getFirstname()+" "+p1.getLastname()+" and "+p2.getFirstname()+" "+p2.getLastname()+" are already not friends";
			
		friendDAO.delete(person);
		return "200#"+p1.getFirstname()+" "+p1.getLastname()+" and "+p2.getFirstname()+" "+p2.getLastname()+" are no longer friends !!!!";
	}
}