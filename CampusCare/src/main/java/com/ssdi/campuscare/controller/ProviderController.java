package com.ssdi.campuscare.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ssdi.campuscare.service.ProviderService;
import com.ssdi.campuscare.model.Provider;

@RestController
public class ProviderController {
	
	@Autowired
	private ProviderService providerservice;

	
	@RequestMapping("/providers")
	public List<Provider> getAllProviders(){
		return providerservice.getAllProviders();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/loginprovider")
	public Provider verifyLogin(@RequestBody Provider provider) {
		return providerservice.verifyLogin(provider.getUserName(), provider.getPassword());
		
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/createprovider")
	public Provider createProvider(@RequestBody Provider provider) {
		return providerservice.createProvider(provider);
		
	}
}

