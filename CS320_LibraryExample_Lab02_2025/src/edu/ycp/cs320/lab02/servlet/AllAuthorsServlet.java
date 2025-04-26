package edu.ycp.cs320.lab02.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.booksdb.model.Author;
import edu.ycp.cs320.booksdb.model.Ball;
import edu.ycp.cs320.lab02.controller.ArsenalController;

public class AllAuthorsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//private AllAuthorsController controller = null;	
	private ArsenalController controller = null;	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nAllAuthorsBallsServlet: doGet");

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

		req.getRequestDispatcher("/_view/allAuthors.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("\nAllAuthorsBallsServlet: doPost");		

		//ArrayList<Author> authors = null;
		ArrayList<Ball> arsenal = null;
		String errorMessage       = null;

		controller = new ArsenalController();

		// get list of authors returned from query
		//authors = controller.getAllAuthors();
		arsenal = controller.getAllBalls();

		// any authors found?
		if (arsenal == null) {
			errorMessage = "No Balls found in arsenal";
		}

		// Add result objects as request attributes
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("arsenal", arsenal);

		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/allAuthors.jsp").forward(req, resp);
	}	
}
