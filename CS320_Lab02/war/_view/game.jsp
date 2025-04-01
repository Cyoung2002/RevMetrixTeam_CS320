<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="edu.ycp.cs320.lab02.model.Game, edu.ycp.cs320.lab02.model.Frame, edu.ycp.cs320.lab02.model.ShotObject" %> 
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

    <h1>Game Information</h1>
    <p>Game Number: <%= game.getGameNumber() %></p>
    <p>Total Score: <%= game.getScore() %></p>

    <h2>Frames</h2>
    <table border="1">
        <tr>
            <th>Frame Number</th>
            <th>Lane</th>
            <th>Shots</th>
            <th>Frame Score</th>
        </tr>

        <%
            for (Frame frame : frames) {
                if (frame.getShotNum() > 1) { // Ensure frame has at least one shot
        %>
                <tr>
                    <td><%= frame.getFrameNum() %></td>
                    <td><%= frame.getLaneNum() %></td>
                    <td>
                        <%
                            for (int i = 1; i <= frame.getShotNum(); i++) {
                                ShotObject shot = frame.getShot(i);
                                if (shot != null) {
                        %>
                            Shot <%= i %>: Pins Knocked Down: <%= shot.getPinsKnockedDown() %> | Special Mark: <%= shot.getSpecialMark() != null ? shot.getSpecialMark() : "None" %><br>
                        <%
                                }
                            }
                        %>
                    </td>
                    <td><%= frame.getPinScore() %></td>
                </tr>
        <%
                }
            }
        %>
    </table>

    <form action="game" method="post">
        <button type="submit" name="newFrame" class="button">Add New Frame</button>
    </form>

	<!-- Shot entry button -->
    
    <button id="shotButton" onclick="location.href= 'http://localhost:8081/lab02/shot' ">Shot Entry</button>
    <!--a href="shot">Go to Shot Entry</a-->
    <!-- Index button -->
    <br>
    <button id="indexButton" onclick="location.href= 'http://localhost:8081/lab02/index' ">Index</button>

</body>
</html>
