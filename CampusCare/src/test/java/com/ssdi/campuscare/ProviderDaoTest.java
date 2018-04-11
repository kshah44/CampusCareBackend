package com.ssdi.campuscare;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ssdi.campuscare.dao.ConsumerRowMapper;
import com.ssdi.campuscare.dao.ProviderDao;
import com.ssdi.campuscare.dao.ProviderRowMapper;
import com.ssdi.campuscare.model.Consumer;
import com.ssdi.campuscare.model.Provider;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
@RunWith(JMockit.class)
@SpringBootTest
public class ProviderDaoTest 
{
	@Injectable
	private JdbcTemplate jdbcTemplate;
	
	@Injectable
	private RowMapper<Provider> rowMapper;
	
	@Tested
	private ProviderDao providerDao;
	
	private Provider provider;
	
	@Test
	public void testFindProviderByUsername()
	{
		Provider provider = new Provider();
		provider.setUserName("username");
		String sql = "SELECT count(1) FROM provider where username = ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, provider.getUserName());
		new Verifications()
		{{
			assertEquals(false, providerDao.findProviderByUsername(provider.getUserName()));
		}};
	}
	@Test
	public void testFindProviderByEmail()
	{
		Provider provider = new Provider();
		provider.setEmail("hot@hotmail.com");
		String sql = "SELECT count(1) FROM provider where email = ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, provider.getEmail());
		new Verifications()
		{{
			assertEquals(false, providerDao.findProviderByEmail(provider.getEmail()));
		}};
	}
	@Test
	public void testGetAllProviders()
	{
		String sql = "select username, firstname, lastname, email, password from provider";
		rowMapper = new ProviderRowMapper();
		List<Provider> providerList = jdbcTemplate.query(sql, rowMapper);
		new Verifications()
		{{
			assertEquals(providerList, providerDao.getAllProviders());
		}};
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
