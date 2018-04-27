package com.ssdi.campuscare.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssdi.campuscare.dao.ICategoryDao;
import com.ssdi.campuscare.model.Category;
import com.ssdi.campuscare.model.Provider;
import com.ssdi.campuscare.service.ConsumerService;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
@SpringBootTest
public class CategoryServiceTest {

	@Injectable
	private ICategoryDao categoryDao;

	@Tested
	private CategoryService categoryService;

	@Test
	public void testGetAllCategories() {

		Category category1 = new Category(1, "Sports");
		Category category2 = new Category(2, "Painting");
		List<Category> returnedCategories = new ArrayList<Category>();
		returnedCategories.add(category1);
		returnedCategories.add(category2);

		// Expectation: The getProviderCategories method of Category DAO should get
		// invoked
		new Expectations() {
			{
				categoryDao.getAllCategories();
				result = returnedCategories; 
			}
		};

		// When: When the getProviderCategories method is invoked from Service layer, it
		// should internally called getProviderCategories method of Category DAO.
		List<Category> expectedCategories = categoryService.getAllCategories();

		new Verifications() {
			{
				assertEquals(2, expectedCategories.size());
				assertEquals("Sports", expectedCategories.get(0).getCategoryName());
				assertEquals("Painting", expectedCategories.get(1).getCategoryName());
			}
		};

	}

	@Test
	public void testGetProviderCategories() {
		// New Provider with provider_id = 1 is created
		Provider provider = new Provider();
		provider.setProviderId(1);

		// Expectation: The getProviderCategories method of Category DAO should get
		// invoked
		new Expectations() {
			{
				categoryDao.getProviderCategories(provider.getProviderId());
			}
		};

		// When: When the getProviderCategories method is invoked from Service layer, it
		// should internally called getProviderCategories method of Category DAO.
		categoryService.getProviderCategories(provider.getProviderId());

	}
	
	
	@Test
	public void testAddProviderCategory() {
		
		Category category = new Category(5, "Yoga");
		Provider provider = new Provider();
		provider.setProviderId(1);

		List<Category> returnedCategories = new ArrayList<Category>();
		returnedCategories.add(category);
			

		// Expectation: The addProviderCategory method of Category DAO should get
		// invoked
		new Expectations() {
			{
				categoryDao.addProviderCategory(provider.getProviderId(), category.getCategoryId());
				result = returnedCategories;
			}
		};

		// When: When the addProviderCategory method is invoked from Service layer, it
		// should internally called addProviderCategory method of Category DAO.
		List<Category> expectedCategories = categoryService.addProviderCategory(provider.getProviderId(), category.getCategoryId());

		new Verifications() {
			{
				assertEquals(1, expectedCategories.size());
				assertEquals(5, expectedCategories.get(0).getCategoryId());
				assertEquals("Yoga", expectedCategories.get(0).getCategoryName());
				
			}
		};
	}


		@Test
		public void testRemoveProviderCategory() {
			
			Category category1 = new Category(3, "Cooking");
			Category category2 = new Category(4, "Driving");
			Category category3 = new Category(5, "Gym");
			
			Provider provider = new Provider();
			provider.setProviderId(2);

			List<Category> returnedCategories = new ArrayList<Category>();
			returnedCategories.add(category2);
			returnedCategories.add(category3);
				

			// Expectation: The addProviderCategory method of Category DAO should get
			// invoked
			new Expectations() {
				{
					categoryDao.removeProviderCategory(provider.getProviderId(), category1.getCategoryId());
					result = returnedCategories;
				}
			};

			// When: When the addProviderCategory method is invoked from Service layer, it
			// should internally called addProviderCategory method of Category DAO.
			List<Category> expectedCategories = categoryService.removeProviderCategory(provider.getProviderId(), category1.getCategoryId());

			new Verifications() {
				{
					assertEquals(2, expectedCategories.size());
					assertEquals("Driving", expectedCategories.get(0).getCategoryName());
					assertEquals("Gym", expectedCategories.get(1).getCategoryName());
					
				}
			};

		
}
	
	

}
