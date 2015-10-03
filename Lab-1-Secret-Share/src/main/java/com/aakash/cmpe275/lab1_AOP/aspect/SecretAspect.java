package com.aakash.cmpe275.lab1_AOP.aspect;

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
			if(App.userSecrets.contains(key))
				throw new SecretException("Secret Exists");
		}catch(NullPointerException e){
			throw new SecretException("Input cannot be null"); 
		}
	}
	
	
	
	
}
