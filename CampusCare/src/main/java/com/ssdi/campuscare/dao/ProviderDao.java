package com.ssdi.campuscare.dao;

import java.util.List;

import com.ssdi.campuscare.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ssdi.campuscare.model.Provider;

@Repository
public class ProviderDao implements IProviderDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

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

		String sql = "select username, firstname, lastname, email, password from provider";
		RowMapper<Provider> rowMapper = new ProviderRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public Provider verifyLogin(String username, String password) {

		String sql1 = "SELECT count(1) FROM provider where username = ? and password = ?";
		int count = jdbcTemplate.queryForObject(sql1, Integer.class, username, password);

		if (count == 1) {
			String sql2 = "select username, firstname, lastname, email, password from provider where username = ? and password = ?";
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

}
