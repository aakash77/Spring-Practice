package com.cmpe275.lab2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cmpe275.lab2.dao.OrganizationDAO;
import com.cmpe275.lab2.model.Organization;
import com.cmpe275.lab2.service.OrganizationService;

@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

	
	@Autowired
	OrganizationDAO organizationDAO;
	
	public Organization create(Organization organization) {
		return organizationDAO.create(organization);
	}

	public Organization read(long id) {
		return organizationDAO.read(id);
	}

	public Organization update(Organization organization) {
		return organizationDAO.update(organization);
	}

	public Organization delete(long id) {
		Organization organization = organizationDAO.read(id);
		if(organization==null)
			return null;
		return organizationDAO.delete(organization);
	}

}
