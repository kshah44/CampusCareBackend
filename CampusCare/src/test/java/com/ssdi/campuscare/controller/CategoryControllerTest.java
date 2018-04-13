package com.ssdi.campuscare.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssdi.campuscare.controller.ConsumerController;
import com.ssdi.campuscare.model.Category;
import com.ssdi.campuscare.model.Consumer;
import com.ssdi.campuscare.model.Provider;
import com.ssdi.campuscare.service.CategoryService;
import com.ssdi.campuscare.service.ICategoryService;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
@SpringBootTest
public class CategoryControllerTest {
	
	@Tested(availableDuringSetup = true)
	private CategoryController categoryController;
	
	@Injectable
	private CategoryService categoryService;
	
	private MockMvc mockMvc;
	
	private ObjectMapper objectMapper;
	
	@Before
	public void init()
	{
		objectMapper = new ObjectMapper();
		mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
	}
	

	@Test
	public void testGetAllCategories() 
	{
		Category category = new Category(1, "Sports");
		
		new Expectations()
		{{
			categoryService.getAllCategories();
		}};
		
		
		try
		{
			mockMvc.perform(post("/categories")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(category)))
			.andExpect(status().isOk());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	@Test
	public void testGetProviderCategories() {
		Provider provider = new Provider();
		provider.setProviderId(1);
		
		new Expectations()
		{{
			categoryService.getProviderCategories(provider.getProviderId());
		}};
		
		
		try
		{
			mockMvc.perform(post("/providercategories")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(provider)))
			.andExpect(status().isOk());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}


	/*
	@Test
	public void testAddProviderCategory() {
		String str = "{\"providerId\": \"2\",\"categoryId\": \"1\"}";
		JSONObject json = new JSONObject(str);
		//Category category = new Category(1, "Sports");
		
		//JSONObject json = new JSONObject();
		//json.put("providerId","2");
		//json.put("categoryId","1");
				
		
		//System.out.println("Displaying JSON object - providerId :" +json.get("providerId"));
		//System.out.println("Displaying JSON object - categoryId :" +json.get("categoryId"));
				
		new Expectations()
		{{
			categoryService.addProviderCategory(Integer.parseInt((String) json.get("providerId")),
					Integer.parseInt((String) json.get("categoryId")));
		}};
		
		
		try
		{
			mockMvc.perform(post("/addprovidercategory")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(json.toString())))
			.andExpect(status().isOk());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	} */

}

