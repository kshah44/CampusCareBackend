package com.ssdi.campuscare.model.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.ssdi.campuscare.model.Category;

import mockit.integration.junit4.JMockit;
import mockit.Tested;
import mockit.Verifications;

@RunWith(JMockit.class)
@SpringBootTest
public class CategoryTest {

	@Tested
	private Category testCategory;
	
	@Test
	public void testCategoryConstructorWithNoParams() {
		testCategory = new Category();
		new Verifications() {
			{
				assertEquals(-1, testCategory.getCategoryId());
				assertEquals(null, testCategory.getCategoryName());
			}
		};
	}
	
	@Test
	public void testCategoryConstructorWithCategoryNameOnly() {
		testCategory = new Category("Academics");
		new Verifications() {
			{
				assertEquals(-1, testCategory.getCategoryId());
				assertEquals("Academics", testCategory.getCategoryName());
			}
		};
	}

	@Test
	public void testCategoryConstructorWithCategoryIdAndName() {
		testCategory = new Category(5, "Swimming");
		new Verifications() {
			{
				assertEquals(5, testCategory.getCategoryId());
				assertEquals("Swimming", testCategory.getCategoryName());
			}
		};
	}
	
	@Test
	public void testSetCategoryIdAndSetCategoryName() {
		
		//Given: A blank category with no ID and no Name is created
		testCategory = new Category();
		new Verifications() {
			{
				assertEquals(-1, testCategory.getCategoryId());
				assertEquals(null,testCategory.getCategoryName());
			}
		};
		
		//When: Set categoryID and Set CategoryName functions are invoked and setting categoryId = 10 and CategoryName = 'Computers"
		testCategory.setCategoryId(10);
		testCategory.setCategoryName("Computers");
		
		
		//Then: CategoryID and CategoryName are set as expected.
		new Verifications() {
			{
				assertEquals(10, testCategory.getCategoryId());
				assertEquals("Computers",testCategory.getCategoryName());
			}
		};

	}

}
