package com.ssdi.campuscare.dao;

import java.util.List;


import com.ssdi.campuscare.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class RequestDao implements IRequestDao 
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public RequestDao()
	{}
	public RequestDao (JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate = jdbcTemplate;
	}
	@Override
	public List<Request> getAllRequests() 
	{
		String sql = "select * from request";
		RowMapper<Request> rowMapper = new RequestRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<Request> getProviderRequest(int provider_id) 
	{
		String sql = "select * from request where provider_id=?";
		RowMapper<Request> rowMapper = new RequestRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, provider_id);
	}

	@Override
	public List<Request> getConsumerRequest(int consumer_id) 
	{
		String sql = "select * from request where consumer_id=?";
		RowMapper<Request> rowMapper = new RequestRowMapper();
		return this.jdbcTemplate.query(sql, rowMapper, consumer_id);
	}

	@Override
	public Request createRequest(Request service) 
	{
		String sql = "INSERT INTO request (consumer_id, provider_id, category_id, request_status) values (?, ?, ?, ?)";
		jdbcTemplate.update(sql, service.getConsumerId(), service.getProviderId(), service.getCategoryId(), service.getStatus());
		return service;
	}

	@Override
	public List<Request> cancelRequest(int consumer_id, int provider_id, int category_id) {
		String sql = "delete from request where consumer_id = ? and provider_id = ? and category_id = ?;";
		jdbcTemplate.update(sql, consumer_id, provider_id, category_id );
		return getConsumerRequest(consumer_id);
	}

}
