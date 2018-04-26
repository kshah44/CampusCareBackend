package com.ssdi.campuscare.dao;

import org.springframework.jdbc.core.RowMapper;
import com.ssdi.campuscare.model.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestRowMapper implements RowMapper<Request> 
{
	public Request mapRow(ResultSet row, int rowNum) throws SQLException
	{
		Request service = new Request();
		service.setRequestId(row.getInt("request_id"));
		service.setConsumerId(row.getInt("consumer_id"));
		service.setProviderId(row.getInt("provider_id"));
		service.setCategoryId(row.getInt("category_id"));
		service.setStatus(row.getString("request_status"));
		return service;
	}
}