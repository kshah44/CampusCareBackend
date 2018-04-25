package com.ssdi.campuscare.dao;


import java.util.List;

//import org.json.JSONArray;

import com.ssdi.campuscare.model.*;

public interface ICategoryDao 
{	
	public List<Category> getAllCategories();
	public List<Category> getProviderCategories(int provider_id);
	public List<Category> addProviderCategory(int provider_id, int category_id);
	public List<Category> removeProviderCategory(int provider_id, int category_id);
}