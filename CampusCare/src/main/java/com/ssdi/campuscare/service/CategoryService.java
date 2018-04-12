package com.ssdi.campuscare.service;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssdi.campuscare.dao.*;
import com.ssdi.campuscare.model.*;

@Service
public class CategoryService implements ICategoryService {

	@Autowired
	private ICategoryDao categorydao;

	@Override
	public List<Category> getAllCategories() {
		return categorydao.getAllCategories();
	}

	@Override
	public List<Category> getProviderCategories(int provider_id) {
		return categorydao.getProviderCategories(provider_id);
	}

	@Override
	public List<Category> addProviderCategory(int provider_id, int category_id) {
		return categorydao.addProviderCategory(provider_id, category_id);
	}

	@Override
	public List<Category> removeProviderCategory(int provider_id, int category_id) {
		return categorydao.removeProviderCategory(provider_id, category_id);
	}
}
