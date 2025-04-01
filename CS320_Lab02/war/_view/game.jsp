<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="edu.ycp.cs320.lab02.model.Game, edu.ycp.cs320.lab02.model.Frame" %> 
<%@ page import="java.util.ArrayList" %> 
<html lang="en">

<head>
    <title>Bowling Game</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            text-align: center;
        }
        table {
            margin: auto;
            border-collapse: collapse;
            width: 80%;
        }
        th, td {
            border: 1px solid black;
            padding: 10px;
            text-align: center;
        }
        .button {
            padding: 10px;
            margin: 10px;
            background-color: #28a745;
            color: white;
            border: none;
            cursor: pointer;
        }
    </style>
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

    <table>
        <tr>
            <th>Frame</th>
            <th>Lane</th>
            <th>Score</th>
            <th>Shots</th>
        </tr>

        <% for (Frame frame : frames) { %>
        <tr>
            <td><%= frame.getFrameNum() %></td>
            <td><%= frame.getLaneNum() %></td>
            <td><%= frame.getPinScore() %></td>
            <td>
                <% for (int i = 1; i <= frame.getShotNum(); i++) { %>
                    Shot <%= i %>: <%= frame.getShot(i) != null ? frame.getShot(i).getPinsKnockedDown().size() : "-" %> |
                <% } %>
            </td>
        </tr>
        <% } %>
    </table>

    <form action="game" method="post">
        <button type="submit" name="newFrame" class="button">Add New Frame</button>
    </form>

    <a href="shot">Go to Shot Entry</a>

</body>
</html>
