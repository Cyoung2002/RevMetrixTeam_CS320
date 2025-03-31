package edu.ycp.cs320.lab02.servlet;

import edu.ycp.cs320.lab02.model.ShotObject;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShotServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        HttpSession session = req.getSession(true);
        
        // Initialize new shot if needed
        if (session.getAttribute("currentShot") == null) {
            resetSession(session);
        }

        ShotObject currentShot = (ShotObject) session.getAttribute("currentShot");
        int shotNumber = currentShot.getShotNumber();

        // Prepare data for JSP
        req.setAttribute("shotNumber", shotNumber);
        req.setAttribute("frameNumber", session.getAttribute("frameNumber"));
        
        // Add additional attributes needed by JSP
        req.setAttribute("gameDate", new Date()); // Current date
        req.setAttribute("eventType", "Practice"); // Default event type
        req.setAttribute("gameNumber", 1); // Default game number
        
        if (shotNumber == 1) {
            // First shot - all pins available (empty standing pins)
            req.setAttribute("standingPins", Arrays.asList());
            req.setAttribute("standingPinsString", ""); // Empty string for JS
        } else {
            // Second shot - show first shot's standing pins
            ShotObject firstShot = (ShotObject) session.getAttribute("firstShot");
            List<Integer> standingPins = firstShot.getStandingPins();
            req.setAttribute("standingPins", standingPins);
            
            // Create comma-separated string for JavaScript
            StringBuilder sb = new StringBuilder();
            for (Integer pin : standingPins) {
                if (sb.length() > 0) sb.append(",");
                sb.append(pin);
            }
            req.setAttribute("standingPinsString", sb.toString());
        }
        
        req.getRequestDispatcher("/_view/shot.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        HttpSession session = req.getSession();
        ShotObject currentShot = (ShotObject) session.getAttribute("currentShot");
        
        // Process STANDING pins (inverse selection)
        String standingPinsParam = req.getParameter("standingPins");
        Set<Integer> standingPins = new HashSet<>();
        if (standingPinsParam != null && !standingPinsParam.isEmpty()) {
            for (String pinStr : standingPinsParam.split(",")) {
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
            currentShot.setShotNumber(2);
        } else {
            // Frame complete - reset for new frame
            int frameNum = (int) session.getAttribute("frameNumber");
            session.setAttribute("frameNumber", frameNum < 10 ? frameNum + 1 : 1);
            resetSession(session);
        }
        
        resp.sendRedirect("shot");
    }

    private void resetSession(HttpSession session) {
        session.setAttribute("currentShot", new ShotObject(1));
        session.setAttribute("firstShot", null);
        if (session.getAttribute("frameNumber") == null) {
            session.setAttribute("frameNumber", 1);
        }
    }
}