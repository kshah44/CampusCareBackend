package com.ssdi.campuscare.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
public class ConsumerServiceTest {

	@Injectable
	private IConsumerDao consumerDao;

	@Tested
	private ConsumerService consumerService;

	@Test
	public void testVerifyLogin() {

		// New consumer with 'username' and 'password' is created
		Consumer consumer = new Consumer();
		consumer.setUserName("username");
		consumer.setPassword("password");

		// Expectation: The verifyLogin method of Consumer DAO should get invoked
		new Expectations() {
			{
				consumerDao.verifyLogin(consumer.getUserName(), consumer.getPassword());
			}
		};

		// When: When the verifyLogin method is invoked from Service layer, it should
		// internally called verifyLogin method of ConsumerDAO.
		consumerService.verifyLogin(consumer.getUserName(), consumer.getPassword());

	}

	@Test
	public void testCreateConsumer() {
		// New consumer with below attributes is created
		Consumer consumer = new Consumer();
		consumer.setUserName("username");
		consumer.setFirstName("Margeret");
		consumer.setLastName("Spyro");
		consumer.setEmail("hot@hotmail.com");
		consumer.setPassword("password");

		// Expectation: The createConsumer method of Consumer DAO should get invoked
		new Expectations() {
			{
				consumerDao.createConsumer(consumer);
			}
		};

		// When: When the createConsumer method is invoked from Service layer, it should
		// internally called createConsumer method of ConsumerDAO.
		consumerService.createConsumer(consumer);
	}

}
