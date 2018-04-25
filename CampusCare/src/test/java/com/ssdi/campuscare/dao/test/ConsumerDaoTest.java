package com.ssdi.campuscare.dao.test;

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

import com.ssdi.campuscare.dao.ConsumerDao;
import com.ssdi.campuscare.dao.ConsumerRowMapper;
import com.ssdi.campuscare.model.Consumer;

import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
@SpringBootTest
public class ConsumerDaoTest {
	private DataSource dataSource;
	private static final String driverClassName = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/campuscaretest?useSSL=false";
	private static final String dbUsername = "root";
	private static final String dbPassword = "1003";

	
	@Injectable
	private JdbcTemplate jdbcTemplate;

	@Injectable
	private RowMapper<Consumer> rowMapper;

	@Tested
	private ConsumerDao consumerDao;

	private Consumer consumer;

	public DriverManagerDataSource getSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(dbPassword);
		return dataSource;
	}
	
	
	@Test
	public void testFindConsumerByUsername() {
		DataSource ds= getSource();
		JdbcTemplate jt = new JdbcTemplate(ds);

		String sql1 = "delete from consumer where consumer_id <> ?";
		jdbcTemplate.update(sql1,0);
		
		String sql2 = "insert into consumer (username, firstname, lastname, email, password) values ('shashi','Shashikant','Jaiswal', 'shashi@gmail.com', 'Password123')";
		jdbcTemplate.update(sql2);
		
		consumerDao = new ConsumerDao(jt);
		
		new Verifications() {
			{
				assertEquals(true, consumerDao.findConsumerByUsername("shashi"));
			}
		};
	}

	@Test
	public void testFindConsumerByEmail() {
		DataSource ds= getSource();
		JdbcTemplate jt = new JdbcTemplate(ds);

		String sql1 = "delete from consumer where consumer_id <> ?";
		jdbcTemplate.update(sql1,0);
		
		String sql2 = "insert into consumer (username, firstname, lastname, email, password) values ('shashi','Shashikant','Jaiswal', 'shashi@gmail.com', 'Password123')";
		jdbcTemplate.update(sql2);
		
		consumerDao = new ConsumerDao(jt);
		
		new Verifications() {
			{
				assertEquals(true, consumerDao.findConsumerByEmail("shashi@gmail.com"));
			}
		};
}

	@Test
	public void testGetAllConsumers() {
		
		DataSource ds= getSource();
		JdbcTemplate jt = new JdbcTemplate(ds);
		
		String sql1 = "delete from consumer where consumer_id <> ?";
		jdbcTemplate.update(sql1,0);
		
		String sql2 = "insert into consumer (username, firstname, lastname, email, password) values ('shashi','Shashikant','Jaiswal', 'shashi@gmail.com', 'Password123')";
		jdbcTemplate.update(sql2);
		
		String sql3 = "insert into consumer (username, firstname, lastname, email, password) values ('kush','Kush','Shah', 'kush@gmail.com', 'Password222')";
		jdbcTemplate.update(sql3);
		
		consumerDao = new ConsumerDao(jt);
		List<Consumer> consumerList = new ArrayList<Consumer>();
		consumerList = consumerDao.getAllConsumers();
		//consumerList.add(new Consumer("shashi","Shashikant","Jaiswal", "shashi@gmail.com", "Password123"));
		//consumerList.add(new Consumer("kush","Kush","Shah", "kush@gmail.com", "Password222"));
		
	
		String a = consumerList.get(0).getFirstName();
		String b = consumerList.get(1).getFirstName();
		
		new Verifications() {
			{
				
				assertEquals("Shashikant", a);
				assertEquals("Kush", b);
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
