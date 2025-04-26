<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>CS320 Arsenal</title>
		<style type="text/css">
			.error {
				color: red;
				font-weight: bold;				
			}
			
			td.label {
				text-align: right;
			}
	
			td.nameColHeading {
				text-align: center;
				font-weight: bold;
				max-width: 200px;
				padding-left: 20px;
			}
			td.brandColHeading {
				text-align: center;
				font-weight: bold;
				max-width: 200px;
				padding-left: 20px;
			}
			
			td.typeColHeading {
				text-align: center;
				font-weight: bold;
				max-width: 200px;
				padding-left: 20px;
			}
			
			tr.bookRow {
				text-align: left;
				color: blue;
				font-weight: bold;
			}

			td.nameCol {
				text-align: left;
				color: blue;
				font-weight: bold;
				max-width: 400px;
				padding-left: 20px;
			}	
			td.brandCol {
				text-align: left;
				color: blue;
				font-weight: bold;
				max-width: 400px;
				padding-left: 20px;
			}		
			td.typeCol {
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
       				<td class="nameColHeading">Long Name</td>
       				<td class="nameColHeading">Short Name</td>       	
					<td class="brandColHeading">Brand</td> 
					<td class="typeColHeading">Type</td> 			
			    </tr>
			        
			    <c:forEach items="${arsenal}" var="ball">
			        <tr class="ballRow">
			            <td class="nameCol">${ball.longname}</td>
			            <td class="nameCol">${ball.shortname}</td>	
						<td class="brandCol">${ball.brand}</td>
						<td class="typeCol">${ball.type}</td>						            
			        </tr>
			    </c:forEach>
			</table>

			<input type="Submit" name="submithome" value="Home">
		</form>
	</body>
</html>