package com.ssdi.campuscare.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssdi.campuscare.dao.IRequestDao;
import com.ssdi.campuscare.model.Category;
import com.ssdi.campuscare.model.Consumer;
import com.ssdi.campuscare.model.Provider;
import com.ssdi.campuscare.model.Request;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
@SpringBootTest
public class RequestServiceTest 
{
	@Injectable
	private IRequestDao requestDao;

	@Injectable
	private Provider test_provider;

	@Injectable
	private Consumer test_consumer;

	@Injectable
	private Category test_category1, test_category2;

	@Injectable
	private Request test_request;
	
	@Tested
	RequestService requestService;
	
//	@Test
//	public void testGetAllRequests() 
//	{
//		Request request = new Request(1, 1, 1, "Pending");
//		Request request1 = new Request(2, 2, 2, "Pending");
//		List<Request> requestList = new ArrayList<Request>();
//		requestList.add(request);
//		requestList.add(request1);
//		
//		new Expectations()
//		{{
//			requestDao.getAllRequests();
//			result = requestList;
//		}};
//		
//		List<Request> expected_requestList = requestService.getAllRequests();
//		
//		new Verifications()
//		{{
//			assertEquals(2, expected_requestList.size());
//			assertEquals(1, expected_requestList.get(0).getConsumerId());
//			assertEquals(2, expected_requestList.get(1).getConsumerId());
//		}};
//	}
	
	@Test
	public void testGetProviderRequest()
	{
	
		test_provider = new Provider(1, "John1111","John","Doe", "John@gmail.com", "Password123");
		test_consumer = new Consumer(1, "shashikant","Shashikant","Jaiswal", "shashi@gmail.com", "Password123");
		test_category1 = new Category(1,"Computers");
		test_category2 = new Category(2,"Academics");
		
		JSONArray provider_requestArray = new JSONArray();
		JSONObject obj1 = new JSONObject();
			obj1.put("requestId", 1);
			obj1.put("fullname",test_consumer.getFirstName()+" "+test_consumer.getLastName());
			obj1.put("categoryName", test_category1.getCategoryName());
			obj1.put("status", "Pending");
			provider_requestArray.put(obj1);
		JSONObject obj2 = new JSONObject();
			obj2.put("requestId", 2);
			obj2.put("fullname",test_consumer.getFirstName()+" "+test_consumer.getLastName());
			obj2.put("categoryName", test_category2.getCategoryName());
			obj2.put("status", "Accepted");
			provider_requestArray.put(obj2);	
		
		new Expectations()
		{{
			requestDao.getProviderRequest(test_provider.getProviderId());  //provider_id = 1
			result = provider_requestArray;
		}};
		
		JSONArray Expected_resultArray = requestService.getProviderRequest(test_provider.getProviderId());  //providerId =1
		
		new Verifications()
		{{
			assertEquals(2, Expected_resultArray.length());

			assertEquals(1, Expected_resultArray.getJSONObject(0).getInt("requestId"));
			assertEquals("Shashikant Jaiswal", Expected_resultArray.getJSONObject(0).getString("fullname"));
			assertEquals("Computers", Expected_resultArray.getJSONObject(0).getString("categoryName"));
			assertEquals("Pending", Expected_resultArray.getJSONObject(0).getString("status"));

			assertEquals(2, Expected_resultArray.getJSONObject(1).getInt("requestId"));
			assertEquals("Shashikant Jaiswal", Expected_resultArray.getJSONObject(1).getString("fullname"));
			assertEquals("Academics", Expected_resultArray.getJSONObject(1).getString("categoryName"));
			assertEquals("Accepted", Expected_resultArray.getJSONObject(1).getString("status"));

		}};
	}
	
