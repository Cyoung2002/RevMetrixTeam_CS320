<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="edu.ycp.cs320.lab02.model.Game, edu.ycp.cs320.lab02.model.Frame" %> 
<%@ page import="java.util.ArrayList" %> 
<html lang="en">


<html>
<head>
    <title>Bowling Scoreboard</title>
</head>
<body>
    <h1>Bowling Game Scoreboard</h1>
    
    <%
        Game game = (Game) session.getAttribute("game");
        if (game == null) {
            game = new Game(1, 1);
            session.setAttribute("game", game);
        }
        ArrayList<Frame> frames = game.getFrames();
    %>
    
    <h2>Game Number: <%= game.getGameNumber() %></h2>
    <h3>Current Lane: <%= game.getLane() %></h3>
    <h3>Total Score: <%= game.getScore() %></h3>
    
    <table border="1">
        <tr>
            <th>Frame</th>
            <th>Shot 1</th>
            <th>Shot 2</th>
            <th>Score</th>
        </tr>
        <% for (int i = 0; i < 10; i++) {
            Frame frame = frames.get(i);
        %>
        <tr>
            <td><%= (i + 1) %></td>
            <td><%= frame.getShot1() %></td>
            <td><%= frame.getShot2() %></td>
            <td><%= frame.getScore() %></td>
        </tr>
        <% } %>
    </table>
    
    <form action="GameServlet" method="post">
        <label for="pins">Enter Pins Knocked Down:</label>
        <input type="number" name="pins" min="0" max="10" required>
        <button type="submit">Submit Shot</button>
    </form>

    <form action="ShotServlet" method="post">
        <button type="submit">Go to Shot Entry</button>
    </form>
</body>
</html>
