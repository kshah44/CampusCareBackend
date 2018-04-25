package com.ssdi.campuscare.dao;

import java.util.List;

import com.ssdi.campuscare.model.*;

public interface IAdministratorDao {

	public List<Administrator> getAllAdministrators();
	public Administrator verifyLogin(String username, String password);
	//public Administrator createAdminisrator(Administrator administrator);
	public boolean findAdministratorByUsername(String username);
	public boolean findAdministratorByEmail(String email);
	
}
