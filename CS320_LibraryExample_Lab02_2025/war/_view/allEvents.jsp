<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>CS320 All Events</title>
		<style type="text/css">
			.error {
				color: red;
				font-weight: bold;				
			}
			
			td.label {
				text-align: right;
			}
			
			td.book {
				text-align: center;
				color: blue;
				font-weight: bold;
			}
			
			td.bookColHeading {
				text-align: center;
				font-weight: bold;
				max-width: 500px;
			}
			
			td.isbnColHeading {
				text-align: center;
				font-weight: bold;
				max-width: 200px;
				padding-left: 20px;
			}
			
			td.publishedColHeading {
				text-align: center;
				font-weight: bold;
				max-width: 100px;
				padding-left: 20px;
			}

			td.nameColHeading {
				text-align: center;
				font-weight: bold;
				max-width: 300px;
				padding-left: 20px;
			}
			
			tr.bookRow {
				text-align: left;
				color: blue;
				font-weight: bold;
			}
			
			td.bookCol {
				text-align: left;
				color: blue;
				font-weight: bold;
				max-width: 500px;
				padding-left: 20px;				
			}
			
			td.isbnCol {
				text-align: left;
				color: blue;
				font-weight: bold;
				max-width: 200px;
				padding-left: 20px;
			}
			
			td.publishedCol {
				text-align: left;
				color: blue;
				font-weight: bold;
				max-width: 100px;
				padding-left: 40px;
			}
						
			td.nameCol {
				text-align: left;
				color: blue;
				font-weight: bold;
				max-width: 400px;
				padding-left: 20px;				
			}						
		</style>
	</head>

	<body>
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
	
		<form action="${pageContext.servletContext.contextPath}/index" method="post">
			<table>
			    <tr>
					<td class="longnameColHeading">Longname</td>
       				<td class="ShortnameColHeading">Shortname</td>
       				<td class="EstablishmentColHeading">Establishment</td>
					<td class="WeeknightColHeading">Weeknight</td>
       				<td class="StartColHeading">Start</td>
       				<td class="EndColHeading">End</td>
       				<td class="GamesPerSessionColHeading">Games Per Session</td>
			    </tr>
			        
			    <c:forEach items="${events}" var="event">
			        <tr class="eventRow">
			            <td class="eventLongnameCol">${event.longname}</td>
			            <td class="eventShortnameCol">${event.shortname}</td>
			            <td class="establishmentCol">${event.establishmentShort}</td>
			            <td class="weeknightCol">${event.weeknight}</td>
			            <td class="startCol">${event.start}</td>
			            <td class="endCol">${event.end}</td>	
			            <td class="gamesPerSessionCol">${event.gamesPerSession}</td>		            
			        </tr>
			    </c:forEach>
			</table>

			<input type="Submit" name="submithome" value="Home">
		</form>
	</body>
</html>