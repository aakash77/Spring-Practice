package com.aakash.cmpe275.lab1_AOP.aspect;

import java.util.UUID;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.aakash.cmpe275.lab1_AOP.App;
import com.aakash.cmpe275.lab1_AOP.exceptions.SecretException;
import com.aakash.cmpe275.lab1_AOP.model.Secret;

@Component
@Aspect
public class SecretAspect {
	
	/**
	 * Advice for validating request for new secret storage
	 * @param joinPoint
	 * @throws SecretException
	 */
	@Before("execution(* com.aakash.cmpe275.lab1_AOP.service.SecretService.storeSecret(..))")
	public void storeSecretAdvice(JoinPoint joinPoint) throws SecretException{
		Object methodArgs[] = joinPoint.getArgs();
		try{
			String userId = (String) methodArgs[0];
			if(userId==null || userId=="")
				throw new NullPointerException();
			Secret secret = (Secret) methodArgs[1];
			String key = userId+"#"+secret.getUUID();
			if(App.userSecrets.containsKey(key))
				throw new SecretException("Secret Exists");
		}catch(NullPointerException e){
			throw new SecretException("Input cannot be null"); 
		}
	}
	
	/**
	 * Advice for validating and authorizing request for reading secret
	 * @param joinPoint
	 * @throws SecretException
	 */
	@Before("execution(* com.aakash.cmpe275.lab1_AOP.service.SecretService.readSecret(..))")
	public void readSecretAdvice(JoinPoint joinPoint) throws SecretException{
		Object methodArgs[] = joinPoint.getArgs();
		try{
			String userId = (String) methodArgs[0];
			UUID secretId = (UUID) methodArgs[1];
			if(userId==null || userId=="" || secretId==null)
				throw new NullPointerException();
			String key = userId+"#"+secretId;
			if(!App.userSecrets.containsKey(key) || !App.sharedSecrets.containsKey(key))
				throw new SecretException("Unauthorized Exception");
		}catch(NullPointerException e){
			throw new SecretException("Input cannot be null"); 
		}
	}
	
	/**
	 * Advice for validating and authorizing request of sharing advice 
	 * @param joinPoint
	 * @throws SecretException
	 */
	@Before("execution(* com.aakash.cmpe275.lab1_AOP.service.SecretService.shareSecret(..))")
	public void shareSecretAdvice(JoinPoint joinPoint) throws SecretException{
		Object methodArgs[] = joinPoint.getArgs();
		try{
			String userId = (String) methodArgs[0];
			UUID secretId = (UUID) methodArgs[1];
			String targetUserId = (String) methodArgs[2];
			if(userId==null || userId=="" || secretId==null || targetUserId==null || targetUserId=="")
				throw new NullPointerException();
			String key = userId+"#"+secretId;
			if(!App.userSecrets.containsKey(key) || !App.sharedSecrets.containsKey(key))
				throw new SecretException("Unauthorized Exception");
		}catch(NullPointerException e){
			throw new SecretException("Input cannot be null"); 
		}
	}
	
	/**
	 * Advice for validating and authorizing request for unsharing the secret
	 * @param joinPoint
	 * @throws SecretException
	 */
	@Before("execution(* com.aakash.cmpe275.lab1_AOP.service.SecretService.unshareSecret(..))")
	public void unshareSecretAdvice(JoinPoint joinPoint) throws SecretException{
		Object methodArgs[] = joinPoint.getArgs();
		try{
			String userId = (String) methodArgs[0];
			UUID secretId = (UUID) methodArgs[1];
			String targetUserId = (String) methodArgs[2];
			if(userId==null || userId=="" || secretId==null || targetUserId==null || targetUserId=="")
				throw new NullPointerException();
			String key = userId+"#"+secretId;
			if(!App.userSecrets.containsKey(key) || !App.sharedSecrets.containsKey(key))
				throw new SecretException("Unauthorized Exception");
		}catch(NullPointerException e){
			throw new SecretException("Input cannot be null"); 
		}
	}
	
}
