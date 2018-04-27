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

@Repository
public class RequestDao implements IRequestDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public RequestDao() {
	}

	public RequestDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

//	@Override
//	public List<Request> getAllRequests() {
//		String sql = "select * from request";
//		RowMapper<Request> rowMapper = new RequestRowMapper();
//		return this.jdbcTemplate.query(sql, rowMapper);
//	}

	/*
	 * @Override public List<Request> getProviderRequest(int provider_id) { String
	 * sql = "select * from request where provider_id=?"; RowMapper<Request>
	 * rowMapper = new RequestRowMapper(); return this.jdbcTemplate.query(sql,
	 * rowMapper, provider_id); }
	 */
	@Override
	public JSONArray getProviderRequest(int provider_id) {
		JSONArray arr = new JSONArray();
		String sql = "select a.request_id, concat(b.firstname,' ',b.lastname) as fullname, c.category_name, a.request_status\r\n"
				+ "from request a, consumer b, category c\r\n" + "where a.consumer_id = b.consumer_id\r\n"
				+ "and a.category_id = c.category_id\r\n" + "and a.provider_id = ?";
		List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, provider_id);
		for (Map res : result) {
			JSONObject obj = new JSONObject();
			obj.put("fullname", res.get("fullname"));
			obj.put("categoryName", res.get("category_name"));
			obj.put("status", res.get("request_status"));
			obj.put("requestId", res.get("request_id"));
			arr.put(obj);
		}
		return arr;
	}

	/*
	 * @Override public List<Request> getConsumerRequest(int consumer_id) { String
	 * sql = "select * from request where consumer_id=?"; RowMapper<Request>
	 * rowMapper = new RequestRowMapper(); return this.jdbcTemplate.query(sql,
	 * rowMapper, consumer_id); }
	 */
	@Override
	public JSONArray getConsumerRequest(int consumer_id) {
		JSONArray arr = new JSONArray();
		String sql = "select a.request_id, concat(b.firstname,' ',b.lastname) as fullname, c.category_name, a.request_status\r\n"
				+ "from request a, provider b, category c\r\n" + "where a.provider_id = b.provider_id\r\n"
				+ "and a.category_id = c.category_id\r\n" + "and a.consumer_id = ?";
		List<Map<String, Object>> result = jdbcTemplate.queryForList(sql, consumer_id);
		for (Map res : result) {
			JSONObject obj = new JSONObject();
			obj.put("fullname", res.get("fullname"));
			obj.put("categoryName", res.get("category_name"));
			obj.put("status", res.get("request_status"));
			obj.put("requestId", res.get("request_id"));
			arr.put(obj);
		}
		return arr;
	}

	@Override
	public Request createRequest(Request service) {
		String sql = "INSERT INTO request (consumer_id, provider_id, category_id, request_status) values (?, ?, ?, ?)";
		jdbcTemplate.update(sql, service.getConsumerId(), service.getProviderId(), service.getCategoryId(),
				service.getStatus());
		return service;
	}

	/*
	@Override
	public List<Request> cancelRequest(int consumer_id, int provider_id, int category_id) {
		String sql = "delete from request where consumer_id = ? and provider_id = ? and category_id = ?;";
		jdbcTemplate.update(sql, consumer_id, provider_id, category_id);
		return getConsumerRequest(consumer_id);
	}
	*/
	@Override
	public JSONArray ConsumerUpdateRequestStatus (int request_id, int consumer_id, String status) {
		String sql = "update request set request_status = ? where request_id = ? ";
		jdbcTemplate.update(sql, status, request_id);
		return getConsumerRequest(consumer_id);
	}
	
	public JSONArray ProviderUpdateRequestStatus(int request_id, int provider_id, String status) {
		String sql = "update request set request_status = ? where request_id = ?";
		jdbcTemplate.update(sql, status, request_id);
		return getProviderRequest(provider_id);
	}
	/*
	@Override
	public JSONArray ProviderAcceptRequest(int request_id, int provider_id) {
		String sql = "update request set request_status = 'Accepted' where request_id = ?";
		jdbcTemplate.update(sql, request_id);
		return getProviderRequest(provider_id);
	}
	
	@Override
	public JSONArray ProviderRejectRequest(int request_id, int provider_id) {
		String sql = "update request set request_status = 'Rejected' where request_id = ?";
		jdbcTemplate.update(sql, request_id);
		return getProviderRequest(provider_id);
	}

	@Override
	public JSONArray ProviderCompleteRequest(int request_id, int provider_id) {
		String sql = "update request set request_status = 'Completed' where request_id = ?";
		jdbcTemplate.update(sql, request_id);
		return getProviderRequest(provider_id);
	}
	*/

	

}
