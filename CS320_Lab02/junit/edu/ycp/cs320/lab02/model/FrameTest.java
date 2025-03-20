package edu.ycp.cs320.lab02.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

import edu.ycp.cs320.lab02.model.Frame;

public class FrameTest {
    private Frame model;

    @Before
    public void setUp() {
        model = new Frame();
    }

    // Getters and Setters Tests
    @Test
    public void testSetFrameNum() {
        model.setFrameNum(3);
        assertEquals(3, model.getFrameNum());
    }

    @Test
    public void testGetFrameNum() {
        model.setFrameNum(7);
        assertEquals(7, model.getFrameNum());
    }

    @Test
    public void testSetLaneNum() {
        model.setLaneNum(6);
        assertEquals(6, model.getLaneNum());
    }

    @Test
    public void testGetLaneNum() {
        model.setLaneNum(3);
        assertEquals(3, model.getLaneNum());
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
    public void testSetShotNum() {
        model.setShotNum(1);
        assertEquals(1, model.getShotNum());
    }

    @Test
    public void testGetShotNum() {
        model.setShotNum(2);
        assertEquals(2, model.getShotNum());
    }

    // Tests of Frame Methods
    @Test
    public void testCancelFrame() {
        model.setShotNum(2);
        model.setResult("Strike");
        assertTrue(model.cancelFrame());
    }

    @Test
    public void testModifyFrame() {
        model.setShotNum(2);
        model.setResult("Strike");
        assertTrue(model.modifyFrame("Spare", Arrays.asList(1)));
    }
}