	@Test
	public void testGetConsumerRequest()
	{
	
		test_provider = new Provider(1, "John1111","John","Doe", "John@gmail.com", "Password123");
		test_consumer = new Consumer(1, "shashikant","Shashikant","Jaiswal", "shashi@gmail.com", "Password123");
		test_category1 = new Category(1,"Computers");
		test_category2 = new Category(2,"Academics");
		
		JSONArray consumer_requestArray = new JSONArray();
		JSONObject obj1 = new JSONObject();
			obj1.put("requestId", 1);
			obj1.put("fullname",test_provider.getFirstName()+" "+test_provider.getLastName());
			obj1.put("categoryName", test_category1.getCategoryName());
			obj1.put("status", "Pending");
			consumer_requestArray.put(obj1);
		JSONObject obj2 = new JSONObject();
			obj2.put("requestId", 2);
			obj2.put("fullname",test_provider.getFirstName()+" "+test_provider.getLastName());
			obj2.put("categoryName", test_category2.getCategoryName());
			obj2.put("status", "Accepted");
			consumer_requestArray.put(obj2);	
		
		new Expectations()
		{{
			requestDao.getConsumerRequest(test_consumer.getConsumerId());  //consumer_id = 1
			result = consumer_requestArray;
		}};
		
		JSONArray Expected_resultArray = requestService.getConsumerRequest(test_consumer.getConsumerId());  //ConsumerId =1
		
		new Verifications()
		{{
			assertEquals(2, Expected_resultArray.length());

			assertEquals(1, Expected_resultArray.getJSONObject(0).getInt("requestId"));
			assertEquals("John Doe", Expected_resultArray.getJSONObject(0).getString("fullname"));
			assertEquals("Computers", Expected_resultArray.getJSONObject(0).getString("categoryName"));
			assertEquals("Pending", Expected_resultArray.getJSONObject(0).getString("status"));

			assertEquals(2, Expected_resultArray.getJSONObject(1).getInt("requestId"));
			assertEquals("John Doe", Expected_resultArray.getJSONObject(1).getString("fullname"));
			assertEquals("Academics", Expected_resultArray.getJSONObject(1).getString("categoryName"));
			assertEquals("Accepted", Expected_resultArray.getJSONObject(1).getString("status"));

		}};
	}

	@Test
	public void testConsumerUpdateRequestStatus()
	{

		test_provider = new Provider(1, "John1111","John","Doe", "John@gmail.com", "Password123");
		test_consumer = new Consumer(1, "shashikant","Shashikant","Jaiswal", "shashi@gmail.com", "Password123");
		test_category1 = new Category(1,"Computers");
		//test_category2 = new Category(2,"Academics");
		test_request = new Request(1, test_consumer.getConsumerId(), test_provider.getProviderId(), test_category1.getCategoryId(), "Pending");
		 
		//Scenario 1 : changing status from "Pending" to "Cancelled"
		JSONArray provider_requestArray = new JSONArray();
		JSONObject obj1 = new JSONObject();
			obj1.put("requestId", test_request.getRequestId());
			obj1.put("fullname",test_provider.getFirstName()+" "+test_provider.getLastName());
			obj1.put("categoryName", test_category1.getCategoryName());
			obj1.put("status", "Cancelled"); 
			provider_requestArray.put(obj1);
			
			new Expectations()
			{{
				requestDao.ConsumerUpdateRequestStatus(test_request.getRequestId(), test_consumer.getConsumerId(), "Cancelled");  //request_id =1 , provider_id = 1 and status = "Pending"
				result = provider_requestArray;
			}};
			
			JSONArray Expected_resultArray = requestService.ConsumerUpdateRequestStatus(test_request.getRequestId(), test_consumer.getConsumerId(), "Cancelled");  //request_id =1 , provider_id = 1 and new_status = "Accepted"
			
			new Verifications()
			{{
				assertEquals(1, Expected_resultArray.length());

				assertEquals(1, Expected_resultArray.getJSONObject(0).getInt("requestId"));
				assertEquals("John Doe", Expected_resultArray.getJSONObject(0).getString("fullname"));
				assertEquals("Computers", Expected_resultArray.getJSONObject(0).getString("categoryName"));
				assertEquals("Cancelled", Expected_resultArray.getJSONObject(0).getString("status"));
			}};
			
	}

