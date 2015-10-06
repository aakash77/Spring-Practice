package com.aakash.cmpe275.lab1_AOP;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aakash.cmpe275.lab1_AOP.exceptions.UnauthorizedException;
import com.aakash.cmpe275.lab1_AOP.model.Secret;
import com.aakash.cmpe275.lab1_AOP.service.DataService;
import com.aakash.cmpe275.lab1_AOP.service.SecretService;

/**
 * @author Aakash Mangal
 * Unit test for Lab1-AOP.
 */
public class AppTest {

	@Autowired
	DataService dataService;
	ApplicationContext context;
	SecretService secretService;
	
	/**
	 * Initializing context
	 */
	@Before
	public void setUp(){
		context = new ClassPathXmlApplicationContext("beans.xml");
		secretService = (SecretService) context.getBean("secretServiceImpl");
	}
	
	
	/**
	 * Test for Unauthorized Read
	 */
	@Test(expected=UnauthorizedException.class)
	public void testA()
	{
		System.out.println("Test A");
		Secret secret = (Secret) context.getBean("secret");
		UUID secretId = secretService.storeSecret("Alice", secret);
		secretService.readSecret("Bob", secretId);
	}
	
	/**
	 * Test for Authorized Read
	 */
	@Test
	public void testB(){
		System.out.println("Test B");
		Secret secret = (Secret) context.getBean("secret");
		UUID secretId = secretService.storeSecret("Alice", secret);
		secretService.shareSecret("Alice", secretId, "Bob");
		secretService.readSecret("Bob", secretId);
	}
	
	/**
	 * Test for transitive share
	 */
	@Test
	public void testC(){
		
		System.out.println("Test C");
		Secret secret = (Secret) context.getBean("secret");
		UUID secretId = secretService.storeSecret("Alice", secret);
		secretService.shareSecret("Alice", secretId, "Bob");
		secretService.shareSecret("Bob", secretId, "Carl");
		secretService.readSecret("Carl", secretId);
	}
	
	/**
	 * Test for sharing unauthorized secret 
	 */
	@Test(expected=UnauthorizedException.class)
	public void testD(){
		
		System.out.println("Test D");
		Secret secretAlice = (Secret) context.getBean("secret");
		UUID secretIdAlice = secretService.storeSecret("Alice", secretAlice);
		Secret secretCarl = (Secret) context.getBean("secret");
		UUID secretIdCarl = secretService.storeSecret("Carl", secretCarl);
		secretService.shareSecret("Alice", secretIdAlice, "Bob");
		secretService.shareSecret("Bob", secretIdCarl, "Alice");
	}
	
	/**
	 * Test for unsharing transitive shared secret
	 */
	@Test(expected=UnauthorizedException.class)
	public void testE(){
		System.out.println("Test E");
		Secret secret = (Secret) context.getBean("secret");
		UUID secretId = secretService.storeSecret("Alice", secret);
		secretService.shareSecret("Alice", secretId, "Bob");
		secretService.shareSecret("Bob", secretId, "Carl");
		secretService.unshareSecret("Alice", secretId, "Carl");
		secretService.readSecret("Carl", secretId);
	}
	
	/**
	 *Test for multiple entry of a shared secret 
	 */
	@Test(expected=UnauthorizedException.class)
	public void testF(){
		System.out.println("Test F");
		Secret secret = (Secret) context.getBean("secret");
		UUID secretId = secretService.storeSecret("Alice", secret);
		secretService.shareSecret("Alice", secretId, "Bob");
		secretService.shareSecret("Alice", secretId, "Carl");
		secretService.shareSecret("Carl", secretId, "Bob");
		secretService.unshareSecret("Alice", secretId, "Bob");
		secretService.readSecret("Bob", secretId);
	}
	
	/**
	 * Test of unsharing an unowned secret
	 */
	@Test
	public void testG(){
		System.out.println("Test G");
		Secret secret = (Secret) context.getBean("secret");
		UUID secretId = secretService.storeSecret("Alice", secret);
		secretService.shareSecret("Alice", secretId, "Bob");
		secretService.shareSecret("Bob", secretId, "Carl");
		secretService.unshareSecret("Bob", secretId, "Carl");
		secretService.readSecret("Carl", secretId);
	}
	
	/**
	 * Test for unsharing unshared/unowned secret
	 */
	@Test(expected=UnauthorizedException.class)
	public void testH(){
		System.out.println("Test H");
		Secret secret = (Secret) context.getBean("secret");
		UUID secretId = secretService.storeSecret("Alice", secret);
		secretService.shareSecret("Alice", secretId, "Bob");
		secretService.unshareSecret("Carl", secretId, "Bob");
	}
	
	/**
	 * Test for sharing secret after being unshared
	 */
	@Test(expected=UnauthorizedException.class)
	public void testI(){
		System.out.println("Test I");
		Secret secret = (Secret) context.getBean("secret");
		UUID secretId = secretService.storeSecret("Alice", secret);
		secretService.shareSecret("Alice", secretId, "Bob");
		secretService.shareSecret("Bob", secretId, "Carl");
		secretService.unshareSecret("Alice", secretId, "Bob");
		secretService.shareSecret("Bob", secretId, "Carl");
	}
	
	/**
	 * Test for secret id generation at store location 
	 */
	@Test
	public void testJ(){
		System.out.println("Test J");
		Secret secret = (Secret) context.getBean("secret");
		UUID secretId1 = secretService.storeSecret("Alice", secret);
		UUID secretId2 = secretService.storeSecret("Alice", secret);
		boolean isSameSecretId = (secretId1==secretId2);
		Assert.assertEquals(false, isSameSecretId);
	}
}