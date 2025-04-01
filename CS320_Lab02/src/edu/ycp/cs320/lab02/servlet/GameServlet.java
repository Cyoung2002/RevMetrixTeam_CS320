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
import edu.ycp.cs320.lab02.model.Frame;
import edu.ycp.cs320.lab02.model.ShotObject;


public class GameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
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
    }

    @Override
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
    }
}
