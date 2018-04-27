package com.ssdi.campuscare.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssdi.campuscare.dao.IProviderDao;
import com.ssdi.campuscare.model.Category;
import com.ssdi.campuscare.model.Provider;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
@SpringBootTest
public class ProviderServiceTest {

	@Injectable
	private IProviderDao providerDao;
	@Injectable
	private Category category;

	@Tested
	private ProviderService providerService;

	@Test
	public void testVerifyLogin() {

		// New provider with 'username' and 'password' is created
		Provider provider = new Provider();
		provider.setUserName("username");
		provider.setPassword("password");

		// Expectation: The verifyLogin method of Provider DAO should get invoked
		new Expectations() {
			{
				providerDao.verifyLogin(provider.getUserName(), provider.getPassword());
			}
		};

		// When: When the verifyLogin method is invoked from Service layer, it should
		// internally called verifyLogin method of ProviderDAO.
		providerService.verifyLogin(provider.getUserName(), provider.getPassword());

	}

	@Test
	public void testCreateProvider() {
		// New provider with below attributes is created
		Provider provider = new Provider();
		provider.setUserName("username");
		provider.setFirstName("Margeret");
		provider.setLastName("Spyro");
		provider.setEmail("hot@hotmail.com");
		provider.setPassword("password");

		// Expectation: The createProvider method of Provider DAO should get invoked
		new Expectations() {
			{
				providerDao.createProvider(provider);
			}
		};

		// When: When the createProvider method is invoked from Service layer, it should
		// internally called createProvider method of ProviderDAO.
		providerService.createProvider(provider);
	}

	
	@Test
	public void testGetAllProviderNames() {
		JSONArray arr = new JSONArray();
		
		JSONObject obj_1 = new JSONObject();
		obj_1.put("fullname","Kush Shah");
		obj_1.put("email","kshah44@campuscare.com");
		obj_1.put("userName","shahkush18");
		obj_1.put("providerId","5");
		arr.put(obj_1);
		
		JSONObject obj_2 = new JSONObject();
		obj_2.put("fullname","Heli Choksi");
		obj_2.put("email","h44choksi@campuscare.com");
		obj_2.put("userName","heli18");
		obj_2.put("providerId","7");
		arr.put(obj_2);

		// Expectation: The getAllProviderNames method of Provider DAO should get invoked
		new Expectations() {
			{
				providerDao.getAllProviderNames(); result = arr;
			}
		};

		// When: When the getAllProviderNames method is invoked from Service layer, it should
		// internally called getAllProviderNames method of ProviderDAO.
		JSONArray expected_arr = providerService.getAllProviderNames();

		new Verifications() {
			{
				assertEquals(2, expected_arr.length());
				assertEquals("shahkush18",  ((JSONObject) expected_arr.get(0)).get("userName"));
				assertEquals("heli18",  ((JSONObject) expected_arr.get(1)).get("userName"));
			}
		};
	}
	
	
	@Test
	public void testgetAllProviders() {
		List<Provider> providerList = new ArrayList<Provider>();
		providerList.add(new Provider(1, "shashi", "Shashi","Jaiswal", "shashi@gmail.com", "password11"));
		providerList.add(new Provider(1, "kush", "Kush","Shah", "Kush@gmail.com", "password22"));
		
		// Expectation: The getAllProviders method of Provider DAO should get invoked
		new Expectations() {
			{
				providerDao.getAllProviders(); result = providerList;
			}
		};

		// When: When the getAllProviderNames method is invoked from Service layer, it should
		// internally called getAllProviderNames method of ProviderDAO.
		List<Provider> expected_providerList = providerService.getAllProviders();

		new Verifications() {
			{
				assertEquals(2, expected_providerList.size());
				assertEquals("Shashi", expected_providerList.get(0).getFirstName());
				assertEquals("Jaiswal", expected_providerList.get(0).getLastName());
				assertEquals("Kush", expected_providerList.get(1).getFirstName());
				assertEquals("Shah", expected_providerList.get(1).getLastName());
			}
		};
	}
	
	@Test
	public void testProviderProfile() {
		Provider provider = new Provider(1, "shashi", "Shashi","Jaiswal", "shashi@gmail.com", "password11");
		
		
		// Expectation: The providerProfile method of Provider DAO should get invoked
		new Expectations() {
			{
				providerDao.providerProfile(provider.getUserName()); result = provider;
			}
		};

		// When: When the providerProfile method is invoked from Service layer, it should
		// internally called providerProfile method of ProviderDAO.
		Provider returned_provider = providerService.providerProfile(provider.getUserName());

		new Verifications() {
			{
				assertEquals("Shashi", returned_provider.getFirstName());
				assertEquals("Jaiswal", returned_provider.getLastName());
				assertEquals("shashi@gmail.com", returned_provider.getEmail());
			}
		};
	}
	
	@Test
	public void testGetProviderById() {
		Provider provider = new Provider(1, "shahkush18", "Kush","Shah", "kshah44@uncc.edu", "1234");
		
		
		// Expectation: The providerProfile method of Provider DAO should get invoked
		new Expectations() {
			{
				providerDao.getProviderById(provider.getProviderId()); result = provider;
			}
		};

		// When: When the providerProfile method is invoked from Service layer, it should
		// internally called providerProfile method of ProviderDAO.
		
		
		Provider prov = providerService.getProviderById(provider.getProviderId());

		new Verifications() {
			{
				assertEquals("Kush", prov.getFirstName());
				assertEquals("Shah", prov.getLastName());
				assertEquals("kshah44@uncc.edu", prov.getEmail());
			}
		};
	}
	@Test
	public void testGetProviderByCategory() {
		List<Provider> providerList = new ArrayList<Provider>();
		providerList.add(new Provider(1, "shashi", "Shashi","Jaiswal", "shashi@gmail.com", "password11"));
		providerList.add(new Provider(1, "kush", "Kush","Shah", "Kush@gmail.com", "password22"));
		
		category = new Category();
		category.setCategoryId(1);
		
		// Expectation: The getAllProviders method of Provider DAO should get invoked
		new Expectations() {
			{
				providerDao.getProviderByCategory(category.getCategoryId()); result = providerList;
			}
		};

		// When: When the getAllProviderNames method is invoked from Service layer, it should
		// internally called getAllProviderNames method of ProviderDAO.
		List<Provider> returnedProviderList = providerService.getProviderByCategoryId(category.getCategoryId());

		new Verifications() {
			{
				assertEquals(2, returnedProviderList.size());
				assertEquals("Shashi", returnedProviderList.get(0).getFirstName());
				assertEquals("Jaiswal", returnedProviderList.get(0).getLastName());
				assertEquals("Kush", returnedProviderList.get(1).getFirstName());
				assertEquals("Shah", returnedProviderList.get(1).getLastName());
			}
		};
	}
	
	

}

