package com.aakash.cmpe275.lab1_AOP.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.aakash.cmpe275.lab1_AOP.model.Secret;

@Service
public class DataService {

	public static HashMap<String, Secret> userSecrets;
	public static HashMap<String, Secret> sharedSecrets;
	
	public DataService() {
		userSecrets = new HashMap<String, Secret>();
		sharedSecrets = new HashMap<String, Secret>();
	}
}
