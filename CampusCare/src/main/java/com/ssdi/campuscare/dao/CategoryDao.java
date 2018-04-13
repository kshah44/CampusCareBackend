package com.ssdi.campuscare.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssdi.campuscare.model.*;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ssdi.campuscare.model.Category;

@Repository
public class CategoryDao implements ICategoryDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
/*	public CategoryDao() {
		
	}
	
	public CategoryDao(JdbcTemplate jdbcTemplate) {
		//super();
		this.jdbcTemplate = jdbcTemplate;
	}
*/

	@Override
	public List<Category> getAllCategories() {

		System.out.println("Inside CategoryDao : Inside getAllCategories method");
		String sql = "select category_id, category_name from category";
		RowMapper<Category> rowMapper = new CategoryRowMapper();
		System.out.println(jdbcTemplate.getDataSource());
		return this.jdbcTemplate.query(sql, rowMapper);
	}

	@Override
	public List<Category> getProviderCategories(int provider_id) {

		System.out.println("getProviderCategories");

		String sql1 = "select count(1) from provider_category a, category b where a.category_id = b.category_id and a.provider_id = ?";
		int count = jdbcTemplate.queryForObject(sql1, Integer.class, provider_id);

		if (count > 0) {
			String sql2 = "select b.category_id, b.category_name from provider_category a, category b where a.category_id = b.category_id and a.provider_id = ?";
			RowMapper<Category> rowMapper = new CategoryRowMapper();
			List<Category> category = jdbcTemplate.query(sql2, rowMapper, provider_id);
			return category;
		} else {
			List<Category> category = new ArrayList<Category>();
			return category;
		}
	}

	@Override
	public List<Category> addProviderCategory(int provider_id, int category_id) {
		String sql = "INSERT INTO provider_category (provider_id, category_id) values (?, ?)";
		jdbcTemplate.update(sql, provider_id, category_id );
		
		return getProviderCategories(provider_id);
	}

	@Override
	public List<Category> removeProviderCategory(int provider_id, int category_id) {
		String sql = "delete from provider_category where provider_id = ? and category_id = ?;";
		jdbcTemplate.update(sql, provider_id, category_id );
		
		return getProviderCategories(provider_id);
	}

}
