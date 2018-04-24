package com.ssdi.campuscare.service;

import java.util.List;
import com.ssdi.campuscare.model.*;


public interface IProviderService {
	
	public List<Provider> getAllProviders();
	public Provider verifyLogin(String username, String password);
	public Provider createProvider(Provider provider);
	public Provider getProviderById(int id);
	public List<Provider> getProviderByCategoryId(int categoryId);

}