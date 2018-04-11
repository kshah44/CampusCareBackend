package com.ssdi.campuscare;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssdi.campuscare.dao.IConsumerDao;
import com.ssdi.campuscare.model.Consumer;
import com.ssdi.campuscare.service.ConsumerService;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
@RunWith(JMockit.class)
@SpringBootTest
public class ConsumerServiceTest 
{
	@Injectable
	private IConsumerDao consumerDao;
	
	@Tested
	private ConsumerService consumerService;
	
	@Test
	public void testVerifyLogin()
	{
		Consumer consumer = new  Consumer();
		consumer.setUserName("username");
		consumer.setPassword("password");
		new Expectations()
		{{
			consumerDao.verifyLogin(consumer.getUserName(), consumer.getPassword());
		}};
		
		consumerService.verifyLogin(consumer.getUserName(), consumer.getPassword());
	}
	
	@Test
	public void testCreateConsumer()
	{
		Consumer consumer = new  Consumer();
		consumer.setUserName("username");
		consumer.setFirstName("Margeret");
		consumer.setLastName("Spyro");
		consumer.setEmail("hot@hotmail.com");
		consumer.setPassword("password");
		new Expectations()
		{{
			consumerDao.createConsumer(consumer);
		}};
		
		consumerService.createConsumer(consumer);
	}
}
