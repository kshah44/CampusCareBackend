package com.ssdi.campuscare.model;

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
	Request testRequest;

	@Test
	public void testRequestConstructorWithNoParams() {
		testRequest = new Request();
		new Verifications() {
			{
				assertEquals(-1, testRequest.getRequestId());
				assertEquals(-1, testRequest.getConsumerId());
				assertEquals(-1, testRequest.getProviderId());
				assertEquals(-1, testRequest.getCategoryId());
				assertEquals(null, testRequest.getStatus());
				
			}
		};
	}

	@Test
	public void testRequestConstructorWithoutRequestId() {
		testRequest = new Request(1, 2, 3, "Pending");
		new Verifications() {
			{
				assertEquals(-1, testRequest.getRequestId());
				assertEquals(1, testRequest.getConsumerId());
				assertEquals(2, testRequest.getProviderId());
				assertEquals(3, testRequest.getCategoryId());
				assertEquals("Pending", testRequest.getStatus());
			}
		};
	}

	
	@Test
	public void testRequestConstructorWithParams() 
	{
		testRequest = new Request(2, 1, 2, 3, "Pending");
		new Verifications()
		{{
			assertEquals(2, testRequest.getRequestId());
			assertEquals(1, testRequest.getConsumerId());
			assertEquals(2, testRequest.getProviderId());
			assertEquals(3, testRequest.getCategoryId());
			assertEquals("Pending", testRequest.getStatus());
		}};
	}

	@Test
	public void testSetRequestId()
	{
		testRequest = new Request();
		new Verifications()
		{{
			assertEquals(-1, testRequest.getRequestId());
		}};
		testRequest.setRequestId(2);
		new Verifications()
		{{
			assertEquals(2, testRequest.getRequestId());
		}};
	}
	
	
	@Test
	public void testSetConsumerId()
	{
		testRequest = new Request();
		new Verifications()
		{{
			assertEquals(-1, testRequest.getConsumerId());
		}};
		testRequest.setConsumerId(567);
		new Verifications()
		{{
			assertEquals(567, testRequest.getConsumerId());
		}};
	}
	
	@Test
	public void testSetProviderId()
	{
		testRequest = new Request();
		new Verifications()
		{{
			assertEquals(-1, testRequest.getProviderId());
		}};
		testRequest.setProviderId(567);;
		new Verifications()
		{{
			assertEquals(567, testRequest.getProviderId());
		}};
	}
	
	@Test
	public void testSetCategoryId()
	{
		testRequest = new Request();
		new Verifications()
		{{
			assertEquals(-1, testRequest.getCategoryId());
		}};
		testRequest.setCategoryId(2);;
		new Verifications()
		{{
			assertEquals(2, testRequest.getCategoryId());
		}};
	}
	
	@Test
	public void testSetStatus()
	{
		testRequest = new Request();
		new Verifications()
		{{
			assertEquals(null, testRequest.getStatus());
		}};
		testRequest.setStatus("Pending");
		new Verifications()
		{{
			assertEquals("Pending", testRequest.getStatus());
		}};
	}

}
