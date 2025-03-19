package edu.ycp.cs320.lab02.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.lab02.model.Numbers;

public class BallTest {
	private Ball model;
	
	@Before
	public void setUp() {
		model = new Ball();
	}
	
	@Test
	public void testSetDiameter() {
		model.setDiameter(1.3);
		assertEquals(1.3, model.getDiameter(), 0.01);
	}
	
	
	@Test
	public void testSetWeight() {
		model.setWeight(10.1);
		assertEquals(10.1, model.getWeight(), 0.01);
	}
	
	
	@Test
	public void testSetColor() {
		model.setColor("Red");
		assertEquals("Red", model.getColor());
	}
	
	
	@Test
	public void testSetName() {
		model.setName("Big Ball");
		assertEquals("Big Ball", model.getName());
	}
	
	@Test
	public void testSetBrand() {
		model.setBrand("BestBowlingBalls");
		assertEquals("BestBowlingBalls", model.getBrand());
	}
	
	
	@Test
	public void testSetCore() {
		model.setCore("1.3 Symmetric");
		assertEquals("1.3 Symmetric", model.getCore());
	}
	
	
}
