package com.cmpe275.lab2.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonUnwrapped;

@Entity
@Table(name="organization")
public class Organization {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long organization_id;
	@Column(nullable=false)
    private String name;
    private String description;
    @Embedded
    @JsonUnwrapped
    private Address address;
	
	/**
	 * @return the organization_id
	 */
	public long getOrganization_id() {
		return organization_id;
	}
	/**
	 * @param organization_id the organization_id to set
	 */
	public void setOrganization_id(long organization_id) {
		this.organization_id = organization_id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	@Override
	public String toString() {
		return "organization : "+this.getName()+","+this.getDescription()+","+this.getAddress().toString();
	}
}