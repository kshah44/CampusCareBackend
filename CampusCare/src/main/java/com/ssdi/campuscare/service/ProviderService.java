package com.ssdi.campuscare.service;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssdi.campuscare.dao.*;
import com.ssdi.campuscare.model.*;


@Service
public class ProviderService implements IProviderService {

	@Autowired
	private IProviderDao providerdao;

	@Override
	public List<Provider> getAllProviders() {
		return providerdao.getAllProviders();
	}
	public JSONArray getAllProviderNames() {
		return providerdao.getAllProviderNames();
	}
	public Provider providerProfile(String username) {
		return providerdao.providerProfile(username);
	}


	@Override
	public Provider verifyLogin(String username, String password) {
		return providerdao.verifyLogin(username, password);
	}

	@Override
	public Provider createProvider(Provider provider) {
		
		if (providerdao.findProviderByUsername(provider.getUserName()) == false
			&& providerdao.findProviderByEmail(provider.getEmail()) == false)
		{
			return providerdao.createProvider(provider);
		}
		else {
			Provider provider1 = new Provider();
			return provider1;
		}
			
			
		
	}
	@Override
	public Provider getProviderById(int id) {
		// TODO Auto-generated method stub
		return providerdao.getProviderById(id);
	}
	@Override
	public List<Provider> getProviderByCategoryId(int categoryId) {
		
		return providerdao.getProviderByCategory(categoryId);
	}

}
