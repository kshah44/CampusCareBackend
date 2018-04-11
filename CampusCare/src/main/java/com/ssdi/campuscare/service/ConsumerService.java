package com.ssdi.campuscare.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssdi.campuscare.dao.*;
import com.ssdi.campuscare.model.Consumer;

import com.ssdi.campuscare.model.*;

@Service
public class ConsumerService implements IConsumerService {

	@Autowired
	private IConsumerDao consumerdao;

	@Override
	public List<Consumer> getAllConsumers() {
		return consumerdao.getAllConsumers();
	}

	@Override
	public Consumer verifyLogin(String username, String password) {
		return consumerdao.verifyLogin(username, password);
	}

	@Override
	public Consumer createConsumer(Consumer consumer) {
		System.out.println("In ConsumerService::createConsumer");
		if (consumerdao.findConsumerByUsername(consumer.getUserName()) == false
			&& consumerdao.findConsumerByEmail(consumer.getEmail()) == false)
		{
			return consumerdao.createConsumer(consumer);
		}
		else {
			Consumer consumer1 = new Consumer();
			return consumer1;
		}
			
			
		
	}

}
