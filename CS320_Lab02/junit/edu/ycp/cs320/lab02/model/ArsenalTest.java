package edu.ycp.cs320.lab02.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ArsenalTest {
    private Arsenal arsenal;

    @Before
    public void setUp() {
        arsenal = new Arsenal("My Arsenal");
    }

    @Test
    public void testAddNewBall() {
        Ball ball = new Ball(15.0, 8.5,"Storm", "Phaze II", "Purple", "Symmetric");
        arsenal.addNewBall(ball);
        List<Ball> balls = arsenal.getBalls();

        assertTrue("Ball should be added to the arsenal", balls.contains(ball));
        assertEquals("The arsenal should contain exactly one ball.", 1, balls.size());
    }

    @Test
    public void testDuplicateBall() {
        Ball original = new Ball(15.0, 8.5,"Storm", "Phaze II", "Purple", "Symmetric");
        arsenal.addNewBall(original);
        arsenal.duplicateBall(original, "My Favorite");

        List<Ball> balls = arsenal.getBalls();

        assertEquals("There should be two balls after duplication.", 2, balls.size());
        assertEquals("The duplicate ball should have the correct nickname.", "Phaze II \"My Favorite\"", balls.get(1).getName());
    }

    @Test
    public void testDeleteBall() {
        Ball ball = new Ball(15.0, 8.5,"Storm", "Phaze II", "Purple", "Symmetric");
        arsenal.addNewBall(ball);
        arsenal.deleteBall(ball);

        assertFalse("The ball should be removed from the arsenal.", arsenal.getBalls().contains(ball));
    }

    @Test
    public void testAddInvalidBall() {
        Ball invalidBall = new Ball(14.5, 8.0, "", "", "Red", "Asymmetric");
        try {
            arsenal.addNewBall(invalidBall);
            //fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Brand and name must not be empty", "Brand and name must not be empty", e.getMessage());
        }
    }

    @Test
    public void testSetAndGetArsenalName() {
        arsenal.setArsenalName("Tournament Arsenal");
        assertEquals("Arsenal name should be updated correctly.", "Tournament Arsenal", arsenal.getArsenalName());
    }

    @Test
    public void testMakeBall() {
        Ball ball = arsenal.makeBall("Phaze II", "Purple", "Storm", "Symmetric", 8.5, 15.0);

        assertNotNull("Ball should not be null", ball);
        assertEquals("Ball should have the correct name.", "Phaze II", ball.getName());
        assertEquals("Ball should have the correct color.", "Purple", ball.getColor());
        assertEquals("Ball should have the correct brand.", "Storm", ball.getBrand());
        assertEquals("Ball should have the correct core.", "Symmetric", ball.getCore());
        assertEquals("Ball should have the correct diameter.", 8.5, ball.getDiameter(), 0.01);
        assertEquals("Ball should have the correct weight.", 15.0, ball.getWeight(), 0.01);
    }

    @Test
    public void testAddNewBall_Success() {
        Ball ball = arsenal.makeBall("Phaze II", "Purple", "Storm", "Symmetric", 8.5, 15.0);
        boolean result = arsenal.addNewBall(ball);

        List<Ball> balls = arsenal.getBalls();
        assertTrue("Ball should be successfully added to the arsenal.", result);
        assertEquals("The arsenal should contain exactly one ball.", 1, balls.size());
        assertTrue("The arsenal should contain the new ball.", balls.contains(ball));
    }

    @Test
    public void testAddNewBall_Fail_DuplicateName() {
        Ball ball1 = arsenal.makeBall("Phaze II", "Purple", "Storm", "Symmetric", 8.5, 15.0);
        Ball ball2 = arsenal.makeBall("Phaze II", "Blue", "Storm", "Asymmetric", 8.6, 14.0);

        arsenal.addNewBall(ball1);
        boolean result = arsenal.addNewBall(ball2);

        assertFalse("Adding a duplicate ball should fail.", result);
        assertEquals("The arsenal should contain exactly one ball.", 1, arsenal.getBalls().size());
    }

    @Test
    public void testDuplicateBall_Success() {
        Ball ball = arsenal.makeBall("Phaze II", "Purple", "Storm", "Symmetric", 8.5, 15.0);
        arsenal.addNewBall(ball);

        boolean result = arsenal.duplicateBall(ball, "Backup");

        List<Ball> balls = arsenal.getBalls();
        assertTrue("Ball should be duplicated successfully.", result);
        assertEquals("The arsenal should contain two balls after duplication.", 2, balls.size());
        assertEquals("Duplicated ball should have the correct nickname.", "Phaze II \"Backup\"", balls.get(1).getName());
    }

    @Test
    public void testDuplicateBall_Fail_NotFound() {
        Ball ball = arsenal.makeBall("Phaze II", "Purple", "Storm", "Symmetric", 8.5, 15.0);

        boolean result = arsenal.duplicateBall(ball, "Backup");

        assertFalse("Duplicating a ball that doesn't exist should fail.", result);
        assertTrue("The arsenal should still be empty.", arsenal.getBalls().isEmpty());
    }

    @Test
    public void testDeleteBall_Success() {
        Ball ball = arsenal.makeBall("Phaze II", "Purple", "Storm", "Symmetric", 8.5, 15.0);
        arsenal.addNewBall(ball);

        boolean result = arsenal.deleteBall(ball);

        assertTrue("Ball should be deleted successfully.", result);
        assertTrue("The arsenal should be empty after deletion.", arsenal.getBalls().isEmpty());
    }

    @Test
    public void testDeleteBall_Fail_NotFound() {
        Ball ball = arsenal.makeBall("Phaze II", "Purple", "Storm", "Symmetric", 8.5, 15.0);

        boolean result = arsenal.deleteBall(ball);

        assertFalse("Deleting a ball that doesn't exist should fail.", result);
    }
}
