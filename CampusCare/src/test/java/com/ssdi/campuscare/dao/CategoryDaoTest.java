package com.ssdi.campuscare.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ssdi.campuscare.model.Category;
import com.ssdi.campuscare.model.Consumer;

import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
@SpringBootTest
public class CategoryDaoTest {
	@Injectable
	private JdbcTemplate jdbcTemplate;

	@Injectable
	private RowMapper<Consumer> rowMapper;

	@Tested
	private CategoryDao categoryDao;

	private Category category;
	
	@Test
	public void testGetAllCategories() {

		String sql = "delete from provider_category where provider_id <> ?";
		jdbcTemplate.update(sql,0);
	}

}
     

