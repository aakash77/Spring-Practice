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
	public void setUp() throws Exception{
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
		UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
		secretService.readSecret("Bob", aliceSecret);
	}
	
	/**
	 * Test for Authorized Read
	 */
	@Test
	public void testB(){
		System.out.println("Test B");
		UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
		secretService.shareSecret("Alice", aliceSecret, "Bob");
		secretService.readSecret("Bob", aliceSecret);
	}
	
	/**
	 * Test for transitive share
	 */
	@Test
	public void testC(){
		
		System.out.println("Test C");
		UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
		secretService.shareSecret("Alice", aliceSecret, "Bob");
		secretService.shareSecret("Bob", aliceSecret, "Carl");
		secretService.readSecret("Carl", aliceSecret);
	}
	
	/**
	 * Test for sharing unauthorized secret 
	 */
	@Test(expected=UnauthorizedException.class)
	public void testD(){
		
		System.out.println("Test D");
		UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
		UUID carlSecret = secretService.storeSecret("Carl", new Secret());
		secretService.shareSecret("Alice", aliceSecret, "Bob");
		secretService.shareSecret("Bob", carlSecret, "Alice");
	}
	
	/**
	 * Test for unsharing transitive shared secret
	 */
	@Test(expected=UnauthorizedException.class)
	public void testE(){
		System.out.println("Test E");
		UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
		secretService.shareSecret("Alice", aliceSecret, "Bob");
		secretService.shareSecret("Bob", aliceSecret, "Carl");
		secretService.unshareSecret("Alice", aliceSecret, "Carl");
		secretService.readSecret("Carl", aliceSecret);
	}
	
	/**
	 *Test for multiple entry of a shared secret 
	 */
	@Test(expected=UnauthorizedException.class)
	public void testF(){
		System.out.println("Test F");
		UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
		secretService.shareSecret("Alice", aliceSecret, "Bob");
		secretService.shareSecret("Alice", aliceSecret, "Carl");
		secretService.shareSecret("Carl", aliceSecret, "Bob");
		secretService.unshareSecret("Alice", aliceSecret, "Bob");
		secretService.readSecret("Bob", aliceSecret);
	}
	
	/**
	 * Test of unsharing an unowned secret
	 */
	@Test
	public void testG(){
		System.out.println("Test G");
		UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
		secretService.shareSecret("Alice", aliceSecret, "Bob");
		secretService.shareSecret("Bob", aliceSecret, "Carl");
		secretService.unshareSecret("Bob", aliceSecret, "Carl");
		secretService.readSecret("Carl", aliceSecret);
	}
	
	/**
	 * Test for unsharing unshared/unowned secret
	 */
	@Test(expected=UnauthorizedException.class)
	public void testH(){
		System.out.println("Test H");
		UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
		secretService.shareSecret("Alice", aliceSecret, "Bob");
		secretService.unshareSecret("Carl", aliceSecret, "Bob");
	}
	
	/**
	 * Test for sharing secret after being unshared
	 */
	@Test(expected=UnauthorizedException.class)
	public void testI(){
		System.out.println("Test I");
		UUID aliceSecret = secretService.storeSecret("Alice", new Secret());
		secretService.shareSecret("Alice", aliceSecret, "Bob");
		secretService.shareSecret("Bob", aliceSecret, "Carl");
		secretService.unshareSecret("Alice", aliceSecret, "Bob");
		secretService.shareSecret("Bob", aliceSecret, "Carl");
	}
	
	/**
	 * Test for secret id generation at store location 
	 */
	@Test
	public void testJ(){
		System.out.println("Test J");
		UUID aliceSecret1 = secretService.storeSecret("Alice", new Secret());
		UUID aliceSecret2 = secretService.storeSecret("Alice", new Secret());
		boolean isSameSecretId = (aliceSecret1==aliceSecret2);
		Assert.assertEquals(false, isSameSecretId);
	}
}