package com.ssdi.campuscare.dao;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.ssdi.campuscare.model.Provider;

import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
@RunWith(JMockit.class)
@SpringBootTest
public class ProviderDaoTest 
{
	
	private DataSource dataSource;
	private static final String driverClassName = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/campuscaretest?useSSL=false";
	private static final String dbUsername = "root";
	private static final String dbPassword = "root";

	@Injectable
	private JdbcTemplate jdbcTemplate;
	
	@Injectable
	private RowMapper<Provider> rowMapper;
	
	@Tested
	private ProviderDao providerDao;
	
	private Provider provider;

	public DriverManagerDataSource getSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(dbPassword);
		return dataSource;
	}

	
	@Test
	public void testFindProviderByUsername() {
		
		DataSource ds= getSource();
		JdbcTemplate jt = new JdbcTemplate(ds);

		String sql1 = "delete from provider where provider_id <> ?";
		jt.update(sql1,0);
		
		String sql2 = "insert into provider (username, firstname, lastname, email, password) values ('shashi','Shashikant','Jaiswal', 'shashi@gmail.com', 'Password123')";
		jt.update(sql2);
		
		providerDao = new ProviderDao(jt);
		
		new Verifications() {
			{
				assertEquals(true, providerDao.findProviderByUsername("shashi"));
			}
		};
	}


	
	@Test
	public void testFindProviderByEmail() {
		DataSource ds= getSource();
		JdbcTemplate jt = new JdbcTemplate(ds);

		String sql1 = "delete from provider where provider_id <> ?";
		jt.update(sql1,0);
		
		String sql2 = "insert into provider (username, firstname, lastname, email, password) values ('shashi','Shashikant','Jaiswal', 'shashi@gmail.com', 'Password123')";
		jt.update(sql2);
		
		providerDao = new ProviderDao(jt);
		
		new Verifications() {
			{
				assertEquals(true, providerDao.findProviderByEmail("shashi@gmail.com"));
			}
		};
}
	
	@Test
	public void testGetAllProviders() {
		
		DataSource ds= getSource();
		JdbcTemplate jt = new JdbcTemplate(ds);
		
		String sql1 = "delete from provider where provider_id <> ?";
		jt.update(sql1,0);
		
		String sql2 = "insert into provider (username, firstname, lastname, email, password) values ('shashi','Shashikant','Jaiswal', 'shashi@gmail.com', 'Password123')";
		jt.update(sql2);
		
		String sql3 = "insert into provider (username, firstname, lastname, email, password) values ('kush','Kush','Shah', 'kush@gmail.com', 'Password222')";
		jt.update(sql3);
		
		providerDao = new ProviderDao(jt);
		List<Provider> providerList = new ArrayList<Provider>();
		providerList = providerDao.getAllProviders();
		//providerList.add(new Provider("shashi","Shashikant","Jaiswal", "shashi@gmail.com", "Password123"));
		//providerList.add(new Provider("kush","Kush","Shah", "kush@gmail.com", "Password222"));
		
	
		String a = providerList.get(0).getFirstName();
		String b = providerList.get(1).getFirstName();
		
		new Verifications() {
			{
				
				assertEquals("Shashikant", a);
				assertEquals("Kush", b);
			}
		};
	}

	
	
	@Test
	public void testVerifyLogin()
	{
		Provider provider = new Provider();
		provider.setUserName("username");
		provider.setPassword("password");
		String sql1 = "SELECT count(1) FROM consumer where username = ? and password = ?";
		int count = jdbcTemplate.queryForObject(sql1, Integer.class, provider.getUserName(), provider.getPassword());
		if(count == 1)
		{
		String sql2 = "select username, firstname, lastname, email, password from consumer where username = ? and password = ?";
		rowMapper = new ProviderRowMapper();
		Provider provider1 = jdbcTemplate.queryForObject(sql2, rowMapper, provider.getUserName(), provider.getPassword());
		new Verifications()
		{{
			assertEquals(provider1, providerDao.verifyLogin(provider.getUserName(), provider.getPassword()));
		}};
		}
	}
	@Test
	public void testCreateProvider()
	{
		Provider provider = new Provider("username", "Margeret", "Spyro", "hot@hotmail.com", "password");
		String sql = "INSERT INTO provider (username, firstname, lastname, email, password) values (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, provider.getUserName(), provider.getFirstName(), provider.getLastName(),
				provider.getEmail(), provider.getPassword());
		new Verifications()
		{{
			assertEquals(provider, providerDao.createProvider(provider));
		}};
	}
	@Test
	public void testFindProviderById() {
		
		DataSource ds= getSource();
		JdbcTemplate jt = new JdbcTemplate(ds);

		String sql1 = "delete from provider where provider_id <> ?";
		jt.update(sql1,0);
		
		String sql2 = "insert into provider (provider_id,username, firstname, lastname, email, password) values (1,'shashi','Shashikant','Jaiswal', 'shashi@gmail.com', 'Password123')";
		jt.update(sql2);
		
		providerDao = new ProviderDao(jt);
		
		Provider returned_provider = providerDao.getProviderById(1);
		
		new Verifications() {
			{
				assertEquals(1,returned_provider.getProviderId());
				assertEquals("shashi",returned_provider.getUserName());
				assertEquals("Shashikant",returned_provider.getFirstName());
				assertEquals("Jaiswal",returned_provider.getLastName());
				assertEquals("shashi@gmail.com",returned_provider.getEmail());
				assertEquals("Password123",returned_provider.getPassword());
				
				
			}
		};
	}	
	@Test
	public void testGetProviderById() {
		
		DataSource ds= getSource();
		JdbcTemplate jt = new JdbcTemplate(ds);

		String sql1 = "delete from provider where provider_id <> ?";
		jt.update(sql1,0);
		
		String sql2 = "insert into provider (provider_id,username, firstname, lastname, email, password) values (1,'shashi','Shashikant','Jaiswal', 'shashi@gmail.com', 'Password123')";
		jt.update(sql2);
		
		providerDao = new ProviderDao(jt);
		
		Provider returned_provider = providerDao.getProviderById(1);
		
		new Verifications() {
			{
				assertEquals(1,returned_provider.getProviderId());
				assertEquals("shashi",returned_provider.getUserName());
				assertEquals("Shashikant",returned_provider.getFirstName());
				assertEquals("Jaiswal",returned_provider.getLastName());
				assertEquals("shashi@gmail.com",returned_provider.getEmail());
				assertEquals("Password123",returned_provider.getPassword());
				
				
			}
		};
	}
	@Test
	public void testGetProviderByCategory() {
		
		DataSource ds= getSource();
		JdbcTemplate jt = new JdbcTemplate(ds);

		String sql1 = "delete from provider where provider_id <> ?";
		jt.update(sql1,0);
		
		String sql2 = "delete from category where category_id <> ?";
		jt.update(sql2,0);
		
		String sql3 = "delete from provider_category where provider_id <> ?";
		jt.update(sql3,0);
		
		String sql7= "insert into provider (provider_id,username, firstname, lastname, email, password) values (1,'shahkush18','Kush','Shah', 'kshah44@uncc.edu', '1234')";
		jt.update(sql7);
		
		String sql4 = "insert into provider (provider_id,username, firstname, lastname, email, password) values (2,'shashi','Shashikant','Jaiswal', 'shashi@gmail.com', 'Password123')";
		jt.update(sql4);
		
		String sql5 = "insert into category(category_id,category_name) values(1,'Music')";
		jt.update(sql5);
		
		String sql6 = "insert into provider_category (category_id,provider_id) values(1,1)";
		jt.update(sql6);
		
		String sql8 = "insert into provider_category (category_id,provider_id) values(1,2)";
		jt.update(sql8);
		
		providerDao = new ProviderDao(jt);
		
		List<Provider> returned_list = providerDao.getProviderByCategory(1);
		
		new Verifications() {
			{
				assertEquals(2,returned_list.size());
				assertEquals("shahkush18",returned_list.get(0).getUserName());
				assertEquals("Kush",returned_list.get(0).getFirstName());
				assertEquals("Shah",returned_list.get(0).getLastName());
				assertEquals(1,returned_list.get(0).getProviderId());
				assertEquals("shashi",returned_list.get(1).getUserName());
				assertEquals("Shashikant",returned_list.get(1).getFirstName());
				assertEquals("Jaiswal",returned_list.get(1).getLastName());
				assertEquals(2,returned_list.get(1).getProviderId());
				
			
				
			}
		};
	}
	
}
