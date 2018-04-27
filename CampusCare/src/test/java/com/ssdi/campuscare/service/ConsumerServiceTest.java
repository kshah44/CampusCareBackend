package com.ssdi.campuscare.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssdi.campuscare.dao.IConsumerDao;
import com.ssdi.campuscare.model.Consumer;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
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

	
	@Test
	public void testGetAllConsumerNames() {
		JSONArray arr = new JSONArray();
		
		JSONObject obj_1 = new JSONObject();
		obj_1.put("fullname","Kush Shah");
		obj_1.put("email","kshah44@campuscare.com");
		obj_1.put("userName","shahkush18");
		obj_1.put("consumerId","5");
		arr.put(obj_1);
		
		JSONObject obj_2 = new JSONObject();
		obj_2.put("fullname","Heli Choksi");
		obj_2.put("email","h44choksi@campuscare.com");
		obj_2.put("userName","heli18");
		obj_2.put("consumerId","7");
		arr.put(obj_2);

		// Expectation: The getAllConsumerNames method of Consumer DAO should get invoked
		new Expectations() {
			{
				consumerDao.getAllConsumerNames(); result = arr;
			}
		};

		// When: When the getAllConsumerNames method is invoked from Service layer, it should
		// internally called getAllConsumerNames method of ConsumerDAO.
		JSONArray expected_arr = consumerService.getAllConsumerNames();

		new Verifications() {
			{
				assertEquals(2, expected_arr.length());
				assertEquals("shahkush18",  ((JSONObject) expected_arr.get(0)).get("userName"));
				assertEquals("heli18",  ((JSONObject) expected_arr.get(1)).get("userName"));
			}
		};
	}
	
	
	@Test
	public void testgetAllConsumers() {
		List<Consumer> consumerList = new ArrayList<Consumer>();
		consumerList.add(new Consumer(1, "shashi", "Shashi","Jaiswal", "shashi@gmail.com", "password11"));
		consumerList.add(new Consumer(1, "kush", "Kush","Shah", "Kush@gmail.com", "password22"));
		
		// Expectation: The getAllConsumers method of Consumer DAO should get invoked
		new Expectations() {
			{
				consumerDao.getAllConsumers(); result = consumerList;
			}
		};

		// When: When the getAllConsumerNames method is invoked from Service layer, it should
		// internally called getAllConsumerNames method of ConsumerDAO.
		List<Consumer> expected_consumerList = consumerService.getAllConsumers();

		new Verifications() {
			{
				assertEquals(2, expected_consumerList.size());
				assertEquals("Shashi", expected_consumerList.get(0).getFirstName());
				assertEquals("Jaiswal", expected_consumerList.get(0).getLastName());
				assertEquals("Kush", expected_consumerList.get(1).getFirstName());
				assertEquals("Shah", expected_consumerList.get(1).getLastName());
			}
		};
	}
	
	@Test
	public void testConsumerProfile() {
		Consumer consumer = new Consumer(1, "shashi", "Shashi","Jaiswal", "shashi@gmail.com", "password11");
		
		
		// Expectation: The consumerProfile method of Consumer DAO should get invoked
		new Expectations() {
			{
				consumerDao.consumerProfile(consumer.getUserName()); result = consumer;
			}
		};

		// When: When the consumerProfile method is invoked from Service layer, it should
		// internally called consumerProfile method of ConsumerDAO.
		Consumer expectedConsumer = consumerService.consumerProfile(consumer.getUserName());

		new Verifications() {
			{
				assertEquals("Shashi", expectedConsumer.getFirstName());
				assertEquals("Jaiswal", expectedConsumer.getLastName());
				assertEquals("shashi@gmail.com", expectedConsumer.getEmail());
			}
		};
	}
	
	@Test
	public void testConsumerById() {
		Consumer consumer = new Consumer(1, "shahkush18", "Kush","Shah", "kshah44@uncc.edu", "password");
		
		
		// Expectation: The consumerProfile method of Consumer DAO should get invoked
		new Expectations() {
			{
				consumerDao.getConsumerById(consumer.getConsumerId()); result = consumer;
			}
		};

		// When: When the consumerProfile method is invoked from Service layer, it should
		// internally called consumerProfile method of ConsumerDAO.
		Consumer returned_consumer = consumerService.getConsumerById(consumer.getConsumerId());

		new Verifications() {
			{
				assertEquals("Kush", returned_consumer.getFirstName());
				assertEquals("Shah", returned_consumer.getLastName());
				assertEquals("kshah44@uncc.edu", returned_consumer.getEmail());
			}
		};
	}
	

}
