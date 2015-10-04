package com.aakash.cmpe275.lab1_AOP.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.aakash.cmpe275.lab1_AOP.model.Secret;
import com.aakash.cmpe275.lab1_AOP.service.DataService;
import com.aakash.cmpe275.lab1_AOP.service.SecretService;

/**
 * @author Aakash Mangal
 *	Implementation of Secret Service
 */
@Component
public class SecretServiceImpl implements SecretService {
	
	/**
	 * Implementation of storing a new secret
	 * @param userId denotes the user requesting to store a new secret
	 * @param secret denotes the new secret to be added
	 */
	public UUID storeSecret(String userId, Secret secret) {
		secret.generateId();
		String key = userId+"#"+secret.getId();
		DataService.userSecrets.put(key,secret);
		return secret.getId();
	}
	
	/**
	 * Implementation of reading a secret
	 * @param userId denotes the user requesting the read operation
	 * @param secretId denotes the id of the secret which needs to be read
	 */
	public Secret readSecret(String userId, UUID secretId) {
		
		String key = userId+"#"+secretId;
		if(DataService.userSecrets.containsKey(key))
			return DataService.userSecrets.get(key);
		else
			return DataService.sharedSecrets.get(key);
	}
	
	/**
	 * Implementation of sharing a secret
	 * @param userId denotes the user with whom secret is already shared/owned
	 * @param secretId denotes the id of the secret which needs to be shared
	 * @param  targetUserId denotes the user with whom secret is to be shared
	 */
	public void shareSecret(String userId, UUID secretId, String targetUserId) {
		
			String ownerSecretKey = userId+"#"+secretId;
			String key = targetUserId+"#"+secretId;
			DataService.sharedSecrets.put(key, DataService.userSecrets.get(ownerSecretKey));
	}
	
	/**
	 * Implementation of unsharing a secret
	 * @param userId denotes the user with whom secret is already shared/owned
	 * @param secretId denotes the id of the secret which needs to be unshared
	 * @param  targetUserId denotes the user with whom secret is to be unshared
	 */
	public void unshareSecret(String userId, UUID secretId, String targetUserId) {
		String key = targetUserId+"#"+secretId; 
		DataService.sharedSecrets.remove(key);
	}
}