package com.ssdi.campuscare.service;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ssdi.campuscare.model.*;

public class ConsumerServiceTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		//fail("Not yet implemented");
	}
	
	@Test
	public void testCreateConsumer() {
	
		ConsumerService cs = new ConsumerService();
		Consumer c = new Consumer("An","An", "B", "an@gmail.com", "pwd");
		Consumer added = cs.createConsumer(c);
		assertEquals("An", added.getFirstName());
	}

}
