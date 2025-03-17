package edu.ycp.cs320.lab02.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.lab02.controller.NumbersController;
import edu.ycp.cs320.lab02.model.Numbers;

public class NumbersControllerTest {
	private Numbers model;
	private NumbersController controller;
	
	@Before
	public void setUp() {
		model = new Numbers();
		controller = new NumbersController();
	}
	
	@Test
	public void testadd() {
		
		model.setFirst(110.0);
		model.setSecond(200.0);
		model.setThird(300.0);
		
		controller.setModel(model);
		model.setResult(controller.add());
		
		assertEquals(610, model.getResult(), 0.01);
		
	}
	
	@Test
	public void testadd1() {
		
		model.setFirst(110.0);
		model.setSecond(200.0);
		model.setThird(-300.0);
		
		controller.setModel(model);
		model.setResult(controller.add());
		assertEquals(10, model.getResult(), 0.01);
		
	}

	
	@Test
	public void testadd2() {

		model.setFirst(1.25);
		model.setSecond(1.25);
		model.setThird(1.25);
		
		controller.setModel(model);
		model.setResult(controller.add());
		
		assertEquals(3.75, model.getResult(), 0.01);
		
	}
	
	
	@Test
	public void testMultiply() {

		model.setFirst(1.25);
		model.setSecond(1.25);
		
		controller.setModel(model);
		model.setResult(controller.multiply());
		
		assertEquals(1.56, model.getResult(), 0.01);
		
	}
	
	
	@Test
	public void testMultiply1() {

		model.setFirst(-200.0);
		model.setSecond(2.0);
		
		controller.setModel(model);
		model.setResult(controller.multiply());
		
		assertEquals(-400, model.getResult(), 0.01);
		
	}
	
	
	
}
