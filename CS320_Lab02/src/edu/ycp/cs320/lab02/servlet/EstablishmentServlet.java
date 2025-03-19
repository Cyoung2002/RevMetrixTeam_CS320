package edu.ycp.cs320.lab02.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.lab02.model.Establishment;

public class EstablishmentServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	Establishment establishment = new Establishment();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Populate ball arsenal with demo balls
		
		
		Establishment establish = establishment.makeEstablishment("Name", "Location", "PhoneNumber", "Hours");
		establishment.addNewEstablishment(establish);
		establishment.addNewEstablishment(establishment.makeEstablishment("", "", "", ""));
		establishment.addNewEstablishment(establishment.makeEstablishment("", "", "", ""));
		establishment.addNewEstablishment(establishment.makeEstablishment("", "", "", ""));
		
		System.out.println("Establishment Servlet: doGet");	
		
		// Pass list of balls to JSP
		request.setAttribute("establishment", establishment.getEstablishments()); 
	    request.getRequestDispatcher("/_view/establishment.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		String action = request.getParameter("action");
	    
	    System.out.println("Establishment Servlet: doPost");

	    if ("addNew".equals(action)) {
	        // Adding a new ball
	    	String name = request.getParameter("name");
	    	String location = request.getParameter("location");
	    	String phoneNumber = request.getParameter("phoneNumber");
	    	String hours = request.getParameter("hours");

	        // Establishment newEstablishment = new Establishment(name, location, phoneNumber, hours);
	        Establishment newEstablishment = establishment.makeEstablishment(name, location, phoneNumber, hours);
	        
	        if (!establishment.addNewEstablishment(newEstablishment)) {
	            response.getWriter().println("<html><body><h3>Error: This establishment is already in your list of establishments!</h3></body></html>");
	            return;
	        }

	    } else if ("delete".equals(action)) {
	        // Deleting a selected establishment
	        String[] establishmentData = request.getParameter("selectedEstablishment").split(",");

	        Establishment establishmentToDelete = establishment.makeEstablishment(establishmentData[0], establishmentData[1], establishmentData[2], establishmentData[3]);
	        establishment.deleteEstablishment(establishmentToDelete);
	    }

	    response.sendRedirect("/_view/establishment.jsp");
	    //request.getRequestDispatcher("/_view/establishment.jsp").forward(request, response);
	}

}