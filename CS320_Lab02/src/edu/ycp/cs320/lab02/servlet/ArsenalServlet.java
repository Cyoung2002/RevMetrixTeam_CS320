package edu.ycp.cs320.lab02.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.lab02.model.Arsenal;


public class ArsenalServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Arsenal arsenal = new Arsenal("Demo");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    request.setAttribute("balls", arsenal.getBalls()); // Pass list of balls to JSP
	    request.getRequestDispatcher("/view/arsenal.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String action = request.getParameter("action");

	    if ("addNew".equals(action)) {
	        // Adding a new ball
	        String name = request.getParameter("name");
	        String color = request.getParameter("color");
	        double weight = Double.parseDouble(request.getParameter("weight"));

	        Ball newBall = new Ball(name, color, weight);
	        if (!arsenal.addNewBall(newBall)) {
	            response.getWriter().println("<html><body><h3>Error: This ball is already in your arsenal!</h3></body></html>");
	            return;
	        }

	    } else if ("addDuplicate".equals(action)) {
	        // Duplicating an existing ball
	        String[] ballData = request.getParameter("selectedBall").split(",");
	        Ball dupeBall = new Ball(ballData[0], ballData[1], Double.parseDouble(ballData[2]));
	        arsenal.duplicateBall(dupeBall);

	    } else if ("delete".equals(action)) {
	        // Deleting a selected ball
	        String[] ballData = request.getParameter("selectedBall").split(",");
	        Ball ballToDelete = new Ball(ballData[0], ballData[1], Double.parseDouble(ballData[2]));
	        arsenal.deleteBall(ballToDelete);
	    }

	    response.sendRedirect("/arsenal");
	}


}
