<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Sessions</title>
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <script>
    function updateForm() {
        let action = document.getElementById("action").value;
        let newSessionFields = document.getElementById("newSessionFields");
        let existingSessionDropdownDelete = document.getElementById("existingSessionDropdownDelete");

        if (action === "addNew") {
            newSessionFields.style.display = "block";
            existingSessionDropdownDelete.style.display = "none";
        } else if (action === "delete") {
            newSessionFields.style.display = "none";
            existingSessionDropdownDelete.style.display = "block";
        }
    }
        window.onload = function() {
            updateForm(); // Set correct form state on page load
        };

    </script>
</head>
<body>
    <h2>Manage Your Sessions</h2>

    <!-- Action Selection -->
    <label for="action">Choose an action:</label>
    
    <select id="action" name="actionSelect" onchange="updateForm()">
        <option value="addNew" ${param.actionSelect == 'addNew' ? 'selected' : ''}>Add New Session</option>
        <option value="delete" ${param.actionSelect == 'delete' ? 'selected' : ''}>Delete Existing Session</option>
    </select>

    <br><br>

    <!-- Form -->
    <form action="${pageContext.servletContext.contextPath}/session" method="post">

		<!-- Add New Establishment Fields -->
        <div id="newSessionFields">
        
        	<c:forEach var="session" items="${sessions}">
        		<label>${establishment.name} - ${establishment.location} - ${establishment.phoneNumber} - ${establishment.hours}<br></label>
        	</c:forEach>
        	<br>
        	
            <label>Name:</label>
            <input type="text" name="name">
            <label>Location:</label>
            <input type="text" name="location">
            <label>Phone Number:</label>
            <input type="text" name="phoneNumber">
            <label>Hours:</label>
            <input type="text" name="hours">
            
            <br><br>
        	<button type="submit" name="action" value="addNew">Submit</button>
        </div>


        <!-- Existing Establishment Drop-down for Deleting-->
        <div id="existingEstablishmentDropdownDelete">
            <label for="selectedEstablishmentDelete">Select a Establishment:</label>
            <select name="selectedEstablishmentDelete" id="selectedEstablishmentDelete">
                <c:forEach var="establishment" items="${establishments}">
                    <option value="${establishment.name},${establishment.location},${establishment.phoneNumber},${establishment.hours}">
                        ${establishment.name} - ${establishment.location} - ${establishment.phoneNumber} - ${establishment.hours}
                    </option>
                </c:forEach>
            </select>
            <br><br>
        	<button type="submit" name="action" value="delete">Submit</button>
        </div>
        
    </form>
    <!-- Index button -->
    <button id="indexButton" onclick="location.href= 'http://localhost:8081/lab02/index' ">Index</button>

</body>
</html>
