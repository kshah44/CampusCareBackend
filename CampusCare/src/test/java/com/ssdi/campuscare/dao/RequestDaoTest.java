package com.ssdi.campuscare.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.sql.DataSource;

import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Repository;

import com.ssdi.campuscare.model.Request;

import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

@Repository
@RunWith(JMockit.class)
@SpringBootTest
public class RequestDaoTest {
	private DataSource dataSource;
	private static final String driverClassName = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/campuscaretest?useSSL=false";
	private static final String dbUsername = "root";
	private static final String dbPassword = "1003";

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

//	@Test
//	public void testGetAllRequests() {
//		DataSource ds = getSource();
//		jt = new JdbcTemplate(ds);
//
//		// Given: There are no requests in the table
//		String sql = "delete from request where request_id <> ?";
//		jt.update(sql, 0);
//
//		// String sql2 = "insert into request values (1, 1, 1, 1, \"Pending\"), (2, 2,
//		// 2, 2, \"Pending\")";
//		String sql2 = "insert into request (request_id, consumer_id, provider_id, category_id, request_status)"
//				+ " values (1, 1, 1, 1, \"Pending\"), (2, 2, 2, 2, \"Pending\")";
//
//		jt.update(sql2);
//
//		requestDao = new RequestDao(jt);
//		List<Request> requestList = requestDao.getAllRequests();
//
//		new Verifications() {
//			{
//				assertEquals(2, requestList.size());
//
//				assertEquals(1, requestList.get(0).getRequestId());
//				assertEquals(1, requestList.get(0).getConsumerId());
//				assertEquals(1, requestList.get(0).getProviderId());
//				assertEquals(1, requestList.get(0).getCategoryId());
//				assertEquals("Pending", requestList.get(0).getStatus());
//
//				assertEquals(2, requestList.get(1).getRequestId());
//				assertEquals(2, requestList.get(1).getConsumerId());
//				assertEquals(2, requestList.get(1).getProviderId());
//				assertEquals(2, requestList.get(1).getCategoryId());
//				assertEquals("Pending", requestList.get(1).getStatus());
//			}
//		};
//	}

	@Test
	public void testGetProviderRequests() {
		DataSource ds = getSource();
		jt = new JdbcTemplate(ds);

		// Given: There are no records in the tables : consumer, provider, category and
		// request

		String sql = "delete from request where request_id <> ?"; // Deleting records from REQUEST table
		jt.update(sql, 0);

		String sql1 = "delete from consumer where consumer_id <> ?"; // Deleting records from CONSUMER table
		jt.update(sql1, 0);

		String sql2 = "delete from provider where provider_id <> ?"; // Deleting records from PROVIDER table
		jt.update(sql2, 0);

		String sql3 = "delete from category where category_id <> ?"; // Deleting records from CATEGORY table
		jt.update(sql3, 0);

		// Inserting rows into above tables
		String sql5 = "insert into consumer (consumer_id, username, firstname, lastname, email, password) "
				+ "values (1, 'shashikant','Shashikant','Jaiswal', 'shashi@gmail.com', 'Password123')";
		jt.update(sql5);

		String sql6 = "insert into provider (provider_id, username, firstname, lastname, email, password) "
				+ "values (1, 'John1111','John','Doe', 'John@gmail.com', 'Password123');";
		jt.update(sql6);

		String sql7 = "insert into category (category_id, category_name) values (1, \"Computers\"), (2, \"Academics\")";
		jt.update(sql7);

		String sql4 = "insert into request (request_id, consumer_id, provider_id, category_id, request_status)"
				+ " values (1, 1, 1, 1, \"Pending\"), (2, 1, 1, 2, \"Accepted\")";
		jt.update(sql4);

		requestDao = new RequestDao(jt);
		JSONArray resultArray = requestDao.getProviderRequest(1);

		new Verifications() {
			{
				assertEquals(2, resultArray.length());

				assertEquals(1, resultArray.getJSONObject(0).getInt("requestId"));
				assertEquals("Shashikant Jaiswal", resultArray.getJSONObject(0).getString("fullname"));
				assertEquals("Computers", resultArray.getJSONObject(0).getString("categoryName"));
				assertEquals("Pending", resultArray.getJSONObject(0).getString("status"));

				assertEquals(2, resultArray.getJSONObject(1).getInt("requestId"));
				assertEquals("Shashikant Jaiswal", resultArray.getJSONObject(1).getString("fullname"));
				assertEquals("Academics", resultArray.getJSONObject(1).getString("categoryName"));
				assertEquals("Accepted", resultArray.getJSONObject(1).getString("status"));

			}
		};
	}

