package edu.ycp.cs320.lab02.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.lab02.controller.InsertEventController;

public class InsertEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private InsertEventController controller = null;	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nInsertEventServlet: doGet");

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

		req.getRequestDispatcher("/_view/insertEvent.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("\nInsertEventServlet: doPost");		
		
		String errorMessage = null;
		String successMessage = null;
		String longname = null;
		String shortname = null;
		String establishmentShort = null;
		String weeknight = null;
		String start = null;
		String end = null;
		int gamesPerSession = 0;
		
		// Decode form parameters and dispatch to controller
		longname = req.getParameter("event_longname");
		shortname = req.getParameter("event_shortname");
		establishmentShort = req.getParameter("event_establishmentShort");
		weeknight = req.getParameter("event_weeknight");
		start = req.getParameter("event_start");
		end = req.getParameter("event_end");
		gamesPerSession = Integer.parseInt(req.getParameter("event_gamesPerSession"));
		
		if (longname == null || longname.equals("") ||
			shortname == null || shortname.equals("")  ||
			establishmentShort == null || establishmentShort.equals("") ||
			weeknight == null || weeknight.equals("") ||
			start == null || start.equals("") ||
			end == null || end.equals("") ||
			gamesPerSession == 0) {
			
			errorMessage = "Please fill in all of the required fields";
		} else {
			controller = new InsertEventController();
			
			// get list of books returned from query			
			if (controller.insertEvent(longname, shortname, establishmentShort, weeknight, start, end, gamesPerSession)) {
				successMessage = longname;
			}
			else {
				errorMessage = "Failed to insert Event: " + longname;					
			}
		}
		
		// Add parameters as request attributes
		req.setAttribute("event_longname", longname);
		req.setAttribute("event_shortname",  shortname);
		req.setAttribute("event_establishmentShort", establishmentShort);
		req.setAttribute("event_weeknight", weeknight);
		req.setAttribute("event_start", start);
		req.setAttribute("event_end", end);
		req.setAttribute("event_gamesPerSession", gamesPerSession);
		
		// Add result objects as request attributes
		req.setAttribute("errorMessage",   errorMessage);
		req.setAttribute("successMessage", successMessage);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/insertEvent.jsp").forward(req, resp);
	}	
}