package com.ssdi.campuscare.service;

import java.util.List;
import com.ssdi.campuscare.model.*;


public interface IConsumerService {
	
	public List<Consumer> getAllConsumers();
	public Consumer verifyLogin(String username, String password);
	public Consumer createConsumer(Consumer consumer);
	public Consumer consumerProfile(String userrname);

}
