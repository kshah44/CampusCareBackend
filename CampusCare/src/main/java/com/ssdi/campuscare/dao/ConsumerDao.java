package com.ssdi.campuscare.dao;

import java.util.List;
import java.util.Map;

import com.ssdi.campuscare.model.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ssdi.campuscare.model.Consumer;

@Repository
public class ConsumerDao implements IConsumerDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean findConsumerByUsername(String username) {
		String sql = "SELECT count(1) FROM consumer where username = ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, username);
		
		if (count == 1) { 
			return true; 
		}
		else {
			return false;
		}
		
	}
	
	@Override
	public boolean findConsumerByEmail(String email) {
		String sql = "SELECT count(1) FROM consumer where email = ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, email);

		if (count == 1) { 
			return true; 
		}
		else {
			return false;
		}

	}

	
	
	@Override
	public List<Consumer> getAllConsumers() {

		String sql = "select username, firstname, lastname, email, password from consumer";
		RowMapper<Consumer> rowMapper = new ConsumerRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}
	

	@Override
	public Consumer verifyLogin(String username, String password) {

		String sql1 = "SELECT count(1) FROM consumer where username = ? and password = ?";
		int count = jdbcTemplate.queryForObject(sql1, Integer.class, username, password);

		if (count == 1) {
			String sql2 = "select username, firstname, lastname, email, password from consumer where username = ? and password = ?";
			RowMapper<Consumer> rowMapper = new ConsumerRowMapper();
			Consumer consumer = jdbcTemplate.queryForObject(sql2, rowMapper, username, password);
			return consumer;
		} else {
			Consumer consumer = new Consumer();
			return consumer;
		}

	}

	@Override
	public Consumer createConsumer(Consumer consumer) {
		// Add Consumer
		String sql = "INSERT INTO consumer (username, firstname, lastname, email, password) values (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, consumer.getUserName(), consumer.getFirstName(), consumer.getLastName(),
				consumer.getEmail(), consumer.getPassword());
		return consumer;
	}

	@Override
	public Consumer consumerProfile(String username) {
		String sql = "select * from consumer where username = ?";
		RowMapper<Consumer> rowMapper = new ConsumerRowMapper();
		Consumer consumer = jdbcTemplate.queryForObject(sql, rowMapper,username);
		return consumer;
	}
	
	@Override
	public JSONArray getAllConsumerNames() {
		JSONArray arr = new JSONArray();
		String sql = "SELECT concat(firstname,' ',lastname) as fullname from consumer";
		List<Map<String,Object>> result = jdbcTemplate.queryForList(sql);
		for(Map res:result) {
			JSONObject obj = new JSONObject();
			obj.put("fullname", res.get("fullname"));
			arr.put(obj);
		}
		
		return arr;
		
	}
	

}
