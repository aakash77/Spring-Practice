package com.aakash.cmpe275.lab1_AOP.aspect;

import java.util.UUID;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.aakash.cmpe275.lab1_AOP.exceptions.UnauthorizedException;
import com.aakash.cmpe275.lab1_AOP.model.Secret;
import com.aakash.cmpe275.lab1_AOP.service.DataService;

/**
 * @author Aakash Mangal
 * Aspect for validating and authorizing request
 */
@Component
@Aspect
public class SecretAspect {

	/**
	 * Advice for validating request for new secret storage
	 * @param joinPoint
	 * @throws UnauthorizedException
	 */
	@Before("storeSecretAdviceAll()")
	public void storeSecretAdviceBefore(JoinPoint joinPoint) {
		Object methodArgs[] = joinPoint.getArgs();
		try{
			String userId = (String) methodArgs[0];
			Secret secret = (Secret) methodArgs[1];
			if(userId==null || userId=="" || secret==null)
				throw new NullPointerException();
		}catch(NullPointerException e){
			throw new NullPointerException("Input cannot be null"); 
		}
	}

	/**
	 * Advice for logging the storing a secret action
	 * @param joinPoint
	 * @param secret
	 * @throws UnauthorizedException
	 */
	@AfterReturning(pointcut = "storeSecretAdviceAll()",returning = "secret")
	public void storeSecretAdviceAfter(JoinPoint joinPoint,Object secret) throws UnauthorizedException{
		Object methodArgs[] = joinPoint.getArgs();
		String userId = (String) methodArgs[0];
		UUID secretId = (UUID) secret;
		System.out.println(userId+" creates a secret with ID "+secretId);
	}

	/**
	 * Advice for validating and authorizing request for reading secret
	 * @param joinPoint
	 * @throws UnauthorizedException
	 */
	@Before("execution(* com.aakash.cmpe275.lab1_AOP.service.SecretService.readSecret(..))")
	public void readSecretAdvice(JoinPoint joinPoint) throws UnauthorizedException{
		Object methodArgs[] = joinPoint.getArgs();
		try{
			String userId = (String) methodArgs[0];
			UUID secretId = (UUID) methodArgs[1];
			if(userId==null || userId=="" || secretId==null)
				throw new NullPointerException();
			System.out.println(userId+" reads the secret of ID "+secretId);
			String key = userId+"#"+secretId;
			if(!DataService.userSecrets.containsKey(key) && !DataService.sharedSecrets.containsKey(key))
				throw new UnauthorizedException("Unauthorized Exception : "+userId+" is not authorized to read this secret");
		}catch(NullPointerException e){
			throw new NullPointerException("Input cannot be null"); 
		}
	}

	/**
	 * Advice for validating and authorizing request of sharing advice 
	 * @param joinPoint
	 * @throws UnauthorizedException
	 */
	@Before("execution(* com.aakash.cmpe275.lab1_AOP.service.SecretService.shareSecret(..))")
	public void shareSecretAdvice(JoinPoint joinPoint) throws UnauthorizedException{
		Object methodArgs[] = joinPoint.getArgs();
		try{
			String userId = (String) methodArgs[0];
			UUID secretId = (UUID) methodArgs[1];
			String targetUserId = (String) methodArgs[2];
			if(userId==null || userId=="" || secretId==null || targetUserId==null || targetUserId=="")
				throw new NullPointerException();
			System.out.println(userId+" shares the secret of ID "+secretId+" with "+targetUserId);
			String key = userId+"#"+secretId;
			if(!DataService.userSecrets.containsKey(key) && !DataService.sharedSecrets.containsKey(key))
				throw new UnauthorizedException("Unauthorized Exception : "+userId+" is not authorized to share this secret");
		}catch(NullPointerException e){
			throw new NullPointerException("Input cannot be null"); 
		}
	}


	/**
	 * Advice for validating and authorizing request for unsharing the secret
	 * @param joinPoint
	 * @throws UnauthorizedException
	 */
	@Around("execution(* com.aakash.cmpe275.lab1_AOP.service.SecretService.unshareSecret(..))")
	public void unshareSecretAdvice(ProceedingJoinPoint joinPoint) throws UnauthorizedException{
		Object methodArgs[] = joinPoint.getArgs();
		String userId = (String) methodArgs[0];
		try{
			UUID secretId = (UUID) methodArgs[1];
			String targetUserId = (String) methodArgs[2];
			
			if(userId==null || userId=="" || secretId==null || targetUserId==null || targetUserId=="")
				throw new NullPointerException();
			
			System.out.println(userId+" unshares the secret of ID "+secretId+" with "+targetUserId);
			
			String key = userId+"#"+secretId;
			
			if(DataService.userSecrets.containsKey(key))
				joinPoint.proceed();
			else if(!DataService.sharedSecrets.containsKey(key))
				throw new Throwable();
		}catch(NullPointerException e){
			throw new NullPointerException("Input cannot be null"); 
		} catch (Throwable e) {
			throw new UnauthorizedException("Unauthorized Exception : "+userId+" is not authorized to unshare this secret");
		}
	}
	/**
	 * Pointcut for storing a secret
	 */
	@Pointcut("execution(* com.aakash.cmpe275.lab1_AOP.service.SecretService.storeSecret(..))")
	public void storeSecretAdviceAll(){}
}