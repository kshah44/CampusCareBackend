package com.ssdi.campuscare.dao;


import java.util.List;

//import org.json.JSONArray;

import com.ssdi.campuscare.model.*;

public interface ICategoryDao {
	
	public List<Category> getAllCategories();
	public List<Category> getProviderCategories(int provider_id);
	public List<Category> addProviderCategory(int provider_id, int category_id);
	public List<Category> removeProviderCategory(int provider_id, int category_id);
	
	//public JSONArray getAllConsumerNames();
	//public Consumer verifyLogin(String username, String password);
	//public Consumer createConsumer(Consumer consumer);
	//public boolean findConsumerByUsername(String username);
	//public boolean findConsumerByEmail(String email);
	//public Consumer consumerProfile(String username);

}