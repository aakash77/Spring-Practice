package com.cmpe275.lab2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

/**
 * Controller for handling person related requests 
 */
@Controller
@RequestMapping(value="/person")
public class PersonController {

	@Autowired
	PersonService personService;

	@Autowired
	OrganizationService organizationService;
	
	
	/**
	 * Handler for mapping person get request
	 * @param id
	 * @param format
	 * @return requested person details
	 */
	@RequestMapping(method=RequestMethod.GET,value="{id}",produces={"application/json","application/xml"})
	@ResponseBody
	public ResponseEntity<Person> getPerson(@PathVariable long id,@RequestParam(required=false) String format){
		Person person = personService.read(id);
		if(person==null)
			return new ResponseEntity<Person>(person, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Person>(person, HttpStatus.OK);
	}
	
	/**
	 * Handler for mapping person get request with content type html
	 * @param id
	 * @param model
	 * @return view
	 */
	@RequestMapping(method=RequestMethod.GET,value="{id}",produces={"text/html"})
	public String getPersonView(@PathVariable long id,Model model){
		
		Person person = personService.read(id);
		if(person==null){
			model.addAttribute("resource", "Person");
			return "error";
		}
		model.addAttribute("person", person);
		return "person";
	}
	
	
	/**
	 * Handler for mapping person create request
	 * @param person
	 * @param address
	 * @param organization
	 * @return the new created person details
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Person> addUser(@ModelAttribute Person person,@ModelAttribute Address address, @ModelAttribute Organization organization){

		if(person.getEmail()==null || person.getFirstname()==null || person.getLastname()==null){
			person=null;
			return new ResponseEntity<Person>(person, HttpStatus.BAD_REQUEST);
		}
		if(address!=null)
			person.setAddress(address);
		if(organization.getOrganization_id()!=0){
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