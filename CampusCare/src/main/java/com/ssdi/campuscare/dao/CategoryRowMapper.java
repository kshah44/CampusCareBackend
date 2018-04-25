package com.ssdi.campuscare.dao;

import org.springframework.jdbc.core.RowMapper;
import com.ssdi.campuscare.model.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRowMapper implements RowMapper<Category>{

	public Category mapRow(ResultSet row, int rowNum) throws SQLException {
		Category category = new Category();
		category.setCategoryId(row.getInt("category_id"));
		category.setCategoryName(row.getString("category_name"));
		return category;
	}
}
