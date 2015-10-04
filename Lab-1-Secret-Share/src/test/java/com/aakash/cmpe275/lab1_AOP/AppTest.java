package com.aakash.cmpe275.lab1_AOP;

import java.util.UUID;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aakash.cmpe275.lab1_AOP.exceptions.UnauthorizedException;
import com.aakash.cmpe275.lab1_AOP.model.Secret;
import com.aakash.cmpe275.lab1_AOP.service.DataService;
import com.aakash.cmpe275.lab1_AOP.service.SecretService;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase{

	ApplicationContext context;
	SecretService secretService;
	@Autowired
	DataService dataService;

	/**
	 * Create the test case
	 * @param testName name of the test case
	 */
	public AppTest( String testName )
	{
		super( testName );
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		context = new ClassPathXmlApplicationContext("beans.xml");
		secretService = (SecretService) context.getBean("secretServiceImpl");
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite()
	{
		return new TestSuite( AppTest.class );
	}

	/**
	 * Test for Unauthorized Read
	 */
	@org.junit.Test
	public void testA()
	{
		System.out.println("Test A");
		Secret secret = (Secret) context.getBean("secret");
		UUID secretId = secretService.storeSecret("Alice", secret);
		try{
			secretService.readSecret("Bob", secretId);
			assertTrue(false);
		}catch(UnauthorizedException s){
			System.out.println(s.getMessage());
			assertTrue(true);
		}
	}
	
	/**
	 * Test for Authorized Read
	 */
	@org.junit.Test
	public void testB(){
		
		System.out.println("Test B");
		Secret secret = (Secret) context.getBean("secret");
		UUID secretId = secretService.storeSecret("Alice", secret);
		try{
			secretService.shareSecret("Alice", secretId, "Bob");
			secretService.readSecret("Bob", secretId);
			assertTrue(true);
		}catch(UnauthorizedException s){
			assertTrue(false);
		}
	}
	
	/**
	 * Test for transitive share
	 */
	@org.junit.Test
	public void testC(){
		System.out.println("Test C");
		Secret secret = (Secret) context.getBean("secret");
		UUID secretId = secretService.storeSecret("Alice", secret);
		try{
			secretService.shareSecret("Alice", secretId, "Bob");
			secretService.shareSecret("Bob", secretId, "Carl");
			secretService.readSecret("Carl", secretId);
			assertTrue(true);
		}catch(UnauthorizedException e){
			System.out.println(e.getMessage());
			assertTrue(false);
		}
	}
	
	/**
	 * Test for sharing unauthorized secret 
	 */
	@org.junit.Test
	public void testD(){
		System.out.println("Test D");
		Secret secretAlice = (Secret) context.getBean("secret");
		UUID secretIdAlice = secretService.storeSecret("Alice", secretAlice);
		Secret secretCarl = (Secret) context.getBean("secret");
		UUID secretIdCarl = secretService.storeSecret("Carl", secretCarl);
		
		try{
			secretService.shareSecret("Alice", secretIdAlice, "Bob");
			secretService.shareSecret("Bob", secretIdCarl, "Alice");
			assertTrue(false);
		}catch(UnauthorizedException e){
			System.out.println(e.getMessage());
			assertTrue(true);
		}
	}
	
	/**
	 * Test for unsharing transitive shared secret
	 */
	@org.junit.Test
	public void testE(){
		System.out.println("Test E");
		Secret secret = (Secret) context.getBean("secret");
		UUID secretId = secretService.storeSecret("Alice", secret);
		try{
			secretService.shareSecret("Alice", secretId, "Bob");
			secretService.shareSecret("Bob", secretId, "Carl");
			secretService.unshareSecret("Alice", secretId, "Carl");
			secretService.readSecret("Carl", secretId);
			assertTrue(false);
		}catch(UnauthorizedException e){
			System.out.println(e.getMessage());
			assertTrue(true);
		}
	}
	
	/**
	 *Test for multiple entry of a shared secret 
	 */
	@org.junit.Test
	public void testF(){
		System.out.println("Test F");
		Secret secret = (Secret) context.getBean("secret");
		UUID secretId = secretService.storeSecret("Alice", secret);
		try{
			secretService.shareSecret("Alice", secretId, "Bob");
			secretService.shareSecret("Alice", secretId, "Carl");
			secretService.shareSecret("Carl", secretId, "Bob");
			secretService.unshareSecret("Alice", secretId, "Bob");
			secretService.readSecret("Bob", secretId);
			assertTrue(false);
		}catch(UnauthorizedException e){
			System.out.println(e.getMessage());
			assertTrue(true);
		}
	}
	
	/**
	 * Test of unsharing an unowned secret
	 */
	@org.junit.Test
	public void testG(){
		System.out.println("Test G");
		Secret secret = (Secret) context.getBean("secret");
		UUID secretId = secretService.storeSecret("Alice", secret);
		try{
			secretService.shareSecret("Alice", secretId, "Bob");
			secretService.shareSecret("Bob", secretId, "Carl");
			secretService.unshareSecret("Bob", secretId, "Carl");
			secretService.readSecret("Carl", secretId);
			assertTrue(true);
		}catch(UnauthorizedException e){
			assertTrue(false);
		}
	}
	
	/**
	 * Test for unsharing unshared/unowned secret
	 */
	@org.junit.Test
	public void testH(){
		System.out.println("Test H");
		Secret secret = (Secret) context.getBean("secret");
		UUID secretId = secretService.storeSecret("Alice", secret);
		try{
			secretService.shareSecret("Alice", secretId, "Bob");
			secretService.unshareSecret("Carl", secretId, "Bob");
			assertTrue(false);
		}catch(UnauthorizedException e){
			System.out.println(e.getMessage());
			assertTrue(true);
		}
	}
	
	/**
	 * Test for sharing secret after being unshared
	 */
	@org.junit.Test
	public void testI(){
		System.out.println("Test I");
		Secret secret = (Secret) context.getBean("secret");
		UUID secretId = secretService.storeSecret("Alice", secret);
		try{
			secretService.shareSecret("Alice", secretId, "Bob");
			secretService.shareSecret("Bob", secretId, "Carl");
			secretService.unshareSecret("Alice", secretId, "Bob");
			secretService.shareSecret("Bob", secretId, "Carl");
			assertTrue(false);
		}catch(UnauthorizedException e){
			System.out.println(e.getMessage());
			assertTrue(true);
		}
	}
	
	/**
	 * Test for secret id generation at store location 
	 */
	@org.junit.Test
	public void testJ(){
		System.out.println("Test J");
		Secret secret = (Secret) context.getBean("secret");
		UUID secretId1 = secretService.storeSecret("Alice", secret);
		UUID secretId2 = secretService.storeSecret("Alice", secret);
		boolean isSameSecretId = (secretId1==secretId2);
		assertEquals(false, isSameSecretId);
	}
}