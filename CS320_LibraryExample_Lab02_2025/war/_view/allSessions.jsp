<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>CS320 All Sessions</title>
		<style type="text/css">
       body {
            font-family: 'Orbitron', sans-serif;
            background-color: #0a0a2a;
            color: #00ffcc;
            margin: 0;
            padding: 0;
            text-align: center;
            min-height: 100vh;
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
            color: #ff00ff;
            text-shadow: 1px 1px 5px #ff6600;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: #1a0033;
            border-radius: 10px;
            box-shadow: 0 0 15px #00ffcc;
            margin-top: 20px;
        }

        td.LeagueColHeading,
        td.SeasonColHeading,
        td.WeekColHeading,
        td.DateScheduledColHeading,
        td.RegSubColHeading,
        td.OpponentColHeading,
        td.StartLaneColHeading, 
        td.BallColHeading,
        td.GameOneColHeading,
        td.GameTwoColHeading,
        td.GameThreeColHeading,
        td.SeriesColHeading	{
            text-align: center;
            font-weight: bold;
            color: #ff00ff;
            background: #1a0033;
            padding: 10px;
            border-bottom: 2px solid #ff6600;
            text-shadow: none;
        }

        td.leagueCol,
        td.seasonCol,
        td.weekCol,
        td.dateScheduledCol,
        td.regSubCol,
        td.opponentCol,
        td.startLaneCol, 
        td.ballCol,
        td.gameOneCol,
        td.gameTwoCol,
        td.gameThreeCol,
        td.seriesCol	 {
            text-align: left;
            color: #00ffcc;
            background: #1a0033;
            font-weight: bold;
            padding: 10px 20px;
            border-bottom: 1px solid #00ffcc;
        }

        tr.eventRow {
            transition: background 0.3s ease;
        }

        tr.eventRow:hover {
            background: #220066;
        }

        button {
            background: #ff6600;
            color: white;
            border: none;
            padding: 12px 20px;
            border-radius: 5px;
            cursor: pointer;
            transition: background 0.3s ease, box-shadow 0.3s ease;
            text-shadow: 1px 1px 5px #000;
            box-shadow: 0 0 10px #ff6600;
            font-size: 16px;
            margin: 20px 10px 0;
        }

        button:hover {
            background: #ff3300;
            box-shadow: 0 0 15px #ff00ff;
        }

        .error {
            color: red;
            font-weight: bold;
            margin-bottom: 20px;
        }

				
</style>
</head>

<body>

    <div class="wrapper">

        <!-- Error message (show only if needed) -->
        <c:if test="${! empty errorMessage}">
            <div class="error">${errorMessage}</div>
        </c:if>

        <h1>Sessions</h1>

        <form action="${pageContext.servletContext.contextPath}/index" method="post">
            <table>
                <tr>
                    <td class="LeagueColHeading">League type</td>
                    <td class="SeasonColHeading">Season</td>
                    <td class="WeekColHeading">Week Number</td>
                    <td class="DateScheduledColHeading">Date Scheduled</td>
                    <td class="RegSubColHeading">Reg/Sub</td>
                    <td class="OpponentColHeading">Opponent</td>
                    <td class="StartLaneColHeading">Starting Lane</td>
                    <td class="BallColHeading">Ball(s)</td>
                    <td class="GameOneColHeading">Game One Score</td>
                    <td class="GameTwoColHeading">Game Two Score</td>
                    <td class="GameThreeColHeading">Game Three Score</td>
                    <td class="SeriesColHeading">Series</td>
                </tr>

                <c:forEach items="${sessions}" var="session">
                    <tr class="sessionRow">
                        <td class="leagueCol">${session.league}</td>
                        <td class="seasonCol">${event.season}</td>
                        <td class="weekCol">${event.week}</td>
                        <td class="dateScheduledCol">${event.dateScheduled}</td>
                        <td class="regSubCol">${event.regSub}</td>
                        <td class="opponentCol">${event.opponent}</td>
                        <td class="startLaneCol">${event.startLane}</td>
                        <td class="ballCol">${event.ball}</td>
                        <td class="gameOneCol">${event.gameOne}</td>
                        <td class="gameTwoCol">${event.gameTwo}</td>
                        <td class="gameThreeCol">${event.gameThree}</td>
                        <td class="seriesCol">${event.series}</td>
                    </tr>
                </c:forEach>
            </table>
        </form>
        <div class="button-container">
            <form action="${pageContext.servletContext.contextPath}/index" method="post">
                <button type="submit" name="submithome">Home</button>
            </form>
        	<br>
			<form action="${pageContext.servletContext.contextPath}/insertSession" method="get">
                <button type="submit" name="submitinsertnewsession">Add New Session</button>
            </form>
        </div>
    </div>
</body>
</html>
