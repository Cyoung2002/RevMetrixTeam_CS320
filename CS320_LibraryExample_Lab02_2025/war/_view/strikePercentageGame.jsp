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
	
			<c:if test="${formSubmitted and not empty errorMessage}">
    			<div class="error">${errorMessage}</div>
			</c:if>

		<form action="${pageContext.servletContext.contextPath}/strikePercentageGame" method="post">
			<table>
				<tr>
					<td class="label">Frame Number:</td>
					<td>
    				<select name="frameNum">
        				<c:forEach begin="1" end="12" var="i">
            				<option value="${i}" <c:if test="${frameNum == i}">selected</c:if>>${i}</option>
        				</c:forEach>
    				</select>
					</td>
				</tr>
				<tr>
				    <td class="label">Event:</td>
				    <td>
				        <select name="event">
				            <c:forEach items="${events}" var="event">
				                <option value="${event.shortname}">
				                    ${event.shortname}
				                </option>
				            </c:forEach>
				        </select>
				    </td>
				</tr>
				<tr>
    				<td class="label">Season:</td>
    				<td>
       					<select name="season">
            				<option value="Fa-24">Fa-24</option>
       					</select>
    				</td>
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