package edu.ycp.cs320.lab02.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.lab02.model.Arsenal;
import edu.ycp.cs320.lab02.model.Ball; 

public class ArsenalServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	Arsenal arsenal = new Arsenal("Demo");
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Populate ball arsenal with demo balls if first doGet
		if(arsenal.getBalls().isEmpty()) {
			Ball bally = arsenal.makeBall("BigBall", "red", 2.5);
			arsenal.addNewBall(bally);
			arsenal.addNewBall(arsenal.makeBall("smallball", "blue", 6.8));
			arsenal.addNewBall(arsenal.makeBall("corny", "yellow", 4.0));
			if(arsenal.getBalls().contains(bally)) {
				System.out.println("arsenal contains bally");
			}
			Ball bally2 = arsenal.makeBall("BigBall", "red", 2.5);
			if(arsenal.getBalls().contains(bally2)) {
				System.out.println("arsenal contains bally2");
			}
		}
		
		
		System.out.println("Arsenal Servlet: doGet");	
		
		// Pass list of balls to JSP
		request.setAttribute("balls", arsenal.getBalls()); 
	    request.getRequestDispatcher("/_view/arsenal.jsp").forward(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		String action = request.getParameter("action");
	    
	    System.out.println("Arsenal Servlet: doPost");

	    if ("addNew".equals(action)) {
	    	System.out.println("addNew action");
	        // Adding a new ball
	        String name = request.getParameter("name");
	        String color = request.getParameter("color");
	        double weight = Double.parseDouble(request.getParameter("weight"));

	        // Ball newBall = new Ball(name, color, weight);
	        Ball newBall = arsenal.makeBall(name, color, weight);
	        
	        if (!arsenal.addNewBall(newBall)) {
	            response.getWriter().println("<html><body><h3>Error: This ball is already in your arsenal!</h3></body></html>");
	            return;
	        }
	        System.out.println("new ball added");
	        for(Ball ball : arsenal.getBalls()) {
	        	System.out.println(ball.getName());
	        	System.out.println(ball.getColor());	
	        }

	    } else if ("addDuplicate".equals(action)) {
	    	System.out.println("duplicate action");
	        // Duplicating an existing ball
	        String[] ballData = request.getParameter("selectedBall").split(",");
	        // Ball dupeBall = new Ball(ballData[0], ballData[1], Double.parseDouble(ballData[2]));
	        // arsenal.duplicateBall(dupeBall);
	        arsenal.duplicateBall(arsenal.makeBall(ballData[0], ballData[1], Double.parseDouble(ballData[2])));
	        System.out.println("ball duplicated");

	    } else if ("delete".equals(action)) {
	    	System.out.println("delete action");
	        // Deleting a selected ball
	        String[] ballData = request.getParameter("selectedBall").split(",");
	        // Ball ballToDelete = new Ball(ballData[0], ballData[1], Double.parseDouble(ballData[2]));
	        Ball ballToDelete = arsenal.makeBall(ballData[0], ballData[1], Double.parseDouble(ballData[2]));
	        arsenal.deleteBall(ballToDelete);
	        System.out.println("ball deleted");
	    }

	    System.out.println("sending redirect");
	    response.sendRedirect("/_view/arsenal.jsp");
	    //request.getRequestDispatcher("/_view/arsenal.jsp").forward(request, response);
	}

}
