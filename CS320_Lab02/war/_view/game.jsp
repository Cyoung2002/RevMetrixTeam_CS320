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
        table { 
        	width: 100%; 
        	border-collapse: collapse; 
        	text-align: center; 
        }
        .frame { 
        	width: 10%; 
        }
    </style>
</head>

<body>
	<%
        Game game = (Game) session.getAttribute("game");
        if (game == null) {
            game = new Game(1, 1);
            session.setAttribute("game", game);
        }
        // ArrayList<Frame> frames = game.getFrames();
    %>
	<h1>Game Information</h1>
    <p>Game Number: <%= game.getGameNumber() %></p>
    <p>Total Score: <%= game.getScore() %></p>
    
    <h2>Bowling Game Scoreboard</h2>

    <table>
        <tr>
            <%-- Frame Numbers --%>
            <th class="frame">Frame 1</th>
            <th class="frame">Frame 2</th>
            <th class="frame">Frame 3</th>
            <th class="frame">Frame 4</th>
            <th class="frame">Frame 5</th>
            <th class="frame">Frame 6</th>
            <th class="frame">Frame 7</th>
            <th class="frame">Frame 8</th>
            <th class="frame">Frame 9</th>
            <th class="frame">Frame 10</th>
        </tr>
        <tr>
            <%-- Frame Shots --%>
            <%
                ArrayList<Frame> frames = game.getFrames();

                for (int i = 0; i < 10; i++) {
                    if (i < frames.size()) {
                        Frame frame = frames.get(i);
                        ArrayList<ShotObject> shots = frame.getShots();
                    	if(!(shots == null) && shots.size() >= 1) {   
            %>		
	                        <td>
	                           	<%= shots.get(0).getPinsKnockedDown().size() %> <!-- Pins knocked down -->

	                        </td>
            <%
                    	} else {
            %>
                        	<td> - </td> <!-- Empty Frame -->
            <%
                    	}
                    } else {   
              		%>
                    	<td> - </td> <!-- Empty Frame -->
        			<%
                    }
                }
            %>
        </tr>
    </table>
    
	<!-- Shot entry button -->
    <button id="shotButton" onclick="location.href= 'http://localhost:8081/lab02/shot' ">Shot Entry</button>
    <!--a href="shot">Go to Shot Entry</a-->
    <!-- Index button -->
    <br>
    <button id="indexButton" onclick="location.href= 'http://localhost:8081/lab02/index' ">Index</button>

</body>
</html>
