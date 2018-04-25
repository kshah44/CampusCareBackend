package com.ssdi.campuscare.model.test;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssdi.campuscare.model.Consumer;

import mockit.integration.junit4.JMockit;
import mockit.Tested;
import mockit.Verifications;

@RunWith(JMockit.class)
@SpringBootTest
public class ConsumerTest {

    @Tested
	private Consumer testConsumer;
	
	@Test
	public void testConstructor()
	{
		testConsumer = new Consumer("username", "Margeret", "Spryo", "hot@hotmail.com", "password");
		
		new Verifications()
		{{
			assertEquals("username", testConsumer.getUserName());
			assertEquals("Margeret", testConsumer.getFirstName());
			assertEquals("Spryo", testConsumer.getLastName());
			assertEquals("hot@hotmail.com", testConsumer.getEmail());
			assertEquals("password", testConsumer.getPassword());
		}};
	}
	@Test
	public void testSetUserName()
	{
		testConsumer = new Consumer();
		//No initial username
		new Verifications()
		{{
			assertEquals(null, testConsumer.getUserName());
		}};
		//create username as hardcore
		testConsumer.setUserName("hardcore");
		//testing that the username is hardcore
		new Verifications()
		{{
			assertEquals("hardcore", testConsumer.getUserName());
		}};
	}
	@Test
	public void testSetFirstName()
	{
		testConsumer = new Consumer();
		//No initial first name
		new Verifications()
		{{
			assertEquals(null, testConsumer.getFirstName());
		}};
		//create first name as carter
		testConsumer.setFirstName("carter");
		//testing that the first name is carter
		new Verifications()
		{{
			assertEquals("carter", testConsumer.getFirstName());
		}};
	}
	@Test
	public void testSetLastName()
	{
		testConsumer = new Consumer();
		//No initial last name
		new Verifications()
		{{
			assertEquals(null, testConsumer.getLastName());
		}};
		//create first name as carter
		testConsumer.setLastName("carter");
		//testing that the last name is carter
		new Verifications()
		{{
			assertEquals("carter", testConsumer.getLastName());
		}};
	}
	@Test
	public void testSetEmail()
	{
		testConsumer = new Consumer();
		//No initial email
		new Verifications()
		{{
			assertEquals(null, testConsumer.getEmail());
		}};
		//create email as harsh@hotmail.com
		testConsumer.setEmail("harsh@hotmail.com");
		//testing that the first name is carter
		new Verifications()
		{{
			assertEquals("harsh@hotmail.com", testConsumer.getEmail());
		}};
	}
	@Test
	public void testSetPassword()
	{
		testConsumer = new Consumer();
		//No initial email
		new Verifications()
		{{
			assertEquals(null, testConsumer.getPassword());
		}};
		//create password as root321
		testConsumer.setPassword("root321");
		//testing that the password is root321
		new Verifications()
		{{
			assertEquals("root321", testConsumer.getPassword());
		}};
	}
}
