package com.ssdi.campuscare.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ssdi.campuscare.service.AdministratorService;
import com.ssdi.campuscare.model.Administrator;


@RestController
public class AdministratorController 
{

	private AdministratorService administratorservice;

	
	@RequestMapping("/administrators")
	public List<Administrator> getAllAdministrators()
	{
		return administratorservice.getAllAdministrators();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/loginadministrator")
	public Administrator verifyLogin(@RequestBody Administrator administrator) 
	{
		return administratorservice.verifyLogin(administrator.getUserName(), administrator.getPassword());
		
	}
		
	
}
