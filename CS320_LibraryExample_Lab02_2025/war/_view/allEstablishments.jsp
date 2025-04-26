<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>All Establishments</title>
		<style type="text/css">
			.error {
				color: red;
				font-weight: bold;				
			}
			
			td.label {
				text-align: right;
			}
			
			td.establishment {
				text-align: center;
				color: blue;
				font-weight: bold;
			}
			
			td.longNameColHeading {
				text-align: center;
				font-weight: bold;
				max-width: 500px;
			}
			
			td.shortNameColHeading {
				text-align: center;
				font-weight: bold;
				max-width: 200px;
				padding-left: 20px;
			}
			
			td.addressColHeading {
				text-align: center;
				font-weight: bold;
				max-width: 100px;
				padding-left: 20px;
			}
			
			tr.establishmentRow {
				text-align: left;
				color: blue;
				font-weight: bold;
			}
			
			td.longNameCol {
				text-align: left;
				color: blue;
				font-weight: bold;
				max-width: 500px;
				padding-left: 20px;				
			}
			
			td.shortNameCol {
				text-align: left;
				color: blue;
				font-weight: bold;
				max-width: 200px;
				padding-left: 20px;
			}
			
			td.addressCol {
				text-align: left;
				color: blue;
				font-weight: bold;
				max-width: 100px;
				padding-left: 40px;
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
					<td class="longNameColHeading">Long Name</td>
       				<td class="shortNameColHeading">Short Name</td>
       				<td class="addressColHeading">Address</td>
			    </tr>
			        
			    <c:forEach items="${establishments}" var="establishment">
			        <tr class="establishmentRow">
			            <td class="longNameCol">${establishment.longname}</td>
			            <td class="shortNameCol">${establishment.shortname}</td>
			            <td class="addressCol">${establishment.address}</td>	            
			        </tr>
			    </c:forEach>
			</table>

			<input type="Submit" name="submithome" value="Home">
		</form>
		<form action="${pageContext.servletContext.contextPath}/insertEstablishment" method="post">
			<input type="Submit" name="submitinsertnewestablishment" value="Add New Establishment To Library">
		</form>
	</body>
</html>