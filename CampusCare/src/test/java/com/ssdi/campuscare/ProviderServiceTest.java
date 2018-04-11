package com.ssdi.campuscare;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssdi.campuscare.dao.IProviderDao;
import com.ssdi.campuscare.model.Provider;
import com.ssdi.campuscare.service.ProviderService;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
@RunWith(JMockit.class)
@SpringBootTest
public class ProviderServiceTest 
{
	@Injectable
	private IProviderDao providerDao;
	
	@Tested
	private ProviderService providerService;
	
	@Test
	public void testVerifyLogin()
	{
		Provider provider = new  Provider();
		provider.setUserName("username");
		provider.setPassword("password");
		new Expectations()
		{{
			providerDao.verifyLogin(provider.getUserName(), provider.getPassword());
		}};
		
		providerService.verifyLogin(provider.getUserName(), provider.getPassword());
	}
	
	@Test
	public void testCreateProvider()
	{
		Provider provider = new  Provider();
		provider.setUserName("username");
		provider.setFirstName("Margeret");
		provider.setLastName("Spyro");
		provider.setEmail("hot@hotmail.com");
		provider.setPassword("password");
		new Expectations()
		{{
			providerDao.createProvider(provider);
		}};
		
		providerService.createProvider(provider);
	}
}
