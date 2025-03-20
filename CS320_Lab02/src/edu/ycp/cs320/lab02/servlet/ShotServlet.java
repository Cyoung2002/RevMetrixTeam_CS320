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
	 ShotObject firstShot = new ShotObject();
	 ShotObject secondShot = new ShotObject();
	 
	 @Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			// Populate ball arsenal with demo balls if first doGet
			
			System.out.println("Shot Servlet: doGet");	
			
		    request.getRequestDispatcher("/_view/shot.jsp").forward(request, response);
		}
	 
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
            //count
            firstShot.modifyCount(0, Integer.parseInt(request.getParameter("firstShot")));
            //leave
            firstShot.modifyLeave(0, Integer.parseInt(request.getParameter("firstShot")));
            
            //count
            secondShot.modifyCount(Integer.parseInt(request.getParameter("firstShot")), Integer.parseInt(request.getParameter("secondShot")));
            //leave
            secondShot.modifyLeave(Integer.parseInt(request.getParameter("firstShot")), Integer.parseInt(request.getParameter("secondShot")));

           
            
            // Instantiate Ball and Frame objects
            Ball shotBall = new Ball(); // Modify if Ball has specific attributes
            Frame shotFrame = new Frame(); // Modify if Frame needs specific setup
                        
            
            // Set ShotObject as request attribute
            request.setAttribute("firstShot", firstShot);
            request.setAttribute("secondShot", secondShot);

                 
    }
}
