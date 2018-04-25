package com.ssdi.campuscare.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

//import mockito.Verifications;
//import mockit.intergration.junit4.JMockit;


public class AdministratorTest {
	private Administrator test_administrator;
	

	@Test
	public void testSetUserName() {
		test_administrator = new Administrator();
		
		assertEquals(" ", test_administrator.getUserName());
		
		//When set userName is invoked
		test_administrator.setUserName("webewright");
		 
		assertEquals("webewright",test_administrator.getUserName());
		
		
	}

	@Test
	public void testSetFirstName() {
		test_administrator = new Administrator();
		
		test_administrator.setFirstName("Kevin");
		

		assertEquals("Kevin", test_administrator.getFirstName());

	}

	@Test
	public void testGetLastName() {
		
		test_administrator= new Administrator();
		test_administrator.setLastName("Blair");
		
				
		assertEquals("Blair", test_administrator.getLastName());
	}

	@Test
	public void testSetLastName() {
		test_administrator = new Administrator();
		
		test_administrator.setLastName("Blair");
		
		assertEquals("Blair", test_administrator.getLastName());
	}

	/*@Test
	public void testGetEmail() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testSetEmail() {
		
		test_administrator = new Administrator();
		
		test_administrator.setEmail("kblair34@yahoo.com");


		assertEquals("kblair34@yahoo.com", test_administrator.getEmail());

	}

	/*@Test
	public void testGetPassword() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testSetPassword() {
		
		test_administrator = new Administrator();
		
		test_administrator.setPassword("!29WebCash");


		assertEquals("!29WebCash", test_administrator.getPassword());

	}

}
