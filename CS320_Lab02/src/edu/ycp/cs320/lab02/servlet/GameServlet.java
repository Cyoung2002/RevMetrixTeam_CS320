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

@WebServlet("/GameServlet")
public class GameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Game game = (Game) session.getAttribute("game");
        
        if (game == null) {
            game = new Game(1, 1); // Default game number and starting lane
            session.setAttribute("game", game);
        }
        
        request.setAttribute("game", game);
        request.getRequestDispatcher("/game.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Game game = (Game) session.getAttribute("game");
        
        if (game == null) {
            game = new Game(1, 1);
            session.setAttribute("game", game);
        }
        
        // Handling shot input from ShotServlet
        String pinStr = request.getParameter("pins");
        if (pinStr != null) {
            try {
                int pins = Integer.parseInt(pinStr);
                ArrayList<Frame> frames = game.getFrames();
                Frame currentFrame;
                
                if (frames.isEmpty() || frames.get(frames.size() - 1).isComplete()) {
                    currentFrame = game.newFrame();
                } else {
                    currentFrame = frames.get(frames.size() - 1);
                }
                
                currentFrame.addShot(pins);
                game.updateScore(pins);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid pin count.");
            }
        }
        
        session.setAttribute("game", game);
        response.sendRedirect("game.jsp");
    }
}
