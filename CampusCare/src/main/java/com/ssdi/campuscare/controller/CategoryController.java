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
import com.ssdi.campuscare.service.CategoryService;
import com.ssdi.campuscare.model.Category;
import com.ssdi.campuscare.model.Consumer;
import com.ssdi.campuscare.model.Provider;
import org.json.JSONObject;

@CrossOrigin(origins = "*")
@RestController
public class CategoryController {

	@Autowired
	private CategoryService categoryservice;

	@RequestMapping("/categories")
	public List<Category> getAllCategories() {
		return categoryservice.getAllCategories();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/providercategories")
	public List<Category> getProviderCategories(@RequestBody Provider provider) {
		return categoryservice.getProviderCategories(provider.getProviderId());

	}

	@RequestMapping(method = RequestMethod.POST, value = "/addprovidercategory")
	public List<Category> addProviderCategory(@RequestBody String obj) {
		JSONObject json = new JSONObject(obj);
		return categoryservice.addProviderCategory(Integer.parseInt((String) json.get("providerId")),
				Integer.parseInt((String) json.get("categoryId")));
	}

	@RequestMapping(method = RequestMethod.POST, value = "/removeprovidercategory")
	public List<Category> removeProviderCategory(@RequestBody String obj) {
		System.out.println(obj);
		JSONObject json = new JSONObject(obj);
		return categoryservice.removeProviderCategory(Integer.parseInt((String) json.get("providerId")),
				Integer.parseInt((String) json.get("categoryId")));

	}

}
