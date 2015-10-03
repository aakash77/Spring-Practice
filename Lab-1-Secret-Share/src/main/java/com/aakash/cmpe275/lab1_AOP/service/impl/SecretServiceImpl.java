package com.aakash.cmpe275.lab1_AOP.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.aakash.cmpe275.lab1_AOP.App;
import com.aakash.cmpe275.lab1_AOP.model.Secret;
import com.aakash.cmpe275.lab1_AOP.service.SecretService;

@Component
public class SecretServiceImpl implements SecretService {
	
	/**
	 * Implementation of storing a new secret
	 * @param userId denotes the user requesting to store a new secret
	 * @param secret denotes the new secret to be added
	 */
	public UUID storeSecret(String userId, Secret secret) {
		System.out.println("In class impl");
		String key = userId+"#"+secret.getUUID();
		App.userSecrets.put(key,secret);
		return secret.getUUID();
	}
	
	/**
	 * Implementation of reading a secret
	 * @param userId denotes the user requesting the read operation
	 * @param secretId denotes the id of the secret which needs to be read
	 */
	public Secret readSecret(String userId, UUID secretId) {
		
		String key = userId+"#"+secretId;
		if(App.userSecrets.containsKey(key))
			return App.userSecrets.get(key);
		else
			return App.sharedSecrets.get(key);
	}
	
	/**
	 * Implementation of sharing a secret
	 * @param userId denotes the user with whom secret is already shared/owned
	 * @param secretId denotes the id of the secret which needs to be shared
	 * @param  targetUserId denotes the user with whom secret is to be shared
	 */
	public void shareSecret(String userId, UUID secretId, String targetUserId) {
		
		if(!userId.equals(targetUserId)){
			String ownerSecretKey = userId+"#"+secretId;
			String key = targetUserId+"#"+secretId;
			App.sharedSecrets.put(key, App.userSecrets.get(ownerSecretKey));
		}
	}
	
	/**
	 * Implementation of unsharing a secret
	 * @param userId denotes the user with whom secret is already shared/owned
	 * @param secretId denotes the id of the secret which needs to be unshared
	 * @param  targetUserId denotes the user with whom secret is to be unshared
	 */
	public void unshareSecret(String userId, UUID secretId, String targetUserId) {
		
		if(!userId.equals(targetUserId)){
			String ownerSecretKey = userId+"#"+secretId;
			if(App.userSecrets.containsKey(ownerSecretKey))
			{
				String key = targetUserId+"#"+secretId; 
				App.sharedSecrets.remove(key);
			}
		}
	}
}