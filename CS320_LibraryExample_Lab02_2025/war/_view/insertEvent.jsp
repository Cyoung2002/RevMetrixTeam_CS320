<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>CS320 Add Event</title>
		<style type="text/css">
			.error {
				color: red;
				font-weight: bold;
			}
			
			.success {
				color: blue;
				font-weight: bold;
			}
			
			.success_longName {
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