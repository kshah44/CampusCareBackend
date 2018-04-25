package com.ssdi.campuscare.dao.test;

import static org.junit.Assert.*;
import java.util.List;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;
import com.ssdi.campuscare.dao.RequestDao;
import com.ssdi.campuscare.model.Request;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;
@Repository
@RunWith(JMockit.class)
@SpringBootTest
public class RequestDaoTest 
{
	private DataSource dataSource;
	private static final String driverClassName = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/campuscaretest?useSSL=false";
	private static final String dbUsername = "root";
	private static final String dbPassword = "root321";
	
	@Tested
	RequestDao requestDao;
	
	@Injectable
	JdbcTemplate jt;

	public DriverManagerDataSource getSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(dbUsername);
		dataSource.setPassword(dbPassword);
		return dataSource;
	}
	
	@Test
	public void testGetAllRequests() {
		DataSource ds= getSource();
		jt = new JdbcTemplate(ds);

		// Given: There are no requests in the table
		String sql = "delete from request where provider_id <> ?";
		jt.update(sql,0);
				
		String sql2 = "delete from request where category_id <> ?";
		jt.update(sql2,0);
		
		String sql3 = "delete from request where consumer_id <> ?";
		jt.update(sql3, 0);
		
		String sql4 = "insert into request values (1, 1, 1, \"Pending\"), (2, 2, 2, \"Pending\")"; 
		jt.update(sql4);
		
		requestDao = new RequestDao(jt);
		List<Request> requestList = requestDao.getAllRequests();					  
		
		new Verifications() {{
			 assertEquals(2, requestList.size());
			 assertEquals(1, requestList.get(0).getConsumerId());
			 assertEquals(1, requestList.get(0).getProviderId());
			 assertEquals(1, requestList.get(0).getCategoryId());
			 assertEquals("Pending", requestList.get(0).getStatus());
			 assertEquals(2, requestList.get(1).getConsumerId());
			 assertEquals(2, requestList.get(1).getProviderId());
			 assertEquals(2, requestList.get(1).getCategoryId());
			 assertEquals("Pending", requestList.get(1).getStatus());
			}};
	    } 
	
	@Test
	public void testGetProviderRequests() {
		DataSource ds= getSource();
		jt = new JdbcTemplate(ds);

		// Given: There are no requests in the table
		String sql = "delete from request where provider_id <> ?";
		jt.update(sql,0);
				
		String sql2 = "delete from request where category_id <> ?";
		jt.update(sql2,0);
		
		String sql3 = "delete from request where consumer_id <> ?";
		jt.update(sql3, 0);
		
		String sql4 = "insert into request values (1, 1, 1, \"Pending\"), (2, 2, 2, \"Pending\")"; 
		jt.update(sql4);
		
		requestDao = new RequestDao(jt);
		List<Request> requestList = requestDao.getProviderRequest(1);					  
		
		new Verifications() {{
			 assertEquals(1, requestList.size());
			 assertEquals(1, requestList.get(0).getConsumerId());
			 assertEquals(1, requestList.get(0).getProviderId());
			 assertEquals(1, requestList.get(0).getCategoryId());
			 assertEquals("Pending", requestList.get(0).getStatus());
			}};
	    }
	
	@Test
	public void testGetConsumerRequests() {
		DataSource ds= getSource();
		jt = new JdbcTemplate(ds);

		// Given: There are no requests in the table
		String sql = "delete from request where provider_id <> ?";
		jt.update(sql,0);
				
		String sql2 = "delete from request where category_id <> ?";
		jt.update(sql2,0);
		
		String sql3 = "delete from request where consumer_id <> ?";
		jt.update(sql3, 0);
		
		String sql4 = "insert into request values (1, 1, 1, \"Pending\"), (2, 2, 2, \"Pending\")"; 
		jt.update(sql4);
		
		requestDao = new RequestDao(jt);
		List<Request> requestList = requestDao.getConsumerRequest(2);				  
		
		new Verifications() {{
			 assertEquals(1, requestList.size());
			 assertEquals(2, requestList.get(0).getConsumerId());
			 assertEquals(2, requestList.get(0).getProviderId());
			 assertEquals(2, requestList.get(0).getCategoryId());
			 assertEquals("Pending", requestList.get(0).getStatus());
			}};
	    }
	
	@Test
	public void testCreateRequests() {
		DataSource ds= getSource();
		jt = new JdbcTemplate(ds);

		// Given: There are no requests in the table
		String sql = "delete from request where provider_id <> ?";
		jt.update(sql,0);
				
		String sql2 = "delete from request where category_id <> ?";
		jt.update(sql2,0);
		
		String sql3 = "delete from request where consumer_id <> ?";
		jt.update(sql3, 0);
		
		String sql4 = "insert into request values (1, 1, 1, \"Pending\"), (2, 2, 2, \"Pending\")"; 
		jt.update(sql4);
		
		System.out.println("Inside RequestDaoTest : Before method call");
		requestDao = new RequestDao(jt);
		Request service = new Request(4, 4, 4, "Pending");
		Request service1 = requestDao.createRequest(service);
		System.out.println("Inside RequestDaoTest : After method call");					  
		
		new Verifications() {{
			 assertEquals(4, service1.getConsumerId());
			 assertEquals(4, service1.getProviderId());
			 assertEquals(4, service1.getCategoryId());
			 assertEquals("Pending", service1.getStatus());
			}};
	    }
	
	@Test
	public void testCancelRequests() {
		DataSource ds= getSource();
		jt = new JdbcTemplate(ds);

		// Given: There are no requests in the table
		String sql = "delete from request where provider_id <> ?";
		jt.update(sql,0);
				
		String sql2 = "delete from request where category_id <> ?";
		jt.update(sql2,0);
		
		String sql3 = "delete from request where consumer_id <> ?";
		jt.update(sql3, 0);
		
		String sql4 = "insert into request values (1, 1, 1, \"Pending\"), (1, 2, 2, \"Pending\")"; 
		jt.update(sql4);
		
		requestDao = new RequestDao(jt);
		List<Request> requestList = requestDao.cancelRequest(1, 1, 1);			  
		
		new Verifications() {{
			 assertEquals(1, requestList.size());
			 assertEquals(1, requestList.get(0).getConsumerId());
			 assertEquals(2, requestList.get(0).getProviderId());
			 assertEquals(2, requestList.get(0).getCategoryId());
			 assertEquals("Pending", requestList.get(0).getStatus());
			}};
	    }
}
