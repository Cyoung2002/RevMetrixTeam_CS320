<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>CS320 Add Ball</title>
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
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
		
		<c:if test="${! empty successMessage}">
			<div class="success">Successfully added <span class="success_title">${successMessage}</span> to Library</div>
		</c:if>
			
		<form action="${pageContext.servletContext.contextPath}/insertBall" method="post">
			<table>
				<tr>
					<td class="label">Long Name:</td>
					<td><input type="text" name="longname" size="20" value="${longname}" /></td>
				</tr>
				<tr>
					<td class="label">Short Name:</td>
					<td><input type="text" name="shortname" size="20" value="${shortname}" /></td>
				</tr>
				<tr>
					<td class="label">Brand:</td>
					<td><input type="text" name="brand" size="50" value="${brand}" /></td>
				</tr>
				<tr>
					<td class="label">Type:</td>
					<td><input type="text" name="type" size="20" value="${type}" /></td>
				</tr>
				<tr>
					<td class="label">Core:</td>
					<td><input type="text" name="core" size="20" value="${core}" /></td>
				</tr>	
				<tr>
					<td class="label">Cover:</td>
					<td><input type="text" name="cover" size="20" value="${cover}" /></td>
				</tr>
				<tr>
					<td class="label">Color:</td>
					<td><input type="text" name="color" size="20" value="${color}" /></td>
				</tr>	
				<tr>
					<td class="label">Surface:</td>
					<td><input type="text" name="surface" size="20" value="${surface}" /></td>
				</tr>				
				<tr>
					<td class="label">Year:</td>
					<td><input type="text" name="year" size="20" value="${year}" /></td>
				</tr>			
				<tr>
					<td class="label">Serial Number:</td>
					<td><input type="text" name="serialNumber" size="20" value="${serialNumber}" /></td>
				</tr>	
				<tr>
					<td class="label">Weight:</td>
					<td><input type="text" name="weight" size="20" value="${weight}" /></td>
				</tr>	
				<tr>
					<td class="label">Mapping:</td>
					<td><input type="text" name="mapping" size="20" value="${mapping}" /></td>
				</tr>	
			</table>
			
			<input type="Submit" name="submitinsertball" value="Add Ball to Arsenal">
		</form>
		<br>
		<form action="${pageContext.servletContext.contextPath}/index" method="post">
			<input type="Submit" name="submithome" value="Home">
		</form>		
	</body>
</html>