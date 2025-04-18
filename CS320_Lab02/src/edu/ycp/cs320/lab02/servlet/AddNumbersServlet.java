package edu.ycp.cs320.lab02.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.lab02.controller.NumbersController;
import edu.ycp.cs320.lab02.model.GuessingGame;
import edu.ycp.cs320.lab02.model.Numbers;

public class AddNumbersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("AddNumbers Servlet: doGet");	
		
		// call JSP to generate empty form
		req.getRequestDispatcher("/_view/addNumbers.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("AddNumbers Servlet: doPost");
		
		Numbers model = new Numbers();
		NumbersController controller = new NumbersController();
		controller.setModel(model);
		

		// holds the error message text, if there is any
		String errorMessage = null;


		
		
		// decode POSTed form parameters and dispatch to controller
		try {
			
			try {
				
				Double first = getDoubleFromParameter(req.getParameter("first"));
				Double second = getDoubleFromParameter(req.getParameter("second"));
				Double third = getDoubleFromParameter(req.getParameter("third"));
		
				
				
				// check for errors in the form data before using is in a calculation
					controller.setAddNum(first, second, third);
					controller.add();
				
			
			
			}catch (NullPointerException e){
				
				errorMessage = "Please specify three numbers";
			}
			// otherwise, data is good, do the calculation
			// must create the controller each time, since it doesn't persist between POSTs
			// the view does not alter data, only controller methods should be used for that
			// thus, always call a controller method to operate on the data

			
		} catch (NumberFormatException e) {
			errorMessage = "Invalid double";
			
		}
		
		
		String firstStr = req.getParameter("first");
		String secondStr = req.getParameter("second");
		String thirdStr = req.getParameter("third");
//		String resultStr = req.getParameter("result");
		
		controller.setAddNumStr(firstStr,secondStr,thirdStr);
		
		req.setAttribute("numbers", model);
		
		// Add parameters as request attributes
		// this creates attributes named "first" and "second for the response, and grabs the
		// values that were originally assigned to the request attributes, also named "first" and "second"
		// they don't have to be named the same, but in this case, since we are passing them back
		// and forth, it's a good idea
		
		// add result objects as attributes
		// this adds the errorMessage text and the result to the response
		req.setAttribute("errorMessage", errorMessage);
	//	req.setAttribute("result", result);
		//same thing "numbers",model

		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/addNumbers.jsp").forward(req, resp);
	}

	// gets double from the request with attribute named s
	private Double getDoubleFromParameter(String s) {
		if (s == null || s.equals("")) {
			return null;
		} else {
			return Double.parseDouble(s);
		}
	}
}
