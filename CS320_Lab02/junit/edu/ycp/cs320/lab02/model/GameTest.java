package edu.ycp.cs320.lab02.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.lab02.model.Numbers;

public class GameTest {
	private Establishment model;
	
	@Before
	public void setUp() {
		model = new Establishment();
	}
	
	//Getters and Setters Test
	
	@Test
	public void testSetName() {
		model.setName("Bowlerama");
		assertEquals("Bowlerama", model.getName());
	}
	
	
	@Test
	public void testSetLocation() {
		model.setLocation("York,PA");
		assertEquals("York,PA", model.getLocation());
	}
	
	
	@Test
	public void testSetPhoneNumber() {
		model.setphoneNumber("777-777-7777");
		assertEquals("777-777-7777", model.getphoneNumber());
	}
	
	
	@Test
	public void testSetHours() {
		model.setHours("9AM - 9PM");
		assertEquals("9AM - 9PM", model.getHours());
	}
	
	
}
