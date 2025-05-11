package edu.ycp.cs320.lab02.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.lab02.controller.InsertEventController;
import edu.ycp.cs320.booksdb.model.Event;
import edu.ycp.cs320.booksdb.model.Establishment;
import edu.ycp.cs320.lab02.controller.AllEstablishmentsController;
import edu.ycp.cs320.lab02.controller.StrikePercentageGameController;

public class StrikePercentageGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private StrikePercentageGameController controller = null;
	//private AllEstablishmentsController establishmentsController = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nStrikePercentageGameServlet: doGet");

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

		req.getRequestDispatcher("/_view/strikePercentageGame.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("\nStrikePercentageGameServlet: doPost");		
		
		String errorMessage = null;
		String successMessage = null;
		String gameID = null;
		Double percentResult = null;
		
		// Decode form parameters and dispatch to controller
		gameID = req.getParameter("gameID");
		
		if (gameID == null || gameID.equals("")) {
			
			errorMessage = "Please fill in required field";
		} else {
			controller = new StrikePercentageGameController();
			
			percentResult = controller.StrikePercentageGame(gameID);
			
		}
		
		// Add parameters as request attributes
		req.setAttribute("percentResult",  percentResult);
		
		// Add result objects as request attributes
		req.setAttribute("errorMessage",   errorMessage);
		req.setAttribute("successMessage", successMessage);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/strikePercentageGame.jsp").forward(req, resp);
	}	
}