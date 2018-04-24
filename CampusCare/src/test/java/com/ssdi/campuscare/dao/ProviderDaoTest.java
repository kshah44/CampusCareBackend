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
	private static final String dbPassword = "1003";

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
	
}
