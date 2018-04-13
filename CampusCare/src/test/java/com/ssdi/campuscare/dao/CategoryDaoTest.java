package com.ssdi.campuscare.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.ssdi.campuscare.model.Category;

import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

@Repository
@RunWith(JMockit.class)
@SpringBootTest
public class CategoryDaoTest {

	private DataSource dataSource;
	private static final String driverClassName = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/campuscaretest?useSSL=false";
	private static final String dbUsername = "root";
	private static final String dbPassword = "1003";
	
	//@Injectable
	//private RowMapper<Category> rowMapper;
	
	
	//@Tested
	private CategoryDao categoryDao;

		
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}

	public DriverManagerDataSource getSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(dbPassword);
		return dataSource;
	}
	
	@Test
	public void testGetAllCategories() {

		DataSource ds= getSource();
		JdbcTemplate jt = new JdbcTemplate(ds);

		// Given: There are no categories in the table
		String sql = "delete from provider_category where provider_id <> ?";
		jt.update(sql,0);
				
		String sql2 = "delete from category where category_id <> ?";
		jt.update(sql2,0);
		
		String sql3 = "insert into category (category_id, category_name) values (1, \"Computers\"), (2, \"Academics\")"; 
		jt.update(sql3);
		
		System.out.println("Inside CategoryDaoTest : Before method call");
		categoryDao = new CategoryDao(jt);
		List<Category> categoryList = categoryDao.getAllCategories();
		System.out.println("Inside CategoryDaoTest : After method call");					  
		
		new Verifications() {{
			 assertEquals(2, categoryList.size());
			 assertEquals("Computers", categoryList.get(0).getCategoryName());
			 assertEquals("Academics", categoryList.get(1).getCategoryName());
			}};
	    } 

	@Test
	public void testGetProviderCategories() {

		DataSource ds= getSource();
		JdbcTemplate jt = new JdbcTemplate(ds);

		// Given: There are no categories in the table

		String sql2 = "delete from category where category_id <> ?";
		jt.update(sql2,0);
		
		String sql3 = "insert into category (category_id, category_name) values (1, \"Computers\"), (2, \"Academics\")"; 
		jt.update(sql3);
		
		String sql4 = "insert into provider_category (provider_id, category_id) values (4,1), (4,2)"; 
		jt.update(sql4);

		categoryDao = new CategoryDao(jt);
		List<Category> categoryList = categoryDao.getProviderCategories(4);
							  
		new Verifications() {{
			 assertEquals(2, categoryList.size());
			 assertEquals("Computers", categoryList.get(0).getCategoryName());
			 assertEquals("Academics", categoryList.get(1).getCategoryName());
			}};
	    } 
	

	
	@Test
	public void testAddProviderCategory() {

		DataSource ds= getSource();
		JdbcTemplate jt = new JdbcTemplate(ds);

		// Given: There are no categories in the table
			
		String sql = "delete from provider_category where provider_id <> ?";
		jt.update(sql,0);		
				
		String sql2 = "delete from category where category_id <> ?";
		jt.update(sql2,0);
		
		String sql3 = "insert into category (category_id, category_name) values (1, \"Computers\"), (2, \"Academics\")"; 
		jt.update(sql3);
		categoryDao = new CategoryDao(jt);
		List<Category> categoryList = categoryDao.addProviderCategory(2,2);
							  
		new Verifications() {{
			 assertEquals(1, categoryList.size());
			 assertEquals(2, categoryList.get(0).getCategoryId());
			 assertEquals("Academics", categoryList.get(0).getCategoryName());
			 
			}};
	    } 

	@Test
	public void testRemoveProviderCategory() {

		DataSource ds= getSource();
		JdbcTemplate jt = new JdbcTemplate(ds);

		// Given: There are no categories in the table
			
		String sql = "delete from provider_category where provider_id <> ?";
		jt.update(sql,0);		
				
		String sql2 = "delete from category where category_id <> ?";
		jt.update(sql2,0);
		
		String sql3 = "insert into category (category_id, category_name) values (1, \"Computers\"), (2, \"Academics\"),  (3, \"Swimming\")"; 
		jt.update(sql3);
		
		String sql4 = "insert into provider_category (provider_id, category_id) values (1, 1), (1, 2), (1,3)"; 
		jt.update(sql4);
		
		categoryDao = new CategoryDao(jt);
		List<Category> categoryList = categoryDao.removeProviderCategory(1,1);
							  
		new Verifications() {{
			 assertEquals(2, categoryList.size());
			 assertEquals(2, categoryList.get(0).getCategoryId());
			 assertEquals("Academics", categoryList.get(0).getCategoryName());
			 assertEquals(3, categoryList.get(1).getCategoryId());
			 assertEquals("Swimming", categoryList.get(1).getCategoryName());
			}};
	    } 

	
}
