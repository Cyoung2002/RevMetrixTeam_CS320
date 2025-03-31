package edu.ycp.cs320.lab02.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.lab02.model.Establishment;

public class EstablishmentServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	Establishment establishment = new Establishment("Demo", "Demoo", "Demooo", "Demoooo");
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Establishment Servlet: doGet");	
		
		// Populate establishment list with demo establishments if first doGet
		if(establishment.getEstablishments().isEmpty()) {
			establishment.addNewEstablishment(establishment.makeEstablishment("Bowlerama", "York,PA", "777-777-7777", "12PM-8PM"));
			establishment.addNewEstablishment(establishment.makeEstablishment("BowlyBowly", "Hanover,PA", "767-777-7777", "12PM-7PM"));
			establishment.addNewEstablishment(establishment.makeEstablishment("BowlingFun", "Lancaster,PA", "717-777-7777", "12PM-9PM"));
		}
		
		request.setAttribute("establishments", establishment.getEstablishments()); 
	    request.getRequestDispatcher("/_view/establishment.jsp").forward(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // check action
		String action = request.getParameter("action");
		System.out.println(action);
	    
	    System.out.println("Establishment Servlet: doPost");

	    if ("addNew".equals(action)) {
	    	// check that it has entered addNew action
	    	System.out.println("addNew action");
	    	
	        // Adding a new Establishment
	        String name = request.getParameter("name");
	        String location = request.getParameter("location");
	        String phoneNumber = request.getParameter("phoneNumber");
	        String hours = request.getParameter("hours");

	        // Partial constructor for now
	        Establishment newEstablishment = establishment.makeEstablishment(name, location, phoneNumber, hours);
	        
	        if (!establishment.addNewEstablishment(newEstablishment)) {
	            response.getWriter().println("<html><body><h3>Error: This establishment is already in your establishment list!</h3></body></html>");
	            return;
	        }
	        System.out.println("new establishment added");
	        
	        // print ball arsenal to check
	        for(Establishment establishment : establishment.getEstablishments()) {
	        	System.out.println(establishment.getName() + " - " + establishment.getLocation() + " - " + establishment.getphoneNumber() + " - " + establishment.getHours());
	        }

	    } else if ("delete".equals(action)) {
	    	// Check that it has entered delete action
	    	System.out.println("delete action");
	    	
	        // Deleting a selected ball
	        String[] establishmentData = request.getParameter("selectedEstablishmentDelete").split(",");
	        
	        // Partial Constructor for now to compare temp argument against arsenal list
	        Establishment establishmentToDelete = establishment.makeEstablishment(establishmentData[0], establishmentData[1], establishmentData[2], establishmentData[3]);
	        establishment.deleteEstablishment(establishmentToDelete);
	        System.out.println("Establishment deleted");
	    }
	    
	    System.out.println("Sending redirect");	   
	    response.sendRedirect(request.getContextPath() + "/establishment");
	}

}
