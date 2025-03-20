package edu.ycp.cs320.lab02.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList; // import the ArrayList class

//import edu.ycp.cs320.lab02.controller.NumbersController;
//import edu.ycp.cs320.lab02.model.GuessingGame;
//import edu.ycp.cs320.lab02.model.Numbers;

import edu.ycp.cs320.lab02.model.ShotObject;
import edu.ycp.cs320.lab02.model.Frame;
import edu.ycp.cs320.lab02.model.Ball;
import edu.ycp.cs320.lab02.model.Arsenal;

public class ShotServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 ShotObject shot = new ShotObject();
	 
	 @Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			// Populate ball arsenal with demo balls if first doGet
			
		
			
			System.out.println("Shot Servlet: doGet");	
			
		    request.getRequestDispatcher("/_view/shot.jsp").forward(request, response);
		}
	 
	 
	 
	 
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
            shot.setShotNum(Integer.parseInt(request.getParameter("shotNum")));
            
            // Populate count and leave arrays from request parameters
            for (int i = 0; i < 10; i++) {
            	shot.setCountIndividual(i, Integer.parseInt(request.getParameter("count" + i)));
            	shot.setLeaveIndividual(i, Integer.parseInt(request.getParameter("leave" + i)));
            }
            
            // Instantiate Ball and Frame objects
            Ball shotBall = new Ball(); // Modify if Ball has specific attributes
            Frame shotFrame = new Frame(); // Modify if Frame needs specific setup
                        
            
            // Set ShotObject as request attribute
            request.setAttribute("shot", shot);
            
            // Forward request to JSP page
            RequestDispatcher dispatcher = request.getRequestDispatcher("/shot.jsp");
            dispatcher.forward(request, response);
       
    }
}
