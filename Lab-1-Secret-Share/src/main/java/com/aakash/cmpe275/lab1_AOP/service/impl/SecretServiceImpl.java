package com.aakash.cmpe275.lab1_AOP.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.aakash.cmpe275.lab1_AOP.App;
import com.aakash.cmpe275.lab1_AOP.model.Secret;
import com.aakash.cmpe275.lab1_AOP.service.SecretService;

@Component
public class SecretServiceImpl implements SecretService {
	

	public UUID storeSecret(String userId, Secret secret) {
		System.out.println("In class impl");
		String key = userId+"#"+secret.getUUID();
		App.userSecrets.add(key);
		return secret.getUUID();
	}

	public Secret readSecret(String userId, UUID secretId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void shareSecret(String userId, UUID secretId, String targetUserId) {
		// TODO Auto-generated method stub
		
	}

	public void unshareSecret(String userId, UUID secretId, String targetUserId) {
		// TODO Auto-generated method stub
		
	}

}
