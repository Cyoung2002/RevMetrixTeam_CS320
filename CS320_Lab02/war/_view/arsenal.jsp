<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Bowling Ball Arsenal</title>
    <script>
        function updateForm() {
            let action = document.getElementById("action").value;
            let newBallFields = document.getElementById("newBallFields");
            let existingBallDropdownDupe = document.getElementById("existingBallDropdownDupe");
            let existingBallDropdownDelete = document.getElementById("existingBallDropdownDelete");

            // Update the hidden input value
            //hiddenAction.value = action;

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
        <option value="addNew">Add New Ball</option>
        <option value="addDuplicate">Duplicate Existing Ball</option>
        <option value="delete">Delete Existing Ball</option>
    </select>

    <br><br>

    <!-- Form -->
    <form action="${pageContext.servletContext.contextPath}/arsenal" method="post">

        <!-- Existing Ball Drop-down -->
        <div id="existingBallDropdownDupe">
            <label for="selectedBall">Select a Ball:</label>
            <select name="selectedBall" id="selectedBall">
                <c:forEach var="ball" items="${balls}">
                    <option value="${ball.name},${ball.color},${ball.weight}">
                        ${ball.name} - ${ball.color} - ${ball.weight} lbs
                    </option>
                </c:forEach>
            </select>
            <br>
        	<button type="submit" name="action" value="addDuplicate">Submit</button>
        </div>
        
        <!-- Existing Ball Drop-down -->
        <div id="existingBallDropdownDelete">
            <label for="selectedBall">Select a Ball:</label>
            <select name="selectedBall" id="selectedBall">
                <c:forEach var="ball" items="${balls}">
                    <option value="${ball.name},${ball.color},${ball.weight}">
                        ${ball.name} - ${ball.color} - ${ball.weight} lbs
                    </option>
                </c:forEach>
            </select>
            <br>
        	<button type="submit" name="action" value="delete">Submit</button>
        </div>

        <!-- Add New Ball Fields -->
        <div id="newBallFields">
            <label>Name:</label>
            <input type="text" name="name">
            <label>Color:</label>
            <input type="text" name="color">
            <label>Weight:</label>
            <input type="number" name="weight" step="0.1">
            
            <br>
        	<button type="submit" name="action" value="addNew">Submit</button>
        	<!--button type="submit" name="action">Submit</button-->
        	<!--button type="submit">Submit</button-->
        </div>

        <!--br-->
        <!--button type="submit">Submit</button-->
        <!--button type="submit" name="action" value="addNew">Submit</button-->
        <!--button type="submit" name="action">Submit</button-->
        <!--button type="submit">Submit</button-->
    </form>
</body>
</html>
