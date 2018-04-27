package com.ssdi.campuscare.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssdi.campuscare.model.Category;
import com.ssdi.campuscare.model.Consumer;
import com.ssdi.campuscare.model.Provider;
import com.ssdi.campuscare.model.Request;
import com.ssdi.campuscare.service.CategoryService;
import com.ssdi.campuscare.service.RequestService;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
@SpringBootTest
public class RequestControllerTest {

	@Tested(availableDuringSetup = true)
	private RequestController requestController;
	
	@Injectable
	private RequestService requestService;
	
	@Injectable
	private Provider test_provider;

	@Injectable
	private Consumer test_consumer;

	@Injectable
	private Request test_request;

	
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;
	
	@Before
	public void init()
	{
		objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(requestController).build();
	}


	@Test
	public void testGetProviderRequests() 
	{
		test_provider = new Provider(1, "John1111","John","Doe", "John@gmail.com", "Password123");
		
		new Expectations()
		{{
			requestService.getProviderRequest(test_provider.getProviderId());
		}};
		
		try
		{
			mockMvc.perform(post("/providerrequests")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(test_provider)))
			.andExpect(status().isOk());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testGetConsumerRequests() 
	{
		test_consumer = new Consumer(1, "shashikant","Shashikant","Jaiswal", "shashi@gmail.com", "Password123");
		
		new Expectations()
		{{
			requestService.getConsumerRequest(test_consumer.getConsumerId());
		}};
		
		try
		{
			mockMvc.perform(post("/consumerrequests")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(test_consumer)))
			.andExpect(status().isOk());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}


	@Test
	public void testCreateRequest() 
	{
		test_request = new Request(1,1,1,"Pending");
		
		new Expectations()
		{{
			requestService.createRequest(test_request);
		}};
		
		try
		{
			mockMvc.perform(post("/createRequest")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(test_request)))
			.andExpect(status().isOk());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	@Test
	public void testConsumerUpdateRequestStatus() {
		
		JSONObject json = new JSONObject();
		json.put("requestId","1");
		json.put("consumerId","1");
		json.put("status","Cancelled");
				
		new Expectations()
		{{
			requestService.ConsumerUpdateRequestStatus(Integer.parseInt((String) json.get("requestId")),
					Integer.parseInt((String) json.get("consumerId")), (String) json.get("status"));
		}};
		
		
		try
		{
			mockMvc.perform(post("/cancelrequest")
					.contentType(MediaType.ALL)
					.content(json.toString()))
					.andExpect(status().isOk());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
} 
	
	
	
	@Test
	public void testProviderUpdateRequestStatus() {
		
		JSONObject json = new JSONObject();
		json.put("requestId","1");
		json.put("providerId","1");
		json.put("status","Accepted");
				
		new Expectations()
		{{
			requestService.ProviderUpdateRequestStatus(Integer.parseInt((String) json.get("requestId")),
					Integer.parseInt((String) json.get("providerId")), (String) json.get("status"));
		}};
		
		
		try
		{
			mockMvc.perform(post("/updatestatus")
					.contentType(MediaType.ALL)
					.content(json.toString()))
					.andExpect(status().isOk());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	} 

}
