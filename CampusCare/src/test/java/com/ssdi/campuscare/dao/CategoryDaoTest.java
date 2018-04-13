package com.ssdi.campuscare.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ssdi.campuscare.dao.CategoryDao;
import com.ssdi.campuscare.dao.CategoryRowMapper;
import com.ssdi.campuscare.model.Category;
import com.ssdi.campuscare.model.ICategory;

import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;
import mockit.Expectations;
import mockit.Verifications;

@Repository
@RunWith(JMockit.class)
@SpringBootTest
public class CategoryDaoTest {

	@Injectable
	private RowMapper<Category> rowMapper;
	
	@Injectable
	private JdbcTemplate jdbcTemplate;

	@Tested
	private CategoryDao categoryDao;

		
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetAllCategories() {

	        
		// Given: There are no categories in the table
		/*String sql1 = "delete from provider_category where provider_id <> ?";
		int i = jdbcTemplate.update(sql1,0);
				
		String sql2 = "delete from category where category_id <> ?";
		jdbcTemplate.update(sql2,0);
		
		String sql3 = "insert into category (category_name) values (\"Computers\"), (\"Academics\")"; 
		jdbcTemplate.update(sql3);
		*/
		System.out.println("Inside CategoryDaoTest : Before method call");
		List<Category> categoryList = categoryDao.getAllCategories();
		System.out.println("Inside CategoryDaoTest : After method call");							  
		
		new Verifications() {{
			assertEquals(10, categoryList.size());
			assertEquals("Computers", categoryList.get(0).getCategoryName());
			assertEquals("Academics", categoryList.get(1).getCategoryName());
			}};
	}

}
