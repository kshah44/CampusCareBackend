package com.ssdi.campuscare.controller;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ssdi.campuscare.model.Consumer;

public class ConsumerControllerTest {

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
		ConsumerController cc = new ConsumerController();
		Consumer c = new Consumer("anusha","Anusha","Balaji","Anusha@gmail.com","BestPwd");

		Consumer added = cc.createConsumer(c);
		assertEquals("Anusha", added.getFirstName());
	
	}
	

	public void testverifyLogin() {
		ConsumerController cc = new ConsumerController();
		Consumer c = new Consumer("anusha","Anusha","Balaji","Anusha@gmail.com","BestPwd");
		System.out.println("Hello");
		System.out.println(cc.getAllConsumers());
		Consumer added = cc.verifyLogin(c);
		assertEquals("Anusha@gmail.com", added.getEmail());
		//assertEquals()
	
	}

}
