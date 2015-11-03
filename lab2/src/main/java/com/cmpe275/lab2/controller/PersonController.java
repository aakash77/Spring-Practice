package com.cmpe275.lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<Person> addUser(@ModelAttribute Person person,@ModelAttribute Address address, @ModelAttribute Organization organization){

		if(person.getEmail()==null || person.getFirstname()==null || person.getLastname()==null){
			person=null;
			return new ResponseEntity<Person>(person, HttpStatus.BAD_REQUEST);
		}
		if(address!=null)
			person.setAddress(address);
		if(organization!=null){
			organization = organizationService.read(organization.getOrganization_id());
			if(organization==null){
				person=null;
				return new ResponseEntity<Person>(person, HttpStatus.BAD_REQUEST);
			}else
				person.setOrganization(organization);
		}
		person = personService.create(person);
		return new ResponseEntity<Person>(person, HttpStatus.OK);	
	}

	@RequestMapping(method=RequestMethod.GET,value="{id}")
	@ResponseBody
	public ResponseEntity<Person> getPerson(@PathVariable long id,@RequestParam(required=false) String format){
		/*System.out.println(format);*/
		//TODO check for format and return accordingly
		Person person = personService.read(id);
		if(person==null)
			return new ResponseEntity<Person>(person, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Person>(person, HttpStatus.OK);
	}

	/**
	 * Handler for mapping person update request
	 * @param id
	 * @param person
	 * @param organization
	 * @param address
	 * @return updated person object
	 */
	@RequestMapping(method=RequestMethod.POST,value="{id}")
	@ResponseBody
	public ResponseEntity<Person> updatePerson(@PathVariable long id,@ModelAttribute Person person,@ModelAttribute Organization organization, @ModelAttribute Address address){

		person.setId(id);
		if(person.getEmail()==null || person.getFirstname()==null || person.getLastname()==null){
			person=null;
			return new ResponseEntity<Person>(person, HttpStatus.BAD_REQUEST);
		}
		if(address!=null)
			person.setAddress(address);
		if(organization!=null){
			organization = organizationService.read(organization.getOrganization_id());
			if(organization==null){
				person=null;
				return new ResponseEntity<Person>(person, HttpStatus.BAD_REQUEST);
			}else
				person.setOrganization(organization);
		}
		person = personService.update(person);
		if(person==null)
			return new ResponseEntity<Person>(person, HttpStatus.NOT_FOUND);

		return new ResponseEntity<Person>(person, HttpStatus.OK);
	}

	/**
	 * Handler for mapping delete person request 
	 * @param id
	 * @return deleted person
	 */
	@RequestMapping(method=RequestMethod.DELETE,value="{id}")
	@ResponseBody
	public ResponseEntity<Person> deletePerson(@PathVariable long id){

		Person person = personService.delete(id);
		if(person==null)
			return new ResponseEntity<Person>(person, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Person>(person, HttpStatus.OK);
	}

}