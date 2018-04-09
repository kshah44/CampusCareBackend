package com.ssdi.campuscare.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ssdi.campuscare.service.ConsumerService;
import com.ssdi.campuscare.model.Consumer;
@CrossOrigin(origins = "*")
@RestController
public class ConsumerController {
	
	@Autowired
	private ConsumerService consumerservice;

	
	@RequestMapping("/consumers")
	public List<Consumer> getAllConsumers(){
		return consumerservice.getAllConsumers();
	}
	@RequestMapping("/consumername")
	public String getAllProviderNames(){
		System.out.println(consumerservice.getAllConsumerNames());
		return consumerservice.getAllConsumerNames().toString();
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/loginconsumer")
	
	public Consumer verifyLogin(@RequestBody Consumer consumer) {
		return consumerservice.verifyLogin(consumer.getUserName(), consumer.getPassword());
		
	}
	@RequestMapping(method=RequestMethod.POST, value="/consumerprofile")
	
	public Consumer consumerProfile(@RequestBody Consumer consumer) {
		return consumerservice.consumerProfile(consumer.getUserName());
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/createconsumer")
	public Consumer createConsumer(@RequestBody Consumer consumer) {
		return consumerservice.createConsumer(consumer);
		
	}
}

