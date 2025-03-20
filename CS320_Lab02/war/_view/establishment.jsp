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
            let existingEstablishmentDropdown = document.getElementById("existingEstablishmentDropdown");

            if (action === "addNew") {
                newEstablishmentFields.style.display = "block";
                existingEstablishmentDropdown.style.display = "none";
            } else {
                newEstablishmentFields.style.display = "none";
                existingEstablishmentDropdown.style.display = "block";
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
    <select id="action" name="action" onchange="updateForm()">
        <option value="addNew">Add New Establishment</option>
        <option value="delete">Delete Existing Establishment</option>
    </select>

    <br><br>

    <!-- Form -->
    <form action="${pageContext.servletContext.contextPath}/establishment" method="post">

        <!-- Existing Establishment Drop-down -->
        <div id="existingEstablishmentDropdown">
            <label for="selectedEstablishment">Select a Establishment:</label>
            <select name="selectedEstablishment" id="selectedEstablishment">
                <c:forEach var="Establishment" items="${establishments}">
                    <option value="${establishment.name},${establishment.location},${establishment.phoneNumber},${establishment.hours}">
                        ${establishment.name} - ${establishment.location} - ${establishment.phoneNumber} - ${establishment.hours}
                    </option>
                </c:forEach>
            </select>
        </div>

         <!-- Add New Establishment Fields -->
        <div id="newEstablishmentFields">
            <label>Name:</label>
            <input type="text" name="name">
            <label>Location:</label>
            <input type="text" name="location">
            <label>Phone Number:</label>
            <input type="text" name="phoneNumber">
            <label>Hours:</label>
            <input type="text" name="hours">
        </div>

        <br>
        <button type="submit">Submit</button>
    </form>
    
    <button id="indexButton" onclick="location.href= 'http://localhost:8081/lab02/index' ">Index</button>
</body>
</html>