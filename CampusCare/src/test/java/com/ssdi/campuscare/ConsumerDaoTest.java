package com.ssdi.campuscare;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ssdi.campuscare.dao.ConsumerDao;
import com.ssdi.campuscare.model.Consumer;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
@RunWith(JMockit.class)
@SpringBootTest
public class ConsumerDaoTest 
{
	@Injectable
	private JdbcTemplate jdc;
	
	@Tested
	private ConsumerDao consumerDao;
	
	private Consumer consumer;
	
	@Test
	public void testfindConsumerByUsername()
	{
		Consumer consumer = new Consumer();
		consumer.setUserName("username");
		new Verifications()
		{{
			assertEquals(false, consumerDao.findConsumerByUsername(consumer.getUserName()));
		}};
	}
}