	@Test
	public void testProviderUpdateRequestStatus()
	{

		test_provider = new Provider(1, "John1111","John","Doe", "John@gmail.com", "Password123");
		test_consumer = new Consumer(1, "shashikant","Shashikant","Jaiswal", "shashi@gmail.com", "Password123");
		test_category1 = new Category(1,"Computers");
		//test_category2 = new Category(2,"Academics");
		test_request = new Request(1, test_consumer.getConsumerId(), test_provider.getProviderId(), test_category1.getCategoryId(), "Pending");
		 
		//Scenario 1 : changing status from "Pending" to "Accepted"
		JSONArray provider_requestArray = new JSONArray();
		JSONObject obj1 = new JSONObject();
			obj1.put("requestId", test_request.getRequestId());
			obj1.put("fullname",test_consumer.getFirstName()+" "+test_consumer.getLastName());
			obj1.put("categoryName", test_category1.getCategoryName());
			obj1.put("status", "Accepted"); 
			provider_requestArray.put(obj1);
			
			new Expectations()
			{{
				requestDao.ProviderUpdateRequestStatus(test_request.getRequestId(), test_provider.getProviderId(), "Accepted");  //request_id =1 , provider_id = 1 and status = "Pending"
				result = provider_requestArray;
			}};
			
			JSONArray Expected_resultArray = requestService.ProviderUpdateRequestStatus(test_request.getRequestId(), test_provider.getProviderId(), "Accepted");  //request_id =1 , provider_id = 1 and new_status = "Accepted"
			
			new Verifications()
			{{
				assertEquals(1, Expected_resultArray.length());

				assertEquals(1, Expected_resultArray.getJSONObject(0).getInt("requestId"));
				assertEquals("Shashikant Jaiswal", Expected_resultArray.getJSONObject(0).getString("fullname"));
				assertEquals("Computers", Expected_resultArray.getJSONObject(0).getString("categoryName"));
				assertEquals("Accepted", Expected_resultArray.getJSONObject(0).getString("status"));
			}};
			
			
			//Scenario 2 : changing status from "Pending" to "Rejected"
			JSONArray provider_requestArray1 = new JSONArray();
			JSONObject obj2 = new JSONObject();
				obj2.put("requestId", test_request.getRequestId());
				obj2.put("fullname",test_consumer.getFirstName()+" "+test_consumer.getLastName());
				obj2.put("categoryName", test_category1.getCategoryName());
				obj2.put("status", "Rejected"); 
				provider_requestArray1.put(obj2);
				
				new Expectations()
				{{
					requestDao.ProviderUpdateRequestStatus(test_request.getRequestId(), test_provider.getProviderId(), "Rejected");  //request_id =1 , provider_id = 1 and status = "Pending"
					result = provider_requestArray1;
				}};
				
				JSONArray Expected_resultArray1 = requestService.ProviderUpdateRequestStatus(test_request.getRequestId(), test_provider.getProviderId(), "Rejected");  //request_id =1 , provider_id = 1 and new_status = "Accepted"
				
				new Verifications()
				{{
					assertEquals(1, Expected_resultArray1.length());

					assertEquals(1, Expected_resultArray1.getJSONObject(0).getInt("requestId"));
					assertEquals("Shashikant Jaiswal", Expected_resultArray1.getJSONObject(0).getString("fullname"));
					assertEquals("Computers", Expected_resultArray1.getJSONObject(0).getString("categoryName"));
					assertEquals("Rejected", Expected_resultArray1.getJSONObject(0).getString("status"));
				}};
				

				//Scenario 3 : changing status from "Accepted" to "Completed"
				test_request = new Request(1, test_consumer.getConsumerId(), test_provider.getProviderId(), test_category1.getCategoryId(), "Accepted");
				
				JSONArray provider_requestArray2 = new JSONArray();
				JSONObject obj3 = new JSONObject();
					obj3.put("requestId", test_request.getRequestId());
					obj3.put("fullname",test_consumer.getFirstName()+" "+test_consumer.getLastName());
					obj3.put("categoryName", test_category1.getCategoryName());
					obj3.put("status", "Completed"); 
					provider_requestArray2.put(obj3);
					
					new Expectations()
					{{
						requestDao.ProviderUpdateRequestStatus(test_request.getRequestId(), test_provider.getProviderId(), "Completed");  //request_id =1 , provider_id = 1 and status = "Pending"
						result = provider_requestArray2;
					}};
					
					JSONArray Expected_resultArray2 = requestService.ProviderUpdateRequestStatus(test_request.getRequestId(), test_provider.getProviderId(), "Completed");  //request_id =1 , provider_id = 1 and new_status = "Accepted"
					
					new Verifications()
					{{
						assertEquals(1, Expected_resultArray2.length());

						assertEquals(1, Expected_resultArray2.getJSONObject(0).getInt("requestId"));
						assertEquals("Shashikant Jaiswal", Expected_resultArray2.getJSONObject(0).getString("fullname"));
						assertEquals("Computers", Expected_resultArray2.getJSONObject(0).getString("categoryName"));
						assertEquals("Completed", Expected_resultArray2.getJSONObject(0).getString("status"));
					}};

	}		


	@Test
	public void testCreateRequest()
	{
		Request request = new Request(1, 2, 3, "Pending");
		new Expectations()
		{{
			requestDao.createRequest(request);
			result = request;
		}};
		
		
		Request expected_request = requestService.createRequest(request);
		new Verifications()
		{{
			assertEquals(1, expected_request.getConsumerId());
			assertEquals(2, expected_request.getProviderId());
			assertEquals(3, expected_request.getCategoryId());
			
		}};	
	}
}
