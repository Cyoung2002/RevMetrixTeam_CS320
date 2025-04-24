package edu.ycp.cs320.lab02.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.lab02.model.Numbers;

public class ShotTest {
	private ShotObject model;
	
	@Before
	public void setUp() {
		model = new ShotObject(3);
	}
	
	@Test
	public void testGetPinsKnockedDown() {
		int pinsKnockedDown = 5;
		assertEquals(5, model.getPinsKnockedDown());
	}
	
	
}
