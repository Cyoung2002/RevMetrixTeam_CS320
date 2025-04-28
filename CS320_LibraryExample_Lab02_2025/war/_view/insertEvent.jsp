<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>CS320 Add Event</title>

    <style>
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
            width: 70%;
            max-width: 800px;
            margin: 30px auto;
            padding: 30px;
            background: #1a0033;
            border-left: 10px solid #ff6600;
            border-right: 10px solid #ff6600;
            box-shadow: 0 0 15px rgba(255, 102, 0, 0.8);
            border-radius: 12px;
        }

        h1 {
            color: #ff00ff;
            text-shadow: 1px 1px 5px #ff6600;
            margin-bottom: 30px;
        }

        table {
            width: 100%;
            margin: 0 auto 30px;
        }

        td.label {
            text-align: right;
            font-weight: bold;
            color: #ff00ff;
            padding-right: 20px;
            width: 40%;
            font-size: 18px;
        }

        td input[type="text"] {
            width: 90%;
            padding: 8px;
            border: 2px solid #ff00ff;
            border-radius: 5px;
            background-color: #0a0a2a;
            color: #00ffcc;
            font-size: 16px;
            transition: all 0.3s ease;
        }

        td input[type="text"]:focus {
            border-color: #00ffcc;
            outline: none;
            box-shadow: 0 0 8px #00ffcc;
        }

        .error {
            color: red;
            font-weight: bold;
            margin-bottom: 20px;
        }

        .success {
            color: #00ffcc;
            font-weight: bold;
            margin-bottom: 20px;
        }

        .success_longName {
            color: #ff00ff;
            font-style: italic;
            font-weight: bold;
        }

        input[type="submit"], button {
            background: #ff6600;
            color: white;
            border: none;
            padding: 12px 25px;
            margin-top: 15px;
            margin-right: 10px;
            border-radius: 8px;
            font-size: 16px;
            cursor: pointer;
            transition: background 0.3s, box-shadow 0.3s;
            box-shadow: 0 0 10px #ff6600;
        }

        input[type="submit"]:hover, button:hover {
            background: #ff3300;
            box-shadow: 0 0 15px #ff00ff;
        }
    </style>
</head>

<body>
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
		
		<c:if test="${! empty successMessage}">
			<div class="success">Successfully added <span class="success_longname">${successMessage}</span> to Table</div>
		</c:if>
			
		<form action="${pageContext.servletContext.contextPath}/insertEvent" method="post">
			<table>
				<tr>
					<td class="label">Long Name:</td>
					<td><input type="text" name="event_longname" size="20" value="${event_longname}" /></td>
				</tr>
				<tr>
					<td class="label">Short Name:</td>
					<td><input type="text" name="event_shortname" size="20" value="${event_shortname}" /></td>
				</tr>
				<tr>
					<td class="label">Establishment:</td>
					<td><input type="text" name="event_establishmentShort" size="20" value="${event_establishmentShort}" /></td>
				</tr>	
				<tr>
					<td class="label">Weeknight:</td>
					<td><input type="text" name="event_weeknight" size="20" value="${event_weeknight}" /></td>
				</tr>
				<tr>
					<td class="label">Start:</td>
					<td><input type="text" name="event_start" size="20" value="${event_start}" /></td>
				</tr>
				<tr>
					<td class="label">End:</td>
					<td><input type="text" name="event_end" size="20" value="${event_end}" /></td>
				</tr>
				<tr>
					<td class="label">Games Per Session:</td>
					<td><input type="number" name="event_gamesPerSession" size="20" value="${event_gamesPerSession}" /></td>
				</tr>										
			</table>
			
			<input type="Submit" name="submitinsertestablishment" value="Add Event to Library">
		</form>
		<br>
		<form action="${pageContext.servletContext.contextPath}/index" method="post">
			<input type="Submit" name="submithome" value="Home">
		</form>		
	</body>
</html>
