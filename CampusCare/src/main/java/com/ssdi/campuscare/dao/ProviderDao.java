package com.ssdi.campuscare.dao;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import com.ssdi.campuscare.model.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProviderDao implements IProviderDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
    public ProviderDao() {}
	
	public ProviderDao(JdbcTemplate jdbcTemplate) 
	{
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
	public boolean findProviderByUsername(String username) {
		String sql = "SELECT count(1) FROM provider where username = ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, username);

		if (count == 1) { 
			return true; 
		}
		else {
			return false;
		}
		
	}
	
	@Override
	public boolean findProviderByEmail(String email) {
		String sql = "SELECT count(1) FROM provider where email = ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, email);

		if (count == 1) { 
			return true; 
		}
		else {
			return false;
		}

	}

	
	
	@Override
	public List<Provider> getAllProviders() {

		String sql = "select provider_id, username, firstname, lastname, email, password from provider";
		RowMapper<Provider> rowMapper = new ProviderRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}
	@Override
	public JSONArray getAllProviderNames() {
		JSONArray arr = new JSONArray();
		String sql = "SELECT concat(firstname,' ',lastname) as fullname,email,username,provider_id from provider";
		List<Map<String,Object>> result = jdbcTemplate.queryForList(sql);
		for(Map res:result) {
			JSONObject obj = new JSONObject();
			obj.put("fullname", res.get("fullname"));
			obj.put("email", res.get("email"));
			obj.put("userName", res.get("userName"));
			obj.put("providerId", res.get("provider_id"));
			arr.put(obj);
		}
		
		return arr;
		
	}
	public Provider providerProfile(String username) {
		String sql = "select * from provider where username = ?";
		RowMapper<Provider> rowMapper = new ProviderRowMapper();
		Provider provider = jdbcTemplate.queryForObject(sql, rowMapper,username);
		return provider;
	}
	

	@Override
	public Provider verifyLogin(String username, String password) {

		String sql1 = "SELECT count(1) FROM provider where username = ? and password = ?";
		int count = jdbcTemplate.queryForObject(sql1, Integer.class, username, password);

		if (count == 1) {
			String sql2 = "select provider_id, username, firstname, lastname, email, password from provider where username = ? and password = ?";
			RowMapper<Provider> rowMapper = new ProviderRowMapper();
			Provider provider = jdbcTemplate.queryForObject(sql2, rowMapper, username, password);
			return provider;
		} else {
			Provider provider = new Provider();
			return provider;
		}

	}

	@Override
	public Provider createProvider(Provider provider) {
		// Add Provider
		String sql = "INSERT INTO provider (username, firstname, lastname, email, password) values (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, provider.getUserName(), provider.getFirstName(), provider.getLastName(),
				provider.getEmail(), provider.getPassword());
		return provider;
	}

	@Override
	public Provider getProviderById(int id) {
		String sql = "select * from provider where provider_id = ?";
		RowMapper<Provider> rowMapper = new ProviderRowMapper();
		Provider provider = jdbcTemplate.queryForObject(sql, rowMapper,id);
		return provider;
	}

}
