package edu.ycp.cs320.lab02.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class EventTest {
    private Event event;
    private Event model;

    @Before
    public void setUp() {
        event = new Event();
        model = new Event();
    }

    @Test
    public void testAddNewEvent() {
        Event event = new Event("Bowling Blitz", "League", "York, PA", "A", 178.4, 2);
        event.addNewEvent(event);
        List<Event> events = event.getEvents();

        assertTrue("Event should be added", events.contains(event));
        assertEquals("There should be only 1 event.", 1, events.size());
    }

    @Test
    public void testDeleteEvent() {
        Event event = new Event("Only 300s", "Tournament", "York, PA", "B.2", 300.0, 3);
        event.addNewEvent(event);
        event.deleteEvent(event);

        assertFalse("The event should be removed.", event.getEvents().contains(event));
    }

    @Test
    public void testMakeEvent() {
        Event event = new Event("Bowling Blitz", "League", "York, PA", "A", 178.4, 2);
        event.addNewEvent(event);
        
        assertEquals("Event should have the correct name.", "Bowling Blitz", event.getName());
        assertEquals("Event should have the correct type.", "League", event.getType());
        assertEquals("Event should have the correct location.", "York, PA", event.getLocation());
        assertEquals("Event should have the correct session.", "A", event.getSession());
        assertEquals("Event should have the correct stats.", 178.4, event.getEventStats(), 0.01);
        assertEquals("Event should have the correct standings.", 2, event.getStandings(), 0.01);
    }
    
    // Getters and Setters Tests
    @Test
    public void testGetEventName() {
        model.setName("Bowling Blitz");
        assertEquals("Bowling Blitz", model.getName());
    }
    
    @Test
    public void testSetEventName() {
        model.setName("Only 300s");
        assertEquals("Only 300s", model.getName());
    }
    
    @Test
    public void testGetEventType() {
        model.setType("Practice");
        assertEquals("Practice", model.getType());
    }
    
    @Test
    public void testSetEventType() {
        model.setType("League");
        assertEquals("League", model.getType());
    }
    
    @Test
    public void testGetEventLocation() {
        model.setLocation("York, PA");
        assertEquals("York, PA", model.getLocation());
    }
    
    @Test
    public void testSetEventLocation() {
        model.setLocation("Hanover");
        assertEquals("Hanover", model.getLocation());
    }
    
    @Test
    public void testGetEventSession() {
        model.setSession("April 10th");
        assertEquals("April 10th", model.getSession());
    }
    
    @Test
    public void testSetEventSession() {
        model.setSession("B");
        assertEquals("B", model.getSession());
    }
    
    @Test
    public void testGetEventStats() {
        model.setEventStats(218.0);
        assertEquals(218.0, model.getEventStats(), 0.01);
    }
    
    @Test
    public void testSetEventStats() {
        model.setEventStats(165.0);
        assertEquals(165, model.getEventStats(), 0.01);
    }
    
    @Test
    public void testGetEventStandings() {
        model.setStandings(1);
        assertEquals(1, model.getStandings());
    }
    
    @Test
    public void testSetEventStandings() {
        model.setStandings(3);
        assertEquals(3, model.getStandings());
    }
}
