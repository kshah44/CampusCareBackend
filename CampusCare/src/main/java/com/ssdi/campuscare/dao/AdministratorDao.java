package com.ssdi.campuscare.dao;

import java.util.List;

import com.ssdi.campuscare.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ssdi.campuscare.model.Administrator;

@Repository
public class AdministratorDao implements IAdministratorDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean findAdministratorByUsername(String username) {
		String sql = "SELECT count(1) FROM administrator where username = ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, username);

		if (count == 1) { 
			return true; 
		}
		else {
			return false;
		}
		
	}
	
	@Override
	public boolean findAdministratorByEmail(String email) {
		String sql = "SELECT count(1) FROM administrator where email = ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, email);

		if (count == 1) { 
			return true; 
		}
		else {
			return false;
		}

	}

	
	@Override
	public List<Administrator> getAllAdministrators() {

		String sql = "select username, firstname, lastname, email, password from administrator";
		RowMapper<Administrator> rowMapper = new AdministratorRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public Administrator verifyLogin(String username, String password) 
	{

		String sql1 = "SELECT count(1) FROM administrator where username = ? and password = ?";
		int count = jdbcTemplate.queryForObject(sql1, Integer.class, username, password);

		if (count == 1) {
			String sql2 = "select username, firstname, lastname, email, password from provider where username = ? and password = ?";
			RowMapper<Administrator> rowMapper = new AdministratorRowMapper();
			Administrator administrator = jdbcTemplate.queryForObject(sql2, rowMapper, username, password);
			return administrator;
		} 
		else 
		{
			Administrator administrator = new Administrator();
			return administrator;
		}

	}

	
	

}
