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


@WebServlet("/game")
public class GameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve or create game object
        Game game = (Game) request.getSession().getAttribute("game");

        if (game == null) {
            game = new Game(1, 1); // Initialize new game
            request.getSession().setAttribute("game", game);
        }

        request.getRequestDispatcher("/_view/game.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Game game = (Game) request.getSession().getAttribute("game");

        if (game == null) {
            game = new Game(1, 1);
            request.getSession().setAttribute("game", game);
        }

        // Add new frame if requested
        if (request.getParameter("newFrame") != null) {
            game.newFrame();
        }

        request.getSession().setAttribute("game", game);
        response.sendRedirect("game");
    }
}
