<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Bowling Ball Arsenal</title>
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
    body {
    font-family: Arial, sans-serif;
    background-color: #0a0a2a;
    color: #00ffcc;
    margin: 0;
    padding: 0;
}

.container {
    width: 80%;
    margin: 20px auto;
    background: #1a0033;
    padding: 20px;
    box-shadow: 0 0 15px #ff6600;
    border-radius: 8px;
}

h1 {
    text-align: center;
    color: #ff00ff;
    text-shadow: 2px 2px 10px #ff6600;
}

.ball-list {
    list-style: none;
    padding: 0;
}

.ball-item {
    background: #220066;
    color: #00ffcc;
    padding: 10px;
    margin: 10px 0;
    border-radius: 5px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    box-shadow: 0 0 10px #ff6600;
}

.ball-item span {
    font-weight: bold;
    color: #ff00ff;
}

.form-container {
    margin-top: 20px;
    padding: 15px;
    background: #330066;
    border-radius: 5px;
    box-shadow: 0 0 10px #00ffcc;
}

label {
    font-weight: bold;
    color: #ff00ff;
}

input, select {
    padding: 8px;
    margin: 5px 0;
    width: 100%;
    border: 1px solid #00ffcc;
    border-radius: 5px;
    background: #220066;
    color: #00ffcc;
}

button {
    background: #ff6600;
    color: white;
    border: none;
    padding: 10px 15px;
    border-radius: 5px;
    cursor: pointer;
    transition: background 0.3s ease, box-shadow 0.3s ease;
    text-shadow: 1px 1px 5px #000;
    box-shadow: 0 0 10px #ff6600;
}

button:hover {
    background: #ff3300;
    box-shadow: 0 0 15px #ff00ff;
}
    
    
    </style>
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
    <form action="${pageContext.servletContext.contextPath}/arsenal" method="post">

		<!-- Add New Ball Fields -->
        <div id="newBallFields">
        
        	<c:forEach var="ball" items="${balls}">
        		<label>${ball.name} - ${ball.color} - ${ball.weight} lbs<br></label>
        	</c:forEach>
        	<br>
        	
            <label>Name:</label>
            <input type="text" name="name">
            <label>Color:</label>
            <input type="text" name="color">
            <label>Weight:</label>
            <input type="number" name="weight" step="0.1">
            
            <br><br>
        	<button type="submit" name="action" value="addNew">Submit</button>
        </div>

        <!-- Existing Ball Drop-down for Duplicating-->
        <div id="existingBallDropdownDupe">
            <label for="selectedBallDupe">Select a Ball:</label>
            <select name="selectedBallDupe" id="selectedBallDupe">
                <c:forEach var="ball" items="${balls}">
                    <option value="${ball.name},${ball.color},${ball.weight}">
                        ${ball.name} - ${ball.color} - ${ball.weight} lbs
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
                    <option value="${ball.name},${ball.color},${ball.weight}">
                        ${ball.name} - ${ball.color} - ${ball.weight} lbs
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
