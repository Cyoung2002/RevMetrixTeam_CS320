package edu.ycp.cs320.lab02.servlet;

import edu.ycp.cs320.lab02.model.ShotObject;
import edu.ycp.cs320.lab02.model.Game;
import edu.ycp.cs320.lab02.model.Frame;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

public class ShotServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
	        throws ServletException, IOException {
	    
	    HttpSession session = req.getSession(true);
	    Game game = (Game) session.getAttribute("game");

	    if (game == null) {
	        game = new Game(1, 1);  // Create a new game if none exists
	        session.setAttribute("game", game);
	    }

	    Frame currentFrame = game.getCurrentFrame();  // Get the active frame

	    // Ensure frame tracking is correct
	    int frameNumber = game.getFrames().size();
	    session.setAttribute("frameNumber", frameNumber);

	    // Initialize new shot if needed
	    ShotObject currentShot = (ShotObject) session.getAttribute("currentShot");
	    if (currentShot == null) {
	        currentShot = new ShotObject(1); // Start with first shot
	        session.setAttribute("currentShot", currentShot);
	    }

	    int shotNumber = currentShot.getShotNumber();

	    // Prepare data for JSP
	    req.setAttribute("shotNumber", shotNumber);
	    req.setAttribute("frameNumber", frameNumber);
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

	        if (firstShot != null) {
	            List<Integer> standingAfterFirstShot = firstShot.getStandingPins();
	            req.setAttribute("standingPins", standingAfterFirstShot);
	            
	            // Convert current selections to string for JS
	            StringBuilder sb = new StringBuilder();
	            for (Integer pin : currentShot.getStandingPins()) {
	                if (sb.length() > 0) sb.append(",");
	                sb.append(pin);
	            }
	            req.setAttribute("standingPinsString", sb.toString());
	        } else {
	            req.setAttribute("standingPins", Arrays.asList(7,8,9,10,4,5,6,2,3,1));
	            req.setAttribute("standingPinsString", "");
	        }
	    }

	    req.getRequestDispatcher("/_view/shot.jsp").forward(req, resp);
	}

	
    /* @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        HttpSession session = req.getSession(true);
        Game game = (Game) session.getAttribute("game");

		if (game == null) {
			game = new Game(1, 1);  // Create new game if none exists
			session.setAttribute("game", game);
		}
		
		Frame currentFrame = game.getCurrentFrame();  // Get active frame
		// Ensure session tracking is correct
        int frameNumber = game.getFrames().size();
        session.setAttribute("frameNumber", frameNumber);
        
        // Initialize new shot if needed
        ShotObject currentShot = (ShotObject) session.getAttribute("currentShot");
        if (currentShot == null) {
        	currentShot = new ShotObject(1); // First shot in frame
            session.setAttribute("currentShot", currentShot);
        }
       
        //ShotObject currentShot = (ShotObject) session.getAttribute("currentShot");
        
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
            
            // Convert current selections to string for JS
            StringBuilder sb = new StringBuilder();
            for (Integer pin : currentShot.getStandingPins()) {
                if (sb.length() > 0) sb.append(",");
                sb.append(pin);
            }
            req.setAttribute("standingPinsString", sb.toString());
        }
     
        req.getRequestDispatcher("/_view/shot.jsp").forward(req, resp);
    } */
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        ShotObject currentShot = (ShotObject) session.getAttribute("currentShot");
        Game game = (Game) session.getAttribute("game");

        // Ensure game exists
        if (game == null) {
            game = new Game(1, 1);
            session.setAttribute("game", game);
        }

        // Ensure currentShot exists
        if (currentShot == null) {
            currentShot = new ShotObject(1);
            session.setAttribute("currentShot", currentShot);
        }

        // Ensure there is an active frame
        Frame currentFrame = game.getCurrentFrame();
        if (currentFrame == null) {
            game.newFrame();
            currentFrame = game.getCurrentFrame();
        }

        // Process STANDING pins selection
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

        // Add shot to the current frame
        boolean shotAdded = currentFrame.addShot(currentShot);

        if (!shotAdded && game.getFrames().size() < 10) {
            // If the shot wasn't added, create a new frame and add the shot
            game.newFrame();
            game.getCurrentFrame().addShot(currentShot);
        }

        // Move to the next shot or frame
        if (currentShot.getShotNumber() == 1) {
            // First shot was just entered, move to second shot
            session.setAttribute("firstShot", currentShot);
            session.setAttribute("currentShot", new ShotObject(2));
        } else {
            // Second shot was just entered, move to next frame
            session.setAttribute("frameNumber", game.getFrames().size());
            session.setAttribute("game", game);
            resetSession(session);
        }

        resp.sendRedirect("shot");
    }


    /* @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        HttpSession session = req.getSession();
        ShotObject currentShot = (ShotObject) session.getAttribute("currentShot");
        Game game = (Game) session.getAttribute("game");

		if (game == null) {
			game = new Game(1, 1);
			session.setAttribute("game", game);
		}
        
        // Process STANDING pins selection
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
        
        // store shot in games current frame unless there are already 2
        // if so make new frame in session game
        if((!(game.getCurrentFrame().addShot(currentShot))) && (game.getFrames().size() < 10)) {
        	game.newFrame();
        	game.getCurrentFrame().addShot(currentShot);
        };
        
        if (currentShot.getShotNumber() == 1) {
            // Store first shot and prepare second
            session.setAttribute("firstShot", currentShot);
            
            currentShot.setShotNumber(2);
            // Reset standing pins for second shot
            currentShot.setStandingPins(new HashSet<>());
        } else {
            // Frame complete - reset for new frame
            int frameNum = (int) session.getAttribute("frameNumber");
            session.setAttribute("frameNumber", game.getFrames().size() < 10 ? frameNum + 1 : 1);
            session.setAttribute("game", game);
            resetSession(session);
        }
        
        resp.sendRedirect("shot");
    }*/

    private void resetSession(HttpSession session) {
        session.setAttribute("currentShot", new ShotObject(1));
        session.setAttribute("firstShot", null);
        //if (session.getAttribute("frameNumber") == null) {
        //    session.setAttribute("frameNumber", 1);
        //}
     // Get the game object to update frame tracking
        Game game = (Game) session.getAttribute("game");
        
        if (game != null) {
            session.setAttribute("frameNumber", game.getFrames().size());
        }
    } 
}