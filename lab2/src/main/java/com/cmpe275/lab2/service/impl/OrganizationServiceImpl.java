package com.cmpe275.lab2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.cmpe275.lab2.dao.OrganizationDAO;
import com.cmpe275.lab2.model.Organization;
import com.cmpe275.lab2.service.OrganizationService;

public class OrganizationServiceImpl implements OrganizationService {

	
	@Autowired
	OrganizationDAO organizationDAO;
	
	public Organization create(Organization organization) {
		return organization;
		// TODO Auto-generated method stub

	}

	public Organization read(long id) {
		// TODO Auto-generated method stub
		
		return organizationDAO.read(id);
	}

	public Organization update(Organization organization) {
		// TODO Auto-generated method stub
		return null;

	}

	public Organization delete(Organization organization) {
		
		// TODO Auto-generated method stub
		return null;
		
	}

}
