package edu.ycp.cs320.lab02.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.lab02.controller.InsertBallController;
import edu.ycp.cs320.lab02.controller.InsertBookController;
import edu.ycp.cs320.lab02.controller.InsertSessionController;

public class InsertSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private InsertSessionController controller = null;	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nInsertSessionServlet: doGet");

		String user = (String) req.getSession().getAttribute("user");
		if (user == null) {
			System.out.println("   User: <" + user + "> not logged in or session timed out");
			
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		// now we have the user's User object,
		// proceed to handle request...
		
		System.out.println("   User: <" + user + "> logged in");

		req.getRequestDispatcher("/_view/insertSession.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("\nInsertSessionServlet: doPost");	
		
		
		String errorMessage   = null;
		String successMessage = null;
		String league      = null;
		String bowled       = null;
		String week      = null;
		String series       = null;

		
		// Decode form parameters and dispatch to controller
		league    = req.getParameter("league");
		bowled     = req.getParameter("bowled");
		week        = req.getParameter("week");
		series         = req.getParameter("series");
		
		if (league     		== null || league.equals("")  ||
			bowled     		== null || bowled.equals("")  ||
			week        	== null || week.equals("")    ||
			series          == null || series.equals("")) {
			
			errorMessage = "Please fill in all of the required fields";
		} else {
		controller = new InsertSessionController();
		}
		// convert published to integer now that it is valid
		// published = Integer.parseInt(strPublished);
		
		// get list of books returned from query			
		if (controller.insertSession(league, bowled, week, series)) {
			successMessage = week;
		}
		else {
			errorMessage = "Failed to insert Session - week: " + week;					
		}
		
		
		// Add parameters as request attributes
		req.setAttribute("league", league);
		req.setAttribute("bowled", bowled);
		req.setAttribute("week", week);
		req.setAttribute("series", series);
		
		
		// Add result objects as request attributes
		req.setAttribute("errorMessage",   errorMessage);
		req.setAttribute("successMessage", successMessage);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/insertSession.jsp").forward(req, resp);
	}	
}
