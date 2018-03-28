package com.ssdi.campuscare.dao;

import java.util.List;

import com.ssdi.campuscare.model.*;

public interface IProviderDao {
	
	public List<Provider> getAllProviders(); 
	public Provider verifyLogin(String username, String password);
	public Provider createProvider(Provider provider);
	public boolean findProviderByUsername(String username);
	public boolean findProviderByEmail(String email);

}
