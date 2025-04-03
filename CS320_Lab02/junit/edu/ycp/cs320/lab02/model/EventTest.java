package edu.ycp.cs320.lab02.model;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class EventTest {
    private Event event;

    @Before
    public void setUp() {
        event = new Event();
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
    public void testMakeBall() {
        Event event = new Event("Bowling Blitz", "League", "York, PA", "A", 178.4, 2);
        event.addNewEvent(event);
        
        assertEquals("Event should have the correct name.", "Bowling Blitz", event.getName());
        assertEquals("Event should have the correct type.", "League", event.getType());
        assertEquals("Event should have the correct location.", "York, PA", event.getLocation());
        assertEquals("Event should have the correct session.", "A", event.getSession());
        assertEquals("Event should have the correct stats.", 178.4, event.getEventStats(), 0.01);
        assertEquals("Event should have the correct standings.", 2, event.getStandings(), 0.01);
    }
}
