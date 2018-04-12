package com.ssdi.campuscare.service;

import java.util.List;
import com.ssdi.campuscare.model.*;


public interface ICategoryService {

	public List<Category> getAllCategories();
	public List<Category> getProviderCategories(int provider_id);
	public List<Category> addProviderCategory(int provider_id, int category_id);
	public List<Category> removeProviderCategory(int provider_id, int category_id);

}