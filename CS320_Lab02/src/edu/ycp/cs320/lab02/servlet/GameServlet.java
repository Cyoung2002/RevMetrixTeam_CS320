package edu.ycp.cs320.lab02.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import edu.ycp.cs320.lab02.model.Game;




public class GameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        Game game = (Game) session.getAttribute("game");

        if (game == null) {
            game = new Game(1, 1);  // Ensure a game exists
            session.setAttribute("game", game);
        }

        // Pass game data to JSP
        req.setAttribute("game", game);
        req.setAttribute("frames", game.getFrames());
        req.setAttribute("score", game.getScore());

        req.getRequestDispatcher("/_view/game.jsp").forward(req, resp);
    }
    
    /*@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Game game = (Game) session.getAttribute("game");
        //Game populatedGame = new Game();

        if (game == null) {
            game = new Game(1, 1); // Initialize a new game
            session.setAttribute("game", game);
            //populatedGame
        }

        request.getRequestDispatcher("/_view/game.jsp").forward(request, response);
    }*/
    
    /* @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Game game = (Game) session.getAttribute("game");

        if (game == null) {
            game = new Game(1, 1);
            session.setAttribute("game", game);
        }

        // Send only completed frames to JSP
        // ArrayList<Frame> completedFrames = new ArrayList<>();
        ArrayList<Frame> completedFrames = new ArrayList<>();
        for (Frame frame : game.getFrames()) {
            if (frame.getShotNum() > 1 || !frame.getShots().isEmpty()) {  // Ensures only frames with shots are displayed
                completedFrames.add(frame);
            }
        }

        request.setAttribute("frames", completedFrames);
        request.getRequestDispatcher("/_view/game.jsp").forward(request, response);
    }*/
    /*@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        HttpSession session = req.getSession();
        
        Game game = (Game) session.getAttribute("game");

        if (game == null) {
            game = new Game(1, 1);  // Ensure a game exists
            session.setAttribute("game", game);
        }

        // Pass game data to JSP
        req.setAttribute("game", game);
        req.setAttribute("frames", game.getFrames());
        req.setAttribute("score", game.getScore());



    /*@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Game game = (Game) session.getAttribute("game");
        
        if (game == null) {
            game = new Game(1, 1);
            session.setAttribute("game", game);
        }

        // Retrieve shot data from session
        ShotObject currentShot = (ShotObject) session.getAttribute("currentShot");
        if (currentShot != null) {
            Frame currentFrame = game.getCurrentFrame();
            if(!currentFrame.addShot(currentShot)) {
            	//game.newFrame().addShot(currentShot);
            	currentFrame = game.newFrame();
                currentFrame.addShot(currentShot);
            };
        }

        if (request.getParameter("newFrame") != null) {
            game.newFrame();
        }

        session.setAttribute("game", game);
        response.sendRedirect(request.getContextPath() + "/game");
    }*/
    /*@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
        
        HttpSession session = req.getSession();
        Game game = (Game) session.getAttribute("game");

        if (game == null) {
            game = new Game(1, 1);
            session.setAttribute("game", game);
        }

        // Get the current frame (or create one if the game is empty)
        Frame currentFrame;
        if (game.getFrames().isEmpty()) {
            currentFrame = game.newFrame();
        } else {
            currentFrame = game.getCurrentFrame();
        }

        // Get shot number (1st or 2nd)
        int shotNumber = (currentFrame.getShotNum() <= 2) ? currentFrame.getShotNum() : 1;

        // Create a new ShotObject
        ShotObject currentShot = new ShotObject(shotNumber);

        // Process standing pins
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

        // If the frame is complete (2 shots), start a new frame for the next shot
        if (!shotAdded) {
            currentFrame = game.newFrame();
            currentFrame.addShot(currentShot);
        }

        // Save updated objects to session
        session.setAttribute("currentShot", currentShot);
        session.setAttribute("game", game);
        
        // Redirect back to shot entry page
        // resp.sendRedirect("shot");
        resp.sendRedirect(req.getContextPath() + "/game");

    }*/

}
