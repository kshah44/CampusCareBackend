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
		providerService.getAllProviderNames();

		new Verifications() {
			{
				assertEquals(2, arr.length());
				assertEquals("shahkush18",  ((JSONObject) arr.get(0)).get("userName"));
				assertEquals("heli18",  ((JSONObject) arr.get(1)).get("userName"));
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
		providerService.getAllProviders();

		new Verifications() {
			{
				assertEquals(2, providerList.size());
				assertEquals("Shashi", providerList.get(0).getFirstName());
				assertEquals("Jaiswal", providerList.get(0).getLastName());
				assertEquals("Kush", providerList.get(1).getFirstName());
				assertEquals("Shah", providerList.get(1).getLastName());
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
		providerService.providerProfile(provider.getUserName());

		new Verifications() {
			{
				assertEquals("Shashi", provider.getFirstName());
				assertEquals("Jaiswal", provider.getLastName());
				assertEquals("shashi@gmail.com", provider.getEmail());
			}
		};
	}
	

}

