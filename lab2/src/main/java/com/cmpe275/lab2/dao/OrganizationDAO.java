/**
 * 
 */
package com.cmpe275.lab2.dao;

import org.springframework.stereotype.Repository;

import com.cmpe275.lab2.model.Organization;

@Repository
public interface OrganizationDAO {
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
	public Organization delete(Organization organization);

}