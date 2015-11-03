package com.cmpe275.lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmpe275.lab2.model.Address;
import com.cmpe275.lab2.model.Organization;
import com.cmpe275.lab2.model.Person;
import com.cmpe275.lab2.service.OrganizationService;
import com.cmpe275.lab2.service.PersonService;


@Controller
@RequestMapping(value="/person")
public class PersonController {

	@Autowired
	PersonService personService;
	
	@Autowired
	OrganizationService organizationService;
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public Person addUser(@ModelAttribute Person person,@ModelAttribute Address address, @ModelAttribute Organization organization){
		
		person.setAddress(address);
		Organization org = organizationService.read(organization.getOrganization_id());
		if(org==null)
			return null;
		person.setOrganization(org);
		return personService.create(person);
	}
	
	@RequestMapping(method=RequestMethod.GET,value="{id}")
	@ResponseBody
	public Person getUser(@PathVariable long id){
		
		return personService.read(id);
	}
	
}