<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>CS320 Add Book</title>
		<style type="text/css">
			.error {
				color: red;
				font-weight: bold;
			}
			
			.success {
				color: blue;
				font-weight: bold;
			}
			
			.success_title {
				color: darkblue;
				font-style: italic;
				font-weight: bold;			
			}
			
			tr.label {
				text-align: right;
			}
		</style>
	</head>

	<body>
		<form action="${pageContext.servletContext.contextPath}/strikePercentageGame" method="post">
			<table>
				<tr>
					<td class="label">Game ID:</td>
					<td><input type="text" name="gameID" size="20" value="${gameID}" /></td>
				</tr>						
			</table>
			<br>
			<table>
				<tr>
			      <td class="label">Strike Percentage:</td>
			         <td class="resultCol">${percentResult}</td>		            
			    </tr>
			</table>
			
			<input type="Submit" name="submitStrikePercentageGame" value="Find Strike Percentage for Game Input">
		</form>
		<br>
		<form action="${pageContext.servletContext.contextPath}/index" method="post">
			<input type="Submit" name="submithome" value="Home">
		</form>		
	</body>
</html>