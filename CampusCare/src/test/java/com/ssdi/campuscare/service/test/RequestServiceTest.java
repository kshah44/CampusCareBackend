package com.ssdi.campuscare.service.test;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssdi.campuscare.dao.IProviderDao;
import com.ssdi.campuscare.dao.IRequestDao;
import com.ssdi.campuscare.model.Provider;
import com.ssdi.campuscare.model.Request;
import com.ssdi.campuscare.service.ProviderService;
import com.ssdi.campuscare.service.RequestService;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
@SpringBootTest
public class RequestServiceTest 
{
	@Injectable
	private IRequestDao requestDao;
	
	@Tested
	RequestService requestService;
	
	@Test
	public void testGetAllRequests() 
	{
		Request request = new Request(1, 1, 1, "Pending");
		Request request1 = new Request(2, 2, 2, "Pending");
		List<Request> requestList = new ArrayList<Request>();
		requestList.add(request);
		requestList.add(request1);
		
		new Expectations()
		{{
			requestDao.getAllRequests();
			result = requestList;
		}};
		
		requestService.getAllRequests();
		
		new Verifications()
		{{
			assertEquals(2, requestList.size());
			assertEquals(1, requestList.get(0).getConsumerId());
			assertEquals(2, requestList.get(1).getConsumerId());
		}};
	}
	
	@Test
	public void testGetProviderRequest()
	{
		Request request = new Request(1, 1, 1, "Pending");
		List<Request> requestList = new ArrayList<Request>();
		requestList.add(request);
		
		new Expectations()
		{{
			requestDao.getProviderRequest(1);
			result = requestList;
		}};
		
		requestService.getProviderRequest(1);
		
		new Verifications()
		{{
			assertEquals(1, requestList.size());
			assertEquals(1, requestList.get(0).getConsumerId());
			assertEquals(1, requestList.get(0).getProviderId());
		}};
	}
	
	@Test
	public void testGetConsumerRequest()
	{
		Request request = new Request(1, 1, 1, "Pending");
		List<Request> requestList = new ArrayList<Request>();
		requestList.add(request);
		
		new Expectations()
		{{
			requestDao.getConsumerRequest(1);
			result = requestList;
		}};
		
		requestService.getConsumerRequest(1);
		
		new Verifications()
		{{
			assertEquals(1, requestList.size());
			assertEquals(1, requestList.get(0).getConsumerId());
			assertEquals(1, requestList.get(0).getProviderId());
		}};
	}
	
	@Test
	public void testCreateRequest()
	{
		Request request = new Request(1, 2, 3, "Pending");
		new Expectations()
		{{
			requestDao.createRequest(request);
			result = request;
		}};
		
		
		requestService.createRequest(request);
		new Verifications()
		{{
			assertEquals(1, request.getConsumerId());
			assertEquals(2, request.getProviderId());
			assertEquals(3, request.getCategoryId());
			
		}};	
	}
	
	@Test
	public void testRemoveRequest()
	{
		Request request = new Request(1, 2, 3, "Pending");
		List<Request> requestList = new ArrayList<Request>();
		requestList.add(request);
		new Expectations()
		{{
			requestDao.cancelRequest(request.getConsumerId(), request.getProviderId(), request.getCategoryId());
			result = request;
		}};
		
		
		requestService.cancelRequest(request.getConsumerId(), request.getProviderId(), request.getCategoryId());
		new Verifications()
		{{
			assertEquals(1, request.getConsumerId());
			assertEquals(2, request.getProviderId());
			assertEquals(3, request.getCategoryId());
			
		}};	
	}
}
