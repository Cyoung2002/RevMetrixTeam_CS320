package edu.ycp.cs320.lab02.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;

import edu.ycp.cs320.lab02.model.Session;

public class SessionServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	Session session = new Session("Demo", "Demoo", "Demooo", "Demoooo", "Demo", 3, 2);
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Session Servlet: doGet");	
		
		request.setAttribute("sessions", session.getSessions()); 
	    request.getRequestDispatcher("/_view/session.jsp").forward(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // check action
		String action = request.getParameter("action");
		System.out.println(action);
	    
	    System.out.println("Session Servlet: doPost");

	    if ("addNew".equals(action)) {
	    	// check that it has entered addNew action
	    	System.out.println("addNew action");
	    	
	        // Adding a new Establishment
	        String establishment = request.getParameter("establishment");
	        String date = request.getParameter("date");
	        String time = request.getParameter("time");
	        String oppoTeam = request.getParameter("oppoTeam");
	        String oppoPlayer = request.getParameter("oppoPlayer");
	        int gameNum = Integer.parseInt(request.getParameter("numGames"));
	        int startLane = Integer.parseInt(request.getParameter("startLane"));

	        // Partial constructor for now
	        Session newSession = session.makeSession(establishment, date, time, oppoTeam, oppoPlayer, gameNum, startLane);
	        
	        if (!session.addNewSession(newSession)) {
	            response.getWriter().println("<html><body><h3>Error: This session is already in your session list!</h3></body></html>");
	            return;
	        }
	        System.out.println("new session added");
	        

	    } else if ("delete".equals(action)) {
	    	// Check that it has entered delete action
	    	System.out.println("delete action");
	    	
	        // Deleting a selected ball
	        String[] sessionData = request.getParameter("selectedSessionDelete").split(",");
	        
	        // Partial Constructor for now to compare temp argument against arsenal list
	        Session sessionToDelete = session.makeSession(sessionData[0], sessionData[1], sessionData[2], sessionData[3], sessionData[4], Integer.parseInt(sessionData[5]), Integer.parseInt(sessionData[6]));
	        session.deleteSession(sessionToDelete);
	        System.out.println("Session deleted");
	    }
	    
	    System.out.println("Sending redirect");	   
	    response.sendRedirect(request.getContextPath() + "/session");
	}

}