	@Test
	public void testGetConsumerRequest() {
		DataSource ds = getSource();
		jt = new JdbcTemplate(ds);

		// Given: There are no records in the tables : consumer, provider, category and
		// request

		String sql = "delete from request where request_id <> ?"; // Deleting records from REQUEST table
		jt.update(sql, 0);

		String sql1 = "delete from consumer where consumer_id <> ?"; // Deleting records from CONSUMER table
		jt.update(sql1, 0);

		String sql2 = "delete from provider where provider_id <> ?"; // Deleting records from PROVIDER table
		jt.update(sql2, 0);

		String sql3 = "delete from category where category_id <> ?"; // Deleting records from CATEGORY table
		jt.update(sql3, 0);

		// Inserting rows into above tables
		String sql5 = "insert into consumer (consumer_id, username, firstname, lastname, email, password) "
				+ "values (1, 'shashikant','Shashikant','Jaiswal', 'shashi@gmail.com', 'Password123')";
		jt.update(sql5);

		String sql6 = "insert into provider (provider_id, username, firstname, lastname, email, password) "
				+ "values (1, 'John1111','John','Doe', 'John@gmail.com', 'Password123')";
		jt.update(sql6);

		String sql7 = "insert into category (category_id, category_name) values (1, \"Computers\"), (2, \"Academics\")";
		jt.update(sql7);

		String sql4 = "insert into request (request_id, consumer_id, provider_id, category_id, request_status)"
				+ " values (1, 1, 1, 1, \"Pending\"), (2, 1, 1, 2, \"Accepted\")";
		jt.update(sql4);

		requestDao = new RequestDao(jt);
		JSONArray resultArray = requestDao.getConsumerRequest(1);

		new Verifications() {
			{
				assertEquals(2, resultArray.length());

				assertEquals(2, resultArray.getJSONObject(0).getInt("requestId"));
				assertEquals("John Doe", resultArray.getJSONObject(0).getString("fullname"));
				assertEquals("Academics", resultArray.getJSONObject(0).getString("categoryName"));
				assertEquals("Accepted", resultArray.getJSONObject(0).getString("status"));
				
				assertEquals(1, resultArray.getJSONObject(1).getInt("requestId"));
				assertEquals("John Doe", resultArray.getJSONObject(1).getString("fullname"));
				assertEquals("Computers", resultArray.getJSONObject(1).getString("categoryName"));
				assertEquals("Pending", resultArray.getJSONObject(1).getString("status"));


			}
		};
	}

	
	@Test public void testCreateRequests() { 
	DataSource ds= getSource(); 
	jt = new JdbcTemplate(ds);
	  
	 // Given: There are no requests in the table 
	String sql =  "delete from request where request_id <> ?"; 
	jt.update(sql,0);
	  
	String sql4 =  "insert into request values (1, 1, 1, 1, \"Pending\"), (2, 2, 2, 2, \"Pending\")";
	jt.update(sql4);
	  
	requestDao = new RequestDao(jt); 
	Request test_request = new Request(3, 3, 3, 3, "Pending");
	Request returned_request = requestDao.createRequest(test_request);
	
	new Verifications() {{ 
	  assertEquals(3, returned_request.getRequestId());	
	  assertEquals(3, returned_request.getConsumerId());
	  assertEquals(3, returned_request.getProviderId()); 
	  assertEquals(3, returned_request.getCategoryId()); 
	  assertEquals("Pending", returned_request.getStatus()); 
	  }};
}


	public void testConsumerUpdateRequestStatus() {
		DataSource ds = getSource();
		jt = new JdbcTemplate(ds);

		// Given: There are no records in the tables : consumer, provider, category and
		// request

		String sql = "delete from request where request_id <> ?"; // Deleting records from REQUEST table
		jt.update(sql, 0);

		String sql1 = "delete from consumer where consumer_id <> ?"; // Deleting records from CONSUMER table
		jt.update(sql1, 0);

		String sql2 = "delete from provider where provider_id <> ?"; // Deleting records from PROVIDER table
		jt.update(sql2, 0);

		String sql3 = "delete from category where category_id <> ?"; // Deleting records from CATEGORY table
		jt.update(sql3, 0);

		// Inserting rows into above tables
		String sql5 = "insert into consumer (consumer_id, username, firstname, lastname, email, password) "
				+ "values (1, 'shashikant','Shashikant','Jaiswal', 'shashi@gmail.com', 'Password123')";
		jt.update(sql5);

		String sql6 = "insert into provider (provider_id, username, firstname, lastname, email, password) "
				+ "values (1, 'John1111','John','Doe', 'John@gmail.com', 'Password123');";
		jt.update(sql6);

		String sql7 = "insert into category (category_id, category_name) values (1, \"Computers\"), (2, \"Academics\")";
		jt.update(sql7);

		String sql4 = "insert into request (request_id, consumer_id, provider_id, category_id, request_status)"
				+ " values (1, 1, 1, 1, \"Pending\"), (2, 1, 1, 2, \"Pending\")";
		jt.update(sql4);

		requestDao = new RequestDao(jt);
		JSONArray resultArray = requestDao.ConsumerUpdateRequestStatus(1, 1, "Cancelled");

		new Verifications() {
			{
				assertEquals(2, resultArray.length());

				assertEquals(1, resultArray.getJSONObject(0).getInt("requestId"));
				assertEquals("John Doe", resultArray.getJSONObject(0).getString("fullname"));
				assertEquals("Computers", resultArray.getJSONObject(0).getString("categoryName"));
				assertEquals("Cancelled", resultArray.getJSONObject(0).getString("status"));

				assertEquals(2, resultArray.getJSONObject(1).getInt("requestId"));
				assertEquals("John Doe", resultArray.getJSONObject(1).getString("fullname"));
				assertEquals("Academics", resultArray.getJSONObject(1).getString("categoryName"));
				assertEquals("Pending", resultArray.getJSONObject(1).getString("status"));
			}
		};
		
	}

	
	
	public void testProviderUpdateRequestStatus() {
		DataSource ds = getSource();
		jt = new JdbcTemplate(ds);

		// Given: There are no records in the tables : consumer, provider, category and
		// request

		String sql = "delete from request where request_id <> ?"; // Deleting records from REQUEST table
		jt.update(sql, 0);

		String sql1 = "delete from consumer where consumer_id <> ?"; // Deleting records from CONSUMER table
		jt.update(sql1, 0);

		String sql2 = "delete from provider where provider_id <> ?"; // Deleting records from PROVIDER table
		jt.update(sql2, 0);

		String sql3 = "delete from category where category_id <> ?"; // Deleting records from CATEGORY table
		jt.update(sql3, 0);

		// Inserting rows into above tables
		String sql5 = "insert into consumer (consumer_id, username, firstname, lastname, email, password) "
				+ "values (1, 'shashikant','Shashikant','Jaiswal', 'shashi@gmail.com', 'Password123')";
		jt.update(sql5);

		String sql6 = "insert into provider (provider_id, username, firstname, lastname, email, password) "
				+ "values (1, 'John1111','John','Doe', 'John@gmail.com', 'Password123');";
		jt.update(sql6);

		String sql7 = "insert into category (category_id, category_name) values (1, \"Computers\"), (2, \"Academics\")";
		jt.update(sql7);

		String sql4 = "insert into request (request_id, consumer_id, provider_id, category_id, request_status)"
				+ " values (1, 1, 1, 1, \"Pending\"), (2, 1, 1, 2, \"Pending\")";
		jt.update(sql4);

		requestDao = new RequestDao(jt);
		JSONArray resultArray = requestDao.ProviderUpdateRequestStatus(1, 1, "Accepted");

		new Verifications() {
			{
				assertEquals(2, resultArray.length());

				assertEquals(1, resultArray.getJSONObject(0).getInt("requestId"));
				assertEquals("Shashikant Jaiswal", resultArray.getJSONObject(0).getString("fullname"));
				assertEquals("Computers", resultArray.getJSONObject(0).getString("categoryName"));
				assertEquals("Accepted", resultArray.getJSONObject(0).getString("status"));

				assertEquals(2, resultArray.getJSONObject(1).getInt("requestId"));
				assertEquals("Shashikant Jaiswal", resultArray.getJSONObject(1).getString("fullname"));
				assertEquals("Academics", resultArray.getJSONObject(1).getString("categoryName"));
				assertEquals("Pending", resultArray.getJSONObject(1).getString("status"));
			}
		};

		JSONArray resultArray2 = requestDao.ProviderUpdateRequestStatus(2, 1, "Rejected");

		new Verifications() {
			{
				assertEquals(2, resultArray2.length());

				assertEquals(1, resultArray2.getJSONObject(0).getInt("requestId"));
				assertEquals("Shashikant Jaiswal", resultArray2.getJSONObject(0).getString("fullname"));
				assertEquals("Computers", resultArray2.getJSONObject(0).getString("categoryName"));
				assertEquals("Accepted", resultArray2.getJSONObject(0).getString("status"));

				assertEquals(2, resultArray2.getJSONObject(1).getInt("requestId"));
				assertEquals("Shashikant Jaiswal", resultArray2.getJSONObject(1).getString("fullname"));
				assertEquals("Academics", resultArray2.getJSONObject(1).getString("categoryName"));
				assertEquals("Rejected", resultArray2.getJSONObject(1).getString("status"));
			}
		};

		JSONArray resultArray3 = requestDao.ProviderUpdateRequestStatus(2, 1, "Rejected");

		new Verifications() {
			{
				assertEquals(2, resultArray3.length());

				assertEquals(1, resultArray3.getJSONObject(0).getInt("requestId"));
				assertEquals("Shashikant Jaiswal", resultArray3.getJSONObject(0).getString("fullname"));
				assertEquals("Computers", resultArray3.getJSONObject(0).getString("categoryName"));
				assertEquals("Completed", resultArray3.getJSONObject(0).getString("status"));

				assertEquals(2, resultArray3.getJSONObject(1).getInt("requestId"));
				assertEquals("Shashikant Jaiswal", resultArray3.getJSONObject(1).getString("fullname"));
				assertEquals("Academics", resultArray3.getJSONObject(1).getString("categoryName"));
				assertEquals("Rejected", resultArray3.getJSONObject(1).getString("status"));
			}
		};
	}
}
