package edu.ycp.cs320.lab02.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.lab02.model.Numbers;

public class NumbersTest {
	private Numbers model;
	
	@Before
	public void setUp() {
		model = new Numbers();
	}
	
	@Test
	public void testSetFirst() {
		model.setFirst(1.0);
		assertEquals(1.0, model.getFirst(), 0.01);
	}
	
	@Test
	public void testSetSecond() {
		model.setSecond(100.0);
		assertEquals(100.0, model.getSecond(), 0.01);
	}
	
	@Test
	public void testSetThird() {
		model.setThird(250.0);
		assertEquals(250.0, model.getThird(), 0.01);
	}
	
	@Test
	public void testGetResult() {
		model.setResult(2.0);
		assertEquals(2.0, model.getResult(), 0.01);
	}
	
	@Test
	public void testgetfirst() {

		model.setFirst(250.0);
		
		
		assertEquals(250.0, model.getFirst(), 0.01);
		
	}
	
	@Test
	public void testgetSecond() {

		model.setSecond(-2.0);
		
		assertEquals(-2.0, model.getSecond(), 0.01);
		
	}
	
	@Test
	public void testgetThird() {

		model.setThird(300000.0);

		assertEquals(300000, model.getThird(), 0.01);
		
	}
	
	@Test
	public void testgetfirstStr() {

		model.setFirstStr("250.0");
		
		
		assertEquals("250.0", model.getFirstStr());
		
	}
	
	@Test
	public void testgetSecondStr() {

		model.setSecondStr("-2.0");
		
		assertEquals("-2.0", model.getSecondStr());
		
	}
	
	@Test
	public void testgetThirdStr() {

		model.setThirdStr("300000.0");

		assertEquals("300000.0", model.getThirdStr());
		
	}
	
	
}
