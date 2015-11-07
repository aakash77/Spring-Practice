package com.cmpe275.lab2.service.impl;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmpe275.lab2.dao.OrganizationDAO;
import com.cmpe275.lab2.dao.PersonDAO;
import com.cmpe275.lab2.model.Organization;
import com.cmpe275.lab2.service.OrganizationService;

@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

	
	@Autowired
	OrganizationDAO organizationDAO;
	
	@Autowired
	PersonDAO personDAO;
	
	/**
	 * create organization service method
	 */
	public Organization create(Organization organization) {
		return organizationDAO.create(organization);
	}
	
	/**
	 * read organization service method
	 */
	public Organization read(long id) {
		return organizationDAO.read(id);
	}
	
	/**
	 * update organization service method
	 */
	public Organization update(Organization organization) {
		return organizationDAO.update(organization);
	}
	
	/**
	 * delete organization service method
	 */
	public Organization delete(long id) throws HibernateException {
		Organization organization = organizationDAO.read(id);
		if(organization==null)
			return null;
		if(personDAO.getPersonByOrganization(organization)!=null)
			throw new HibernateException("Cannot delete organization");
		else
			return organizationDAO.delete(organization);
	}
	
}
