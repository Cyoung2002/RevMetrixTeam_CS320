package edu.ycp.cs320.lab02.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.booksdb.model.Session;
import edu.ycp.cs320.booksdb.persist.DatabaseProvider;
import edu.ycp.cs320.booksdb.persist.IDatabase;

public class OverallGameAverageForSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {

	    System.out.println("\nOverallGameAverageForSessionServlet: doGet");

	    // remove previous results if any
	    req.setAttribute("formSubmitted", false);
	    req.setAttribute("errorMessage", null);
	    req.setAttribute("percentResult", null);
	    req.setAttribute("sessionDate", null);

	    req.getRequestDispatcher("/_view/overallGameAverageForSession.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	        throws ServletException, IOException {

	    System.out.println("\nOverallGameAverageForSessionServlet: doPost");

	    String sessionDate = req.getParameter("sessionDate");
	    Double average = null;
	    String errorMessage = null;

	    req.setAttribute("formSubmitted", true);
	    req.setAttribute("sessionDate", sessionDate);

	    if (sessionDate == null || sessionDate.trim().isEmpty()) {
	        errorMessage = "Please enter a session date.";
	    } else {
	        IDatabase db = DatabaseProvider.getInstance();
	        ArrayList<Session> gameList = db.findGamesWithSessionDate(sessionDate);

	        if (gameList == null || gameList.isEmpty()) {
	            errorMessage = "No sessions found for the entered date.";
	        } else {
	            try {
	                int total = 0;
	                int count = 0;

	                for (Session session : gameList) {
	                    total += session.getGameOneScore();
	                    total += session.getGameTwoScore();
	                    total += session.getGameThreeScore();
	                    count += 3;
	                }

	                average = (double) total / count;
	            } catch (Exception e) {
	                errorMessage = "Error calculating average: " + e.getMessage();
	            }
	        }
	    }

	    req.setAttribute("percentResult", average);
	    req.setAttribute("errorMessage", errorMessage);

	    req.getRequestDispatcher("/_view/overallGameAverageForSession.jsp").forward(req, resp);
	}
}
