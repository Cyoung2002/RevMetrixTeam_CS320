package edu.ycp.cs320.lab02.servlet;

import edu.ycp.cs320.lab02.model.ShotObject;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;
import edu.ycp.cs320.lab02.model.*;

public class ShotServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        HttpSession session = req.getSession(true);
        
        Arsenal arsenal = (Arsenal) session.getAttribute("arsenal");
        
        req.setAttribute("arsenalBalls", arsenal.getBalls());
        
        // Initialize new shot if needed
        if (session.getAttribute("currentShot") == null) {
            resetSession(session);
        }

        ShotObject currentShot = (ShotObject) session.getAttribute("currentShot");
        int shotNumber = currentShot.getShotNumber();

        // Prepare data for JSP
        req.setAttribute("shotNumber", shotNumber);
        req.setAttribute("frameNumber", session.getAttribute("frameNumber"));
        req.setAttribute("gameDate", new Date());
        req.setAttribute("eventType", "Practice");
        req.setAttribute("gameNumber", 1);
        
        if (shotNumber == 1) {
            // First shot - all pins start standing
            req.setAttribute("standingPins", Arrays.asList(7,8,9,10,4,5,6,2,3,1));
            req.setAttribute("standingPinsString", "");
        } else {
            // Second shot - get standing pins from first shot
            ShotObject firstShot = (ShotObject) session.getAttribute("firstShot");
            List<Integer> standingAfterFirstShot = firstShot.getStandingPins();
            req.setAttribute("standingPins", standingAfterFirstShot);
            
            // Initialize currentShot's standing pins with first shot's standing pins
            currentShot.setStandingPins(new HashSet<>(standingAfterFirstShot));
            
            // Convert to string for JS
            req.setAttribute("standingPinsString", String.join(",", 
                standingAfterFirstShot.stream().map(String::valueOf).toArray(String[]::new)));
        }
        
        req.setAttribute("arsenalBalls", Arsenal.getBalls());
        
        req.getRequestDispatcher("/_view/shot.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String selectedBallName = req.getParameter("ball");
        if (selectedBallName != null && !selectedBallName.isEmpty()) {
            HttpSession session = req.getSession();
            ShotObject currentShot = (ShotObject) session.getAttribute("currentShot");
            
            // Get the actual ball object from arsenal
            Arsenal arsenal = (Arsenal) session.getAttribute("arsenal");
            for (Ball ball : arsenal.getBalls()) {
                if (ball.getName().equals(selectedBallName)) {
                    currentShot.setSelectBall(ball); // You'll need to add this method
                    break;
                }
            }
        }
        // Process STANDING pins selection
        Set<Integer> standingPins = new HashSet<>();
        String standingPinsParam = req.getParameter("standingPins");
        if (standingPinsParam != null && !standingPinsParam.isEmpty()) {
            String[] pinStrings = standingPinsParam.split(",");
            for (String pinStr : pinStrings) {
                standingPins.add(Integer.parseInt(pinStr));
            }
        }
        currentShot.setStandingPins(standingPins);

        // Process special marks
        String specialMark = req.getParameter("specialMark");
        if ("X".equals(specialMark)) currentShot.setAsStrike();
        else if ("/".equals(specialMark)) currentShot.setAsSpare();
        else if ("F".equals(specialMark)) currentShot.setAsFoul();
        else if ("-".equals(specialMark)) currentShot.setAsGutter();

        if (currentShot.getShotNumber() == 1) {
            // Store first shot and prepare second
            session.setAttribute("firstShot", currentShot);
            
            // Create new shot for second attempt
            ShotObject secondShot = new ShotObject(2);
            // Carry over the standing pins from first shot
            secondShot.setStandingPins(new HashSet<Integer>(standingPins));
            session.setAttribute("currentShot", secondShot);
        } else {
            // Frame complete - reset for new frame
            int frameNum = (Integer) session.getAttribute("frameNumber");
            session.setAttribute("frameNumber", frameNum < 10 ? frameNum + 1 : 1);
            resetSession(session);
        }
        
        resp.sendRedirect("shot");
    }

    private Set<Integer> parseStandingPins(String param) {
        if (param == null || param.isEmpty()) return new HashSet<>();
        return Arrays.stream(param.split(","))
                     .map(Integer::parseInt)
                     .collect(Collectors.toSet());
    }

    private void resetSession(HttpSession session) {
        session.setAttribute("currentShot", new ShotObject(1));
        session.setAttribute("firstShot", null);
        if (session.getAttribute("frameNumber") == null) {
            session.setAttribute("frameNumber", 1);
        }
    }
}