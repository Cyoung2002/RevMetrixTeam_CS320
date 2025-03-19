package edu.ycp.cs320.lab02.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.lab02.model.Frame;

public class FrameTest {
	private Frame model;
	
	@Before
	public void setUp() {
		model = new Frame();
	}
	
	@Test
	public void testSetFrameNum() {
		model.setFrameNum(3);
		assertEquals(3, model.getFrameNum(), 0.01);
	}
	
	@Test
	public void testGetFrameNum() {
		model.setFrameNum(7);
		assertEquals(7, model.getFrameNum(), 0.01);
		
	}
	
	@Test
	public void testSetLaneNum() {
		model.setLaneNum(6);
		assertEquals(6, model.getLaneNum(), 0.01);
	}
	
	@Test
	public void testGetLaneNum() {
		model.setLaneNum(3);
		assertEquals(4, model.getLaneNum(), 0.01);
	}
	
	@Test
	public void testSetResult() {
		model.setResult("Strike");
		assertEquals("Strike", model.getResult());
	}
	
	@Test
	public void testGetResult() {
		model.setResult("Spare");
		assertEquals("Spare", model.getResult());
	}
	
	@Test
	public void testAddShotNum() {
		model.addShotNum(1);
		assertEquals(1, model.getShotNum());
	}
	
	@Test
	public void testGetShotNum() {
		model.addShotNum(2);
		assertEquals(2, model.getShotNum());
	}
}