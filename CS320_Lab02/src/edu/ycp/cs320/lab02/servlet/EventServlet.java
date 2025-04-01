package edu.ycp.cs320.lab02.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.lab02.model.Event;

public class EventServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	Event event = new Event("Demo", "Demo", "Demo", "Demo", 0.0, 0);
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Event Servlet: doGet");	
		
		// Populate event list with demo events if first doGet
		if(event.getEvents().isEmpty()) {
			event.addNewEvent(event.makeEvent("Bowling Blitz", "League", "York, PA", "A", 178.4, 2));
			event.addNewEvent(event.makeEvent("Strikes and Spares", "Practice", "Hanover, PA", "Monday, March 31st", 156.9, 1));
			event.addNewEvent(event.makeEvent("Only 300s", "Tornament", "York, PA", "B.2", 300.0, 3));
		}
		
		request.setAttribute("events", event.getEvents()); 
	    request.getRequestDispatcher("/_view/event.jsp").forward(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // check action
		String action = request.getParameter("action");
		System.out.println(action);
	    
	    System.out.println("Event Servlet: doPost");

	    if ("addNew".equals(action)) {
	    	// check that it has entered addNew action
	    	System.out.println("addNew action");
	    	
	        // Adding a new Event
	        String name = request.getParameter("name");
	        String type = request.getParameter("type");
	        String location = request.getParameter("location");
	        String session = request.getParameter("session");
	        double eventStats = Double.parseDouble(request.getParameter("eventStats"));
	        int standings = Integer.parseInt(request.getParameter("standings"));

	        // Partial constructor for now
	        Event newEvent = event.makeEvent(name, type, location, session, eventStats, standings);
	        
	        if (!event.addNewEvent(newEvent)) {
	            response.getWriter().println("<html><body><h3>Error: This event is already in your event list!</h3></body></html>");
	            return;
	        }
	        System.out.println("new event added");
	        
	        // print ball arsenal to check
	        for(Event event : event.getEvents()) {
	        	System.out.println(event.getName() + " - " + event.getLocation());
	        }

	    } else if ("delete".equals(action)) {
	    	// Check that it has entered delete action
	    	System.out.println("delete action");
	    	
	        // Deleting a selected ball
	        String[] eventData = request.getParameter("selectedEventDelete").split(",");
	        
	        // Partial Constructor for now to compare temp argument against arsenal list
	        Event eventToDelete = event.makeEvent(eventData[0], eventData[1], eventData[2], eventData[3], Double.parseDouble(eventData[4]), Integer.parseInt(eventData[5]));
	        event.deleteEvent(eventToDelete);
	        System.out.println("Event deleted");
	    }
	    
	    System.out.println("Sending redirect");	   
	    response.sendRedirect(request.getContextPath() + "/event");
	}

}
