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
import com.cmpe275.lab2.service.OrganizationService;

@Controller
@RequestMapping(value="/org")
public class OrganizationController {

	@Autowired
	OrganizationService organizationService;
	
	/**
	 * Handler for mapping organization get request
	 * @param id
	 * @param format
	 * @return requested organization details
	 */
	@RequestMapping(method=RequestMethod.GET,value="{id}")
	@ResponseBody
	public ResponseEntity<Organization> getOrg(@PathVariable long id,@RequestParam(required=false) String format){
		/*System.out.println(format);*/
		//TODO check for format and return accordingly
		Organization organization = organizationService.read(id);
		if(organization==null)
			return new ResponseEntity<Organization>(organization, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Organization>(organization, HttpStatus.OK);
	}
	
	/**
	 * Handler for mapping organization create request
	 * @param organization
	 * @param address
	 * @return created organization details
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Organization> createOrg(@ModelAttribute Organization organization,@ModelAttribute Address address){
		if(organization.getName()==null){
			organization=null;
			return new ResponseEntity<Organization>(organization, HttpStatus.BAD_REQUEST);
		}
		
		if(address!=null)
			organization.setAddress(address);
			
		organization=organizationService.create(organization);
		return new ResponseEntity<Organization>(organization, HttpStatus.OK);
	}
	
	
	/**
	 * Handler for mapping organization update request
	 * @param organization
	 * @param address
	 * @return updated organization details
	 */
	@RequestMapping(method=RequestMethod.POST,value="{id}")
	@ResponseBody
	public ResponseEntity<Organization> updateOrg(@PathVariable long id,@ModelAttribute Organization organization,@ModelAttribute Address address){
		if(organization.getName()==null){
			organization=null;
			return new ResponseEntity<Organization>(organization, HttpStatus.BAD_REQUEST);
		}
		
		if(address!=null)
			organization.setAddress(address);
		organization.setOrganization_id(id);	
		organization=organizationService.update(organization);
		if(organization==null)
			return new ResponseEntity<Organization>(organization, HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<Organization>(organization, HttpStatus.OK);
	}
	
	/**
	 * Handler for mapping delete organization request 
	 * @param id
	 * @return deleted organization
	 */
	@RequestMapping(method=RequestMethod.DELETE,value="{id}")
	@ResponseBody
	public ResponseEntity<Organization> deleteOrg(@PathVariable long id){

		Organization organization = organizationService.delete(id);
		if(organization==null)
			return new ResponseEntity<Organization>(organization, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<Organization>(organization, HttpStatus.OK);
	}
}