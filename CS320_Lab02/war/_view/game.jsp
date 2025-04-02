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
            font-family: 'Orbitron', sans-serif;
            background-color: #0a0a2a;
            color: #00ffcc;
            margin: 0;
            padding: 0;
            text-align: center;
        }

        .wrapper {
            width: 80%;
            max-width: 1000px;
            margin: 30px auto;
            padding: 20px;
            background: #1a0033;
            border-left: 10px solid #ff6600;
            border-right: 10px solid #ff6600;
            box-shadow: 0 0 15px rgba(255, 102, 0, 0.8);
            border-radius: 10px;
        }

        h1, h2 {
            text-align: center;
            color: #ff00ff;
            text-shadow: 2px 2px 10px #ff6600;
        }

        table {
            margin: auto;
            border-collapse: collapse;
            width: 90%;
            background: #220066;
            color: #00ffcc;
            box-shadow: 0 0 10px #ff6600;
            border-radius: 5px;
        }

        th, td {
            border: 1px solid #00ffcc;
            padding: 10px;
            text-align: center;
        }

        .button {
            padding: 12px 20px;
            margin: 10px;
            background: #ff6600;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            transition: background 0.3s ease, box-shadow 0.3s ease;
            text-shadow: 1px 1px 5px #000;
            box-shadow: 0 0 10px #ff6600;
            font-size: 16px;
            font-family: 'Orbitron', sans-serif;
        }

        .button:hover {
            background: #ff3300;
            box-shadow: 0 0 15px #ff00ff;
        }
    </style>
</head>
<body>
    <div class="wrapper">
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
        <table>
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
        <button class="button" onclick="location.href='http://localhost:8081/lab02/shot'">Shot Entry</button>

        <!-- Index button -->
        <br>
        <button class="button" onclick="location.href='http://localhost:8081/lab02/index'">Index</button>
    </div>
</body>
</html>
