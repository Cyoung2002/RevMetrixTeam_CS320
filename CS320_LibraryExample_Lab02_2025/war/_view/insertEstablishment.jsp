<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>CS320 Add Establishment</title>
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
			<div class="success">Successfully added <span class="success_longName">${successMessage}</span> to Table</div>
		</c:if>
			
		<form action="${pageContext.servletContext.contextPath}/insertEstablishment" method="post">
			<table>
				<tr>
					<td class="label">Long Name:</td>
					<td><input type="text" name="establishment_longName" size="20" value="${establishment_longName}" /></td>
				</tr>
				<tr>
					<td class="label">Short Name:</td>
					<td><input type="text" name="establishment_shortName" size="20" value="${establishment_shortName}" /></td>
				</tr>
				<tr>
					<td class="label">Address:</td>
					<td><input type="text" name="establishment_address" size="50" value="${establishment_address}" /></td>
				</tr>							
			</table>
			
			<input type="Submit" name="submitinsertestablishment" value="Add Establishment to Library">
		</form>
		<br>
		<form action="${pageContext.servletContext.contextPath}/index" method="post">
			<input type="Submit" name="submithome" value="Home">
		</form>	
		<form action="${pageContext.servletContext.contextPath}/allEstablishments" method="post">
			<input type="Submit" name="submitallestablishments" value="View Establishments">
		</form>	
	</body>
</html>