<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Game</title>
    <script>
        function updateForm() {
            let action = document.getElementById("action").value;
            let newBallFields = document.getElementById("newBallFields");
            let existingBallDropdownDupe = document.getElementById("existingBallDropdownDupe");
            let existingBallDropdownDelete = document.getElementById("existingBallDropdownDelete");

            if (action === "addNew") {
                newBallFields.style.display = "block";
                existingBallDropdownDupe.style.display = "none";
                existingBallDropdownDelete.style.display = "none";
            } else if (action === "addDuplicate") {
                newBallFields.style.display = "none";
                existingBallDropdownDupe.style.display = "block";
                existingBallDropdownDelete.style.display = "none";
            } else {
            	newBallFields.style.display = "none";
                existingBallDropdownDupe.style.display = "none";
                existingBallDropdownDelete.style.display = "block";
            }
        }

        window.onload = function() {
            updateForm(); // Set correct form state on page load
        };

    </script>
</head>
<body>
    <h2>Manage Your Bowling Ball Arsenal</h2>

    <!-- Action Selection -->
    <label for="action">Choose an action:</label>
    
    <select id="action" name="actionSelect" onchange="updateForm()">
        <option value="addNew" ${param.actionSelect == 'addNew' ? 'selected' : ''}>Add New Ball</option>
        <option value="addDuplicate" ${param.actionSelect == 'addDuplicate' ? 'selected' : ''}>Duplicate Existing Ball</option>
        <option value="delete" ${param.actionSelect == 'delete' ? 'selected' : ''}>Delete Existing Ball</option>
    </select>

    <br><br>

    <!-- Form -->
    <form action="${pageContext.servletContext.contextPath}/game" method="post">

		<!-- Add New Ball Fields -->
        <div id="newBallFields">
        
        	<c:forEach var="ball" items="${balls}">
        		<label>${ball.brand} - ${ball.name} - ${ball.color} - ${ball.core} - ${ball.weight} lbs - ${ball.diameter} in<br></label>
        	</c:forEach>
        	<br>
        	
        	<label>Brand:</label>
            <input type="text" name="brand">
            <label>Name:</label>
            <input type="text" name="name">
            <label>Color:</label>
            <input type="text" name="color">
            <label>Core:</label>
            <input type="text" name="core">
            <label>Weight:</label>
            <input type="number" name="weight" step="0.1">
            <label>Diameter:</label>
            <input type="number" name="diameter" step="0.1">
            
            <br><br>
        	<button type="submit" name="action" value="addNew">Submit</button>
        </div>

        <!-- Existing Ball Drop-down for Duplicating-->
        <div id="existingBallDropdownDupe">
            <label for="selectedBallDupe">Select a Ball:</label>
            <select name="selectedBallDupe" id="selectedBallDupe">
                <c:forEach var="ball" items="${balls}">
                    <option value="${ball.brand},${ball.name},${ball.color},${ball.core},${ball.weight},${ball.diameter}">
                        ${ball.brand} - ${ball.name} - ${ball.color} - ${ball.core} - ${ball.weight} lbs - ${ball.diameter} in
                    </option>
                </c:forEach>
            </select>
            <br><br>
            
            <label>Add a nickname to your duplicate ball:</label>
            <input type="text" name="nickname">
            
            <br><br>
        	<button type="submit" name="action" value="addDuplicate">Submit</button>
        </div>
        
        <!-- Existing Ball Drop-down for Deleting-->
        <div id="existingBallDropdownDelete">
            <label for="selectedBallDelete">Select a Ball:</label>
            <select name="selectedBallDelete" id="selectedBallDelete">
                <c:forEach var="ball" items="${balls}">
                    <option value="${ball.brand},${ball.name},${ball.color},${ball.core},${ball.weight},${ball.diameter}">
                        ${ball.brand} - ${ball.name} - ${ball.color} - ${ball.core} - ${ball.weight} lbs - ${ball.diameter} in
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
