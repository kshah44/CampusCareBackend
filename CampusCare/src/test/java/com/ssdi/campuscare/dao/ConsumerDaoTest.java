package com.ssdi.campuscare.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.ssdi.campuscare.dao.ConsumerDao;
import com.ssdi.campuscare.dao.ConsumerRowMapper;
import com.ssdi.campuscare.model.Consumer;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
@SpringBootTest
public class ConsumerDaoTest {
	@Injectable
	private JdbcTemplate jdbcTemplate;

	@Injectable
	private RowMapper<Consumer> rowMapper;

	@Tested
	private ConsumerDao consumerDao;

	private Consumer consumer;

	@Test
	public void testfindConsumerByUsername() {
		
		String sql1 = "delete from provider_category where provider_id <> ?";
		int i = jdbcTemplate.update(sql1,0);
		
		Consumer consumer = new Consumer();
		consumer.setUserName("shashi");
		String sql = "SELECT count(1) FROM consumer where username = ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, consumer.getUserName());
		
		System.out.println("the number of rows" +count);
		new Verifications() {
			{
				assertEquals(true, consumerDao.findConsumerByUsername(consumer.getUserName()));
			}
		};
	}

	@Test
	public void testFindConsumerByEmail() {
		Consumer consumer = new Consumer();
		consumer.setEmail("hot@hotmail.com");
		String sql = "SELECT count(1) FROM consumer where email = ?";
		int count = jdbcTemplate.queryForObject(sql, Integer.class, consumer.getEmail());
		new Verifications() {
			{
				assertEquals(false, consumerDao.findConsumerByEmail(consumer.getEmail()));
			}
		};
	}

	@Test
	public void testGetAllConsumers() {
		String sql = "select username, firstname, lastname, email, password from consumer";
		rowMapper = new ConsumerRowMapper();
		
		List<Consumer> consumerList = jdbcTemplate.query(sql, rowMapper);
		new Verifications() {
			{
				assertEquals(consumerList, consumerDao.getAllConsumers());
			}
		};
	}

	@Test
	public void testVerifyLogin() {
		Consumer consumer = new Consumer();
		consumer.setUserName("username");
		consumer.setPassword("password");
		String sql1 = "SELECT count(1) FROM consumer where username = ? and password = ?";
		int count = jdbcTemplate.queryForObject(sql1, Integer.class, consumer.getUserName(), consumer.getPassword());
		if (count == 1) {
			String sql2 = "select username, firstname, lastname, email, password from consumer where username = ? and password = ?";
			rowMapper = new ConsumerRowMapper();
			Consumer consumer1 = jdbcTemplate.queryForObject(sql2, rowMapper, consumer.getUserName(),
					consumer.getPassword());
			new Verifications() {
				{
					assertEquals(consumer1, consumerDao.verifyLogin(consumer.getUserName(), consumer.getPassword()));
				}
			};
		}
	}

	@Test
	public void testCreateConsumer() {
		Consumer consumer = new Consumer("username", "Margeret", "Spyro", "hot@hotmail.com", "password");
		String sql = "INSERT INTO consumer (username, firstname, lastname, email, password) values (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, consumer.getUserName(), consumer.getFirstName(), consumer.getLastName(),
				consumer.getEmail(), consumer.getPassword());

		new Verifications() {
			{
				assertEquals(consumer, consumerDao.createConsumer(consumer));
			}
		};
}
}
