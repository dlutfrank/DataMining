package com.swx;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.AfterClass;

public class UnitTestExample {
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("before class");
	}
	
	@Before
    public void setUp() throws Exception {
		System.out.println("before");		
	}
	
	@Test
	public void testAddExample() {		
		assertEquals(4,3+1);		
	}
	
	@Test
	public void testSubExample() {
		assertEquals(3,5-3);
	}
	
	@After
	public void after() {
		System.out.println("after");		
	}
	
	@AfterClass
	public static void afterClass() {
		System.out.println("after class");
	}
}