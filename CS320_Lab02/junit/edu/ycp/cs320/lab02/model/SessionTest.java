package edu.ycp.cs320.lab02.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class SessionTest {
    private Session session;
    private Session model;

    @Before
    public void setUp() {
        session = new Session();
        model = new Session();
    }

    @Test
    public void testAddNewSession() {
        Session session = new Session("Demo", "Demoo", "Demooo", "Demoooo", "Demo", 3, 2);
        session.addNewSession(session);
        List<Session> sessions = session.getSessions();

        assertTrue("Session should be added", sessions.contains(session));
        assertEquals("There should be only 1 session.", 1, sessions.size());
    }

    @Test
    public void testDeleteSession() {
        Session session = new Session("Demo", "Demoo", "Demooo", "Demoooo", "Demo", 3, 2);
        session.addNewSession(session);
        session.deleteSession(session);

        assertFalse("The session should be removed.", session.getSessions().contains(session));
    }

    @Test
    public void testMakeSession() {
        Session session = new Session("Demo", "Demoo", "Demooo", "Demoooo", "Demooooo", 3, 2);
        session.addNewSession(session);
        
        assertEquals("Session should have the correct establishment.", "Demo", session.getEstablishment());
        assertEquals("Session should have the correct date.", "Demoo", session.getDate());
        assertEquals("Session should have the correct time.", "Demooo", session.getTime());
        assertEquals("Session should have the correct opposing team.", "Demoooo", session.getOppoTeam());
        assertEquals("Session should have the correct opposing player.", "Demooooo", session.getOppoPlayer());
        assertEquals("Session should have the correct number of games.", 3, session.getNumGames());
        assertEquals("Session should have the correct starting lane.", 2, session.getStartLane());
    }
    
    // Getters and Setters Tests
    @Test
    public void testGetSessionEstablishment() {
        model.setEstablishment("Bowlerama");
        assertEquals("Bowlerama", model.getEstablishment());
    }
    
    @Test
    public void testSetSessionEstablishment() {
        model.setEstablishment("BowlyBowly");
        assertEquals("BowlyBowly", model.getEstablishment());
    }
    
    @Test
    public void testGetSessionDate() {
        model.setDate("April 12th");
        assertEquals("April 12th", model.getDate());
    }
    
    @Test
    public void testSetSessionDate() {
        model.setDate("March 31st");
        assertEquals("March 31st", model.getDate());
    }
    
    @Test
    public void testGetSessionTime() {
        model.setTime("7:00 AM");
        assertEquals("7:00 AM", model.getTime());
    }
    
    @Test
    public void testSetSessionTime() {
        model.setTime("8:00 PM");
        assertEquals("8:00 PM", model.getTime());
    }
    
    @Test
    public void testGetSessionOppoTeam() {
        model.setOppoTeam("Lucky Strike");
        assertEquals("Lucky Strike", model.getOppoTeam());
    }
    
    @Test
    public void testSetSessionOppoTeam() {
        model.setOppoTeam("Lucky Strike");
        assertEquals("Lucky Strike", model.getOppoTeam());
    }
    
    @Test
    public void testGetSessionOppoPlayer() {
        model.setOppoPlayer("Caroline");
        assertEquals("Caroline", model.getOppoPlayer());
    }
    
    @Test
    public void testSetSessionOppoPlayer() {
        model.setOppoPlayer("Collin");
        assertEquals("Collin", model.getOppoPlayer());
    }
    
    @Test
    public void testGetSessionNumGames() {
        model.setNumGames(1);
        assertEquals(1, model.getNumGames());
    }
    
    @Test
    public void testSetSessionNumGames() {
        model.setNumGames(3);
        assertEquals(3, model.getNumGames());
    }
    @Test
    public void testGetSessionStartlane() {
        model.setStartLane(11);
        assertEquals(11, model.getStartLane());
    }
    
    @Test
    public void testSetSessionStartlane() {
        model.setStartLane(15);
        assertEquals(15, model.getStartLane());
    }
}
