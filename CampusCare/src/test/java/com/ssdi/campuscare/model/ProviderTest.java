package com.ssdi.campuscare.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssdi.campuscare.model.Consumer;
import com.ssdi.campuscare.model.Provider;

import mockit.integration.junit4.JMockit;
import mockit.Tested;
import mockit.Verifications;

@RunWith(JMockit.class)
@SpringBootTest
public class ProviderTest {

	@Tested
	private Provider testProvider;
	
	@Test
	public void testConstructor()
	{
		testProvider = new Provider("username", "Margeret", "Spryo", "hot@hotmail.com", "password");
		
		new Verifications()
		{{
			assertEquals("username", testProvider.getUserName());
			assertEquals("Margeret", testProvider.getFirstName());
			assertEquals("Spryo", testProvider.getLastName());
			assertEquals("hot@hotmail.com", testProvider.getEmail());
			assertEquals("password", testProvider.getPassword());
		}};
	}
	@Test
	public void testSetUserName()
	{
		testProvider = new Provider();
		//No initial username
		new Verifications()
		{{
			assertEquals(null, testProvider.getUserName());
		}};
		//create username as hardcore
		testProvider.setUserName("hardcore");
		//testing that the username is hardcore
		new Verifications()
		{{
			assertEquals("hardcore", testProvider.getUserName());
		}};
	}
	@Test
	public void testSetFirstName()
	{
		testProvider = new Provider();
		//No initial first name
		new Verifications()
		{{
			assertEquals(null, testProvider.getFirstName());
		}};
		//create first name as carter
		testProvider.setFirstName("carter");
		//testing that the first name is carter
		new Verifications()
		{{
			assertEquals("carter", testProvider.getFirstName());
		}};
	}
	@Test
	public void testSetLastName()
	{
		testProvider = new Provider();
		//No initial last name
		new Verifications()
		{{
			assertEquals(null, testProvider.getLastName());
		}};
		//create first name as carter
		testProvider.setLastName("carter");
		//testing that the last name is carter
		new Verifications()
		{{
			assertEquals("carter", testProvider.getLastName());
		}};
	}
	@Test
	public void testSetEmail()
	{
		testProvider = new Provider();
		//No initial email
		new Verifications()
		{{
			assertEquals(null, testProvider.getEmail());
		}};
		//create email as harsh@hotmail.com
		testProvider.setEmail("harsh@hotmail.com");
		//testing that the first name is carter
		new Verifications()
		{{
			assertEquals("harsh@hotmail.com", testProvider.getEmail());
		}};
	}
	@Test
	public void testSetPassword()
	{
		testProvider = new Provider();
		//No initial email
		new Verifications()
		{{
			assertEquals(null, testProvider.getPassword());
		}};
		//create password as root321
		testProvider.setPassword("root321");
		//testing that the password is root321
		new Verifications()
		{{
			assertEquals("root321", testProvider.getPassword());
		}};
	}

}
