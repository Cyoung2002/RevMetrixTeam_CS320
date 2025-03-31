package edu.ycp.cs320.lab02.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.lab02.model.Arsenal;
import edu.ycp.cs320.lab02.model.Ball; 

public class GameServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	Arsenal arsenal = new Arsenal("Demo");
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("Arsenal Servlet: doGet");	
		
		// Populate ball arsenal with demo balls if first doGet
		if(arsenal.getBalls().isEmpty()) {
			arsenal.addNewBall(arsenal.makeBall("BigBall", "red", "BowlerPro", "Wobble", 9.0, 2.5));
			arsenal.addNewBall(arsenal.makeBall("Smallball", "blue", "BowlerPro", "Twist", 8.7, 6.8));
			arsenal.addNewBall(arsenal.makeBall("Corny", "yellow", "Motiv", "Differential", 8.5, 4.0));
		}
		
		request.setAttribute("balls", arsenal.getBalls()); 
	    request.getRequestDispatcher("/_view/arsenal.jsp").forward(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // check action
		String action = request.getParameter("action");
		System.out.println(action);
	    
	    System.out.println("Arsenal Servlet: doPost");

	    if ("addNew".equals(action)) {
	    	// check that it has entered addNew action
	    	System.out.println("addNew action");
	    	
	        // Adding a new ball
	    	String brand = request.getParameter("brand");
	        String name = request.getParameter("name");
	        String color = request.getParameter("color");
	        String core = request.getParameter("core");
	        double weight = Double.parseDouble(request.getParameter("weight"));
	        double diameter = Double.parseDouble(request.getParameter("diameter"));

	        // Partial constructor for now
	        Ball newBall = arsenal.makeBall(name, color, brand, core, diameter, weight);
	        
	        if (!arsenal.addNewBall(newBall)) {
	            response.getWriter().println("<html><body><h3>Error: This ball is already in your arsenal!</h3></body></html>");
	            return;
	        }
	        System.out.println("new ball added");
	        
	        // print ball arsenal to check
	        // for(Ball ball : arsenal.getBalls()) {
	        // 	 System.out.println(ball.getName() + " - " + ball.getColor() + " - " + ball.getWeight() + " lbs");
	        // }

	    } else if ("addDuplicate".equals(action)) {
	    	// Check that it has entered duplicate action
	    	System.out.println("duplicate action");
	    	
	        // Duplicating an existing ball
	        String[] ballData = request.getParameter("selectedBallDupe").split(",");
	        String nickname = request.getParameter("nickname");
	        
	        // Parse string from user selection
	        System.out.println(ballData[0]);						// brand
	        System.out.println(ballData[1]);						// name
	        System.out.println(ballData[2]);    					// color
	        System.out.println(ballData[3]);						// core
	        System.out.println(Double.parseDouble(ballData[4])); 	// weight
	        System.out.println(Double.parseDouble(ballData[5])); 	// diameter
	        
	        // constructor to compare temp ball against arsenal list
	        arsenal.duplicateBall(arsenal.makeBall(ballData[1], ballData[2], ballData[0], ballData[3], Double.parseDouble(ballData[5]), Double.parseDouble(ballData[4])), nickname);
	        System.out.println("ball duplicated");

	    } else if ("delete".equals(action)) {
	    	// Check that it has entered delete action
	    	System.out.println("delete action");
	    	
	        // Deleting a selected ball
	        String[] ballData = request.getParameter("selectedBallDelete").split(",");
	        
	        // Constructor to compare temp ball against arsenal list
	        Ball ballToDelete = arsenal.makeBall(ballData[1], ballData[2], ballData[0], ballData[3], Double.parseDouble(ballData[5]), Double.parseDouble(ballData[4]));
	        arsenal.deleteBall(ballToDelete);
	        System.out.println("Ball deleted");
	    }
	    
	    System.out.println("Sending redirect");	   
	    response.sendRedirect(request.getContextPath() + "/arsenal");
	}

}