/**
 * 
 */
package com.cmpe275.lab2.service;

import org.springframework.stereotype.Service;

import com.cmpe275.lab2.model.Organization;

@Service
public interface OrganizationService {
	/**
	 * create a new organization
	 */
	public Organization create(Organization organization);
	
	/**
	 * read organization's details 
	 */
	public Organization read(long id);
	
	
	/**
	 * update organization's details
	 */
	public Organization update(Organization organization);
	
	/**
	 * delete organization
	 */
	public Organization delete(long id);

}