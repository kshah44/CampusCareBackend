package com.ssdi.campuscare.dao;

import java.util.List;

import org.json.JSONArray;

import com.ssdi.campuscare.model.*;

import java.util.List;

import com.ssdi.campuscare.model.*;

public interface IConsumerDao {
	
	public List<Consumer> getAllConsumers(); 
	public Consumer verifyLogin(String username, String password);
	public Consumer createConsumer(Consumer consumer);
	public boolean findConsumerByUsername(String username);
	public boolean findConsumerByEmail(String email);

}
