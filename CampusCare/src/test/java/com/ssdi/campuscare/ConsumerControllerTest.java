package com.ssdi.campuscare;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssdi.campuscare.controller.ConsumerController;
import com.ssdi.campuscare.model.Consumer;
import com.ssdi.campuscare.service.ConsumerService;
import com.ssdi.campuscare.service.IConsumerService;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
@SpringBootTest
public class ConsumerControllerTest {
	
	@Tested(availableDuringSetup = true)
	private ConsumerController consumerController;
	
	@Injectable
	private ConsumerService consumerService;
	
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;
	
	@Before
	public void init()
	{
		objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(consumerController).build();
	}
	

	@Test
	public void testVerifyLogin() 
	{
		Consumer consumer = new Consumer();
		//Given the username is username and the password is password
		consumer.setUserName("username");
		consumer.setPassword("password");
		
		//This will execute after the connection is tested
		new Expectations()
		{{
			consumerService.verifyLogin(consumer.getUserName(), consumer.getPassword());
		}};
		
		//Testing the connection with this url
		try
		{
			mockMvc.perform(post("/loginconsumer")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(consumer)))
			.andExpect(status().isOk());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testCreateConsumer() {
		Consumer consumer = new Consumer();
		//Given the username is username, the password is password, the email is hot@hotmail.com, and the name is Margeret Spyro
		consumer.setUserName("username");
		consumer.setPassword("password");
		consumer.setEmail("hot@hotmail.com");
		consumer.setFirstName("Margeret");
		consumer.setLastName("Spyro");
		
		//This will execute after the connection is tested
		new Expectations()
		{{
			consumerService.createConsumer(consumer);
		}};
		
		//Testing the connection with this url
		try
		{
			mockMvc.perform(post("/createconsumer")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(consumer)))
			.andExpect(status().isOk());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
