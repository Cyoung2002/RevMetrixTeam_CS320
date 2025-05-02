package edu.ycp.cs320.lab02.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.booksdb.model.Ball;
import edu.ycp.cs320.booksdb.model.Event;
import edu.ycp.cs320.lab02.controller.AllEventsController;
import edu.ycp.cs320.lab02.controller.InsertBallController;
import edu.ycp.cs320.lab02.controller.InsertBookController;
import edu.ycp.cs320.lab02.controller.InsertSessionController;
import edu.ycp.cs320.lab02.controller.ArsenalController;

public class InsertSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private InsertSessionController controller = null;	
	private AllEventsController eventsController = null;
	private ArsenalController arsenalController = null;

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
		
		ArrayList<Event> events = null;
		eventsController = new AllEventsController();
		events = eventsController.getEvents();
		req.setAttribute("events",  events);
		
		ArrayList<Ball> arsenal = null;
		arsenalController = new ArsenalController();
		arsenal = arsenalController.getAllBalls();
		req.setAttribute("arsenal",  arsenal);

		req.getRequestDispatcher("/_view/insertSession.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("\nInsertSessionServlet: doPost");	
		
		
		String errorMessage   = null;
		String successMessage = null;
		String league      	  = null;
		String bowled         = null;
		//String week      	  = null;
		String strikeBall     = null;
		String spareBall      = null;
		String ball      	  = null;
		String startLane      = null;
		String series         = null;

		
		// Decode form parameters and dispatch to controller
		league    = req.getParameter("league");
		bowled     = req.getParameter("bowled");
		strikeBall        = req.getParameter("strikeBall");
		spareBall        = req.getParameter("spareBall");
		startLane        = req.getParameter("startLane");
		//week        = req.getParameter("week");
		series         = req.getParameter("series");
		
		if (league     		== null || league.equals("")  ||
			bowled     		== null || bowled.equals("")  ||
			strikeBall      == null || strikeBall.equals("")    ||
			spareBall       == null || spareBall.equals("")     ||
			startLane       == null || startLane.equals("")     ||
			series          == null || series.equals("")) {
			
			errorMessage = "Please fill in all of the required fields";
		} else {
			controller = new InsertSessionController();
			if(strikeBall.equals(spareBall)) {
				ball = strikeBall;
			}
			else {
				ball = strikeBall + "/" + spareBall;
			}
		}
		
		
		// convert published to integer now that it is valid
		// published = Integer.parseInt(strPublished);
		Integer weekID = controller.insertSession(league, bowled, ball, startLane, "week", series);
		// get list of books returned from query			
		if (weekID >= 1) {
			successMessage = "League: " + league + " - Bowled: " + bowled+ " - Ball: " + ball+ " - Start Lane: " + startLane+ " - Week: " + weekID+ " - Series: " + series;
		}
		else {
			errorMessage = "Failed to insert Session - week: " + weekID;					
		}
		
		ArrayList<Event> events = null;
		eventsController = new AllEventsController();
		events = eventsController.getEvents();
		req.setAttribute("events",  events);
		
		ArrayList<Ball> arsenal = null;
		arsenalController = new ArsenalController();
		arsenal = arsenalController.getAllBalls();
		req.setAttribute("arsenal",  arsenal);
		
		// Add parameters as request attributes
		req.setAttribute("league", league);
		req.setAttribute("bowled", bowled);
		req.setAttribute("startLane",startLane);
		req.setAttribute("week", weekID);
		req.setAttribute("series", series);
		
		// Add result objects as request attributes
		req.setAttribute("errorMessage",   errorMessage);
		req.setAttribute("successMessage", successMessage);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/insertSession.jsp").forward(req, resp);
	}	
}
