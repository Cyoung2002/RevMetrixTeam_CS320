<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Bowling Ball Arsenal</title>
    <script>
        function updateForm() {
            let action = document.getElementById("action").value;
            let newBallFields = document.getElementById("newBallFields");
            let existingBallDropdown = document.getElementById("existingBallDropdown");

            if (action === "addNew") {
                newBallFields.style.display = "block";
                existingBallDropdown.style.display = "none";
            } else {
                newBallFields.style.display = "none";
                existingBallDropdown.style.display = "block";
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
    <select id="action" name="action" onchange="updateForm()">
        <option value="addNew">Add New Ball</option>
        <option value="addDuplicate">Duplicate Existing Ball</option>
        <option value="delete">Delete Existing Ball</option>
    </select>

    <br><br>

    <!-- Form -->
    <form action="/arsenal" method="post">

        <!-- Existing Ball Dropdown -->
        <div id="existingBallDropdown">
            <label for="selectedBall">Select a Ball:</label>
            <select name="selectedBall" id="selectedBall">
                <c:forEach var="ball" items="${balls}">
                    <option value="${ball.name},${ball.color},${ball.weight}">
                        ${ball.name} - ${ball.color} - ${ball.weight} lbs
                    </option>
                </c:forEach>
            </select>
        </div>

        <!-- Add New Ball Fields -->
        <div id="newBallFields">
            <label>Name:</label>
            <input type="text" name="name">
            <label>Color:</label>
            <input type="text" name="color">
            <label>Weight:</label>
            <input type="number" name="weight" step="0.1">
        </div>

        <br>
        <button type="submit">Submit</button>
    </form>
</body>
</html>
