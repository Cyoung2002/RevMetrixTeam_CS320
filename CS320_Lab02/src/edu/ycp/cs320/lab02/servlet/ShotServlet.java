package edu.ycp.cs320.lab02.servlet;

import edu.ycp.cs320.lab02.model.ShotObject;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import edu.ycp.cs320.lab02.model.*;

public class ShotServlet extends HttpServlet {
    
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
	        throws ServletException, IOException {
	    
	    HttpSession session = req.getSession(true);
	    
	    // Initialize new frame if needed
	    if (session.getAttribute("currentFrame") == null) {
	        resetSession(session);
	    }

	    Frame currentFrame = (Frame) session.getAttribute("currentFrame");
	    ShotObject currentShot = currentFrame.getShot(currentFrame.getShotNum());
	    int shotNumber = currentShot.getShotNumber();

	    // Prepare data for JSP
	    req.setAttribute("shotNumber", shotNumber);
	    req.setAttribute("frameNumber", currentFrame.getFrameNum());
	    req.setAttribute("gameDate", new Date());
	    req.setAttribute("eventType", "Practice");
	    req.setAttribute("gameNumber", 1);
	    
	    // Get arsenal balls for dropdown
	    Arsenal arsenal = (Arsenal) session.getAttribute("arsenal");
	    req.setAttribute("arsenalBalls", arsenal.getBalls());
	    
	    // Set ball values for the view
	    if (shotNumber == 1) {
	        // First shot - get first ball if set
	        req.setAttribute("firstBall", currentShot.getSelectBall() != null ? 
	            ballToString(currentShot.getSelectBall()) : "");
	        req.setAttribute("secondBall", "");
	        
	        // All pins start standing for first shot
	        req.setAttribute("standingPins", Arrays.asList(7,8,9,10,4,5,6,2,3,1));
	        req.setAttribute("standingPinsString", "7,8,9,10,4,5,6,2,3,1"); 
	    } else {
	        // Second shot - get both balls
	        ShotObject firstShot = currentFrame.getShot(1);
	        req.setAttribute("firstBall", firstShot.getSelectBall() != null ? 
	            ballToString(firstShot.getSelectBall()) : "");
	        req.setAttribute("secondBall", currentShot.getSelectBall() != null ? 
	            ballToString(currentShot.getSelectBall()) : "");
	        
	        // Get standing pins from first shot
	        List<Integer> standingAfterFirstShot = firstShot.getStandingPins();
	        req.setAttribute("standingPins", standingAfterFirstShot);
	        
	        // Initialize currentShot's standing pins with first shot's standing pins
	        currentShot.setStandingPins(new HashSet<>(standingAfterFirstShot));
	        
	        // Convert to string for JS
	        req.setAttribute("standingPinsString", String.join(",", 
	            standingAfterFirstShot.stream().map(String::valueOf).toArray(String[]::new)));
	    }
	    
	    req.getRequestDispatcher("/_view/shot.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
	        throws ServletException, IOException {
	    HttpSession session = req.getSession();
	    Frame currentFrame = (Frame) session.getAttribute("currentFrame");
	    ShotObject currentShot = currentFrame.getShot(currentFrame.getShotNum());
	    int shotNumber = currentShot.getShotNumber();
	    
	    // Process ball selections
	    String firstBallStr = req.getParameter("firstBall");
	    String secondBallStr = req.getParameter("secondBall");
	    
	    Arsenal arsenal = (Arsenal) session.getAttribute("arsenal");
	    
	    if (shotNumber == 1) {
	        if (firstBallStr != null && !firstBallStr.isEmpty()) {
	            Ball selectedBall = parseBall(firstBallStr, arsenal);
	            currentShot.setSelectBall(selectedBall);
	        }
	    } else {
	        if (secondBallStr != null && !secondBallStr.isEmpty()) {
	            Ball selectedBall = parseBall(secondBallStr, arsenal);
	            currentShot.setSelectBall(selectedBall);
	        }
	    }

	    // Process STANDING pins selection
	    String standingPinsParam = req.getParameter("standingPins");
	    Set<Integer> standingPins = parseStandingPins(standingPinsParam);
	    currentShot.setStandingPins(standingPins);

	    // Process special marks
	    String specialMark = req.getParameter("specialMark");
	    if ("X".equals(specialMark)) {
	        currentShot.setAsStrike();
	        if (currentShot.isStrike()) {
	            // Handle strike
	            if (currentFrame.getFrameNum() == 10) {
	                // 10th frame special handling
	                ShotObject secondShot = new ShotObject(2);
	                currentFrame.addShot(secondShot);
	            } else {
	                // Normal frame - move to next
	                int nextFrameNum = currentFrame.getFrameNum() + 1;
	                session.setAttribute("frameNumber", nextFrameNum);
	                resetSession(session);
	            }
	            resp.sendRedirect("shot");
	            return;
	        }
	    }
	    else if ("/".equals(specialMark)) currentShot.setAsSpare();
	    else if ("F".equals(specialMark)) currentShot.setAsFoul();
	    else if ("-".equals(specialMark)) currentShot.setAsGutter();

	    if (currentShot.getShotNumber() == 1) {
	        // Add second shot to frame
	        ShotObject secondShot = new ShotObject(2);
	        if (currentShot.isStrike()) {
	            secondShot.setStandingPins(new HashSet<>());
	        } else {
	            secondShot.setStandingPins(new HashSet<>(standingPins));
	        }
	        currentFrame.addShot(secondShot);
	    } else {
	        // Frame complete - reset for new frame
	        int nextFrameNum = currentFrame.getFrameNum() < 10 ? 
	                          currentFrame.getFrameNum() + 1 : 1;
	        session.setAttribute("frameNumber", nextFrameNum);
	        resetSession(session);
	    }
	    
	    resp.sendRedirect("shot");
	}

    private Ball parseBall(String ballStr, Arsenal arsenal) {
        String[] parts = ballStr.split(",");
        if (parts.length != 6) return null;
        
        for (Ball ball : arsenal.getBalls()) {
            if (ball.getBrand().equals(parts[0]) && 
                ball.getName().equals(parts[1]) && 
                ball.getColor().equals(parts[2]) && 
                ball.getCore().equals(parts[3]) && 
                ball.getWeight() == Double.parseDouble(parts[4]) && 
                ball.getDiameter() == Double.parseDouble(parts[5])) {
                return ball;
            }
        }
        return null;
    }

    private String ballToString(Ball ball) {
        return ball.getBrand() + "," + ball.getName() + "," + ball.getColor() + "," + 
               ball.getCore() + "," + ball.getWeight() + "," + ball.getDiameter();
    }

    private Set<Integer> parseStandingPins(String param) {
        if (param == null || param.isEmpty()) return new HashSet<>();
        return Arrays.stream(param.split(","))
                     .map(Integer::parseInt)
                     .collect(Collectors.toSet());
    }

    private void resetSession(HttpSession session) {
        int frameNumber = session.getAttribute("frameNumber") != null ? 
                         (Integer) session.getAttribute("frameNumber") : 1;
        Frame currentFrame = new Frame(frameNumber, 1, ""); // Assuming lane 1
        currentFrame.addShot(new ShotObject(1)); // Add first shot
        
        session.setAttribute("currentFrame", currentFrame);
        session.setAttribute("frameNumber", frameNumber);
    }
}