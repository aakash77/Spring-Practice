package com.cmpe275.lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmpe275.lab2.service.FriendService;

/**
 * Controller for handling friends related requests 
 */
@Controller
@RequestMapping(value="/friends")
public class FriendsController {

	
	@Autowired
	FriendService friendService;
	
	/**
	 * Handler for mapping create friends request
	 * @param id1
	 * @param id2
	 * @return status message
	 */
	@RequestMapping(method=RequestMethod.PUT,value="{id1}/{id2}")
	@ResponseBody
	public ResponseEntity<String> addFriend(@PathVariable long id1, @PathVariable long id2){
		
		String result = friendService.create(id1, id2);
		String returnString = result.split("#")[1];
		String responseStatus = result.split("#")[0];
		
		if(responseStatus.equals("200"))
			return new ResponseEntity<String>(returnString, HttpStatus.OK);
		
		return new ResponseEntity<String>(returnString, HttpStatus.NOT_FOUND);
	}
	
	
	
	/**
	 * Handler for mapping removing friends request
	 * @param id1
	 * @param id2
	 * @return status message
	 */
	@RequestMapping(method=RequestMethod.DELETE,value="{id1}/{id2}")
	@ResponseBody
	public ResponseEntity<String> removeFriend(@PathVariable long id1, @PathVariable long id2){
		
		String result = friendService.delete(id1, id2);
		String returnString = result.split("#")[1];
		String responseStatus = result.split("#")[0];
		
		if(responseStatus.equals("200"))
			return new ResponseEntity<String>(returnString, HttpStatus.OK);
		
		return new ResponseEntity<String>(returnString, HttpStatus.NOT_FOUND);
	}
	
}
