package com.aakash.cmpe275.lab1_AOP;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aakash.cmpe275.lab1_AOP.model.Secret;
import com.aakash.cmpe275.lab1_AOP.service.SecretService;

public class App {
	
	/*public static ArrayList<String> userSecrets;
	public static ArrayList<String> sharedSecrets;*/
	
	
	public static HashMap<String, Secret> userSecrets;
	public static HashMap<String, Secret> sharedSecrets;

	public static void main(String[] args) {
		
		/*userSecrets = new ArrayList<String>();
		sharedSecrets = new ArrayList<String>();*/
		
		userSecrets = new HashMap<String, Secret>();
		sharedSecrets = new HashMap<String, Secret>();
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		
		SecretService secretService = (SecretService) context.getBean("secretServiceImpl");
		Secret secret = (Secret) context.getBean("secret1");
		userSecrets.put("1111#5231b533-ba17-4787-98a3-f2df37de2ad7",secret);
		secretService.storeSecret("", secret);

	}

}
