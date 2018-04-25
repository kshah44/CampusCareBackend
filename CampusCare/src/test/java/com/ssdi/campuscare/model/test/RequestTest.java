package com.ssdi.campuscare.model.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssdi.campuscare.model.Request;

import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
@RunWith(JMockit.class)
@SpringBootTest
public class RequestTest {

	@Tested
	Request service;
	
	@Test
	public void testConstructor() 
	{
		service = new Request(521, 527, 7, "Pending");
		new Verifications()
		{{
			assertEquals(521, service.getConsumerId());
			assertEquals(527, service.getProviderId());
			assertEquals(7, service.getCategoryId());
			assertEquals("Pending", service.getStatus());
		}};
	}
	
	@Test
	public void testSetConsumerId()
	{
		service = new Request();
		new Verifications()
		{{
			assertEquals(0, service.getConsumerId());
		}};
		service.setConsumerId(567);
		new Verifications()
		{{
			assertEquals(567, service.getConsumerId());
		}};
	}
	
	@Test
	public void testSetProviderId()
	{
		service = new Request();
		new Verifications()
		{{
			assertEquals(0, service.getProviderId());
		}};
		service.setProviderId(567);;
		new Verifications()
		{{
			assertEquals(567, service.getProviderId());
		}};
	}
	
	@Test
	public void testSetCategoryId()
	{
		service = new Request();
		new Verifications()
		{{
			assertEquals(0, service.getCategoryId());
		}};
		service.setCategoryId(2);;
		new Verifications()
		{{
			assertEquals(2, service.getCategoryId());
		}};
	}
	
	@Test
	public void testSetStatus()
	{
		service = new Request();
		new Verifications()
		{{
			assertEquals(null, service.getStatus());
		}};
		service.setStatus("Pending");
		new Verifications()
		{{
			assertEquals("Pending", service.getStatus());
		}};
	}

}
