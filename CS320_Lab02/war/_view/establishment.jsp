<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Establishments</title>
    <script>
        function updateForm() {
            let action = document.getElementById("action").value;
            let newEstablishmentFields = document.getElementById("newEstablishmentFields");
            let existingEstablishmentDropdownDupe = document.getElementById("existingEstablishmentDropdownDupe");
            let existingEstablishmentDropdownDelete = document.getElementById("existingEstablishmentDropdownDelete");

            if (action === "addNew") {
                newEstablishmentFields.style.display = "block";
                existingEstablishmentDropdownDupe.style.display = "none";
                existingEstablishmentDropdownDelete.style.display = "none";
            } else {
            	newEstablishmentFields.style.display = "none";
                existingEstablishmentDropdownDupe.style.display = "none";
                existingEstablishmentDropdownDelete.style.display = "block";
            }
        }

        window.onload = function() {
            updateForm(); // Set correct form state on page load
        };

    </script>
</head>
<body>
    <h2>Manage Your Establishments</h2>

    <!-- Action Selection -->
    <label for="action">Choose an action:</label>
    
    <select id="action" name="actionSelect" onchange="updateForm()">
        <option value="addNew" ${param.actionSelect == 'addNew' ? 'selected' : ''}>Add New Establishment</option>
        <option value="delete" ${param.actionSelect == 'delete' ? 'selected' : ''}>Delete Existing Establishment</option>
    </select>

    <br><br>

    <!-- Form -->
    <form action="${pageContext.servletContext.contextPath}/arsenal" method="post">

		<!-- Add New Establishment Fields -->
        <div id="newBallFields">
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
    <br>
    <button id="indexButton" onclick="location.href= 'http://localhost:8081/lab02/index' ">Index</button>
</body>
</html>
