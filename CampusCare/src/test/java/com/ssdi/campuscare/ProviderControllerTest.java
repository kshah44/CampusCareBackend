package com.ssdi.campuscare;

import static
org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssdi.campuscare.controller.ProviderController;
import com.ssdi.campuscare.model.Provider;
import com.ssdi.campuscare.service.IProviderService;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
@SpringBootTest
public class ProviderControllerTest {
	
	@Tested(availableDuringSetup = true)
	private ProviderController providerController;
	
	@Injectable
	private IProviderService providerService;
	
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;
	
	@Before
	public void init()
	{
		objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(providerController).build();
	}
	

	@Test
	public void testVerifyLogin() 
	{
		Provider provider = new Provider();
		//Given the username is username and the password is password
		provider.setUserName("username");
		provider.setPassword("password");
		
		//This will execute after the connection is tested
		new Expectations()
		{{
			providerService.verifyLogin(provider.getUserName(), provider.getPassword());
		}};
		
		//Testing the connection with this url
		try
		{
			mockMvc.perform(post("/loginprovider")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(provider)))
			.andExpect(status().isOk());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	@Test
	public void testCreateProvider() {
		Provider provider = new Provider();
		//Given the username is username, the password is password, the email is hot@hotmail.com, and the name is Margeret Spyro
		provider.setUserName("username");
		provider.setPassword("password");
		provider.setEmail("hot@hotmail.com");
		provider.setFirstName("Margeret");
		provider.setLastName("Spyro");
		
		//This will execute after the connection is tested
		new Expectations()
		{{
			providerService.createProvider(provider);
		}};
		
		//Testing the connection with this url
		try
		{
			mockMvc.perform(post("/createprovider")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(provider)))
			.andExpect(status().isOk());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
