<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<html>
	<head>
		<title>Carry Percentages</title>
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
	
	<div class="wrapper">
	
			<c:if test="${requestScope.formSubmitted and not empty requestScope.errorMessage}">
    			<div class="error">${requestScope.errorMessage}</div>
			</c:if>


		<form action="${pageContext.servletContext.contextPath}/carryPercentage" method="post">
			<table>
				<tr>
    				<td class="label">Frame Number:</td>
    				<td>
        				<select name="frameNum">
            				<option value="" <c:if test="${empty frameNum}">selected</c:if>> </option>
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
            			<!-- Blank option -->
            			<option value="" <c:if test="${empty selectedEvent}">selected</c:if>> </option>
            
            			<!-- Actual event options -->
            			<c:forEach items="${events}" var="event">
                			<option value="${event.shortname}" <c:if test="${event.shortname == selectedEvent}">selected</c:if>>
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
            				<option value="Fa-24" <c:if test="${season == 'Fa-24'}">selected</c:if>>Fa-24</option>
            				<option value="Su-25" <c:if test="${season == 'Su-25'}">selected</c:if>>Su-25</option>
            				<option value="" <c:if test="${empty season}">selected</c:if>> </option>
        				</select>
    				</td>
				</tr>
			</table>
			<br>
			<table>
				<tr>
			      <td class="label">Carry Percentage:</td>
			         <td class="resultCol">
    					<fmt:formatNumber value="${percentResult}" type="number" maxFractionDigits="2" />%
					</td>   
			    </tr>
			</table>
			
			<input type="Submit" name="submitStrikePercentage" value="Find Carry Percentage">
		</form>
		<br>
		<form action="${pageContext.servletContext.contextPath}/index" method="post">
			<input type="Submit" name="submithome" value="Home">
		</form>	
		</div>	
	</body>
</html>