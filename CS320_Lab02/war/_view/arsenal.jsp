<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Bowling Ball Arsenal</title>
    <link href="https://fonts.googleapis.com/css2?family=Orbitron:wght@400;700&display=swap" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
body {
    font-family: 'Orbitron', sans-serif;
    background-color: #0a0a2a; /* Background for side margins */
    color: #00ffcc;
    margin: 0;
    padding: 0;
    text-align: center;
}

/* Wrapper to center content and add side margins */
.wrapper {
    width: 80%;
    max-width: 1000px; /* Keeps content from getting too wide */
    margin: 30px auto;
    padding: 20px;
    background: #1a0033; /* Main content background */
    border-left: 10px solid #ff6600;  /* Left border */
    border-right: 10px solid #ff6600; /* Right border */
    box-shadow: 0 0 15px rgba(255, 102, 0, 0.8); /* Glowing effect */
    border-radius: 10px;
}

/* Main title */
h1, h2 {
    text-align: center;
    color: #ff00ff;
    text-shadow: 2px 2px 10px #ff6600;
}

/* Ball list styling */
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

/* Form section */
.form-container {
    width: 60%;
    max-width: 500px;
    margin: 20px auto;
    padding: 15px;
    background: #330066;
    border-radius: 5px;
    box-shadow: 0 0 10px #00ffcc;
    text-align: left;
}

/* Labels and inputs */
label {
    font-weight: bold;
    color: #ff00ff;
    display: block;
    margin: 10px 0 5px;
}

input, select {
    padding: 8px;
    margin: 5px 0;
    width: 90%;
    max-width: 400px;
    border: 1px solid #00ffcc;
    border-radius: 5px;
    background: #220066;
    color: #00ffcc;
    font-size: 14px;
}

/* Dropdowns */
select {
    cursor: pointer;
}

/* Buttons */
button {
    background: #ff6600;
    color: white;
    border: none;
    padding: 12px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: background 0.3s ease, box-shadow 0.3s ease;
    text-shadow: 1px 1px 5px #000;
    box-shadow: 0 0 10px #ff6600;
    font-size: 16px;
    margin: 10px;
}

button:hover {
    background: #ff3300;
    box-shadow: 0 0 15px #ff00ff;
}

/* Error messages */
.error {
    color: red;
    font-weight: bold;
}

            #submit {
    font-family: 'Orbitron', sans-serif;
    }
    
                #indexbutton {
    font-family: 'Orbitron', sans-serif;
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
            updateForm();
        };
    </script>
</head>
<body>
    <div class="wrapper"> <!-- Added wrapper div here -->
    <h2>Manage Your Bowling Ball Arsenal</h2>

    <!-- Action Selection -->
    <label for="action">Choose an action:</label>
    
    <select id="action" name="actionSelect" onchange="updateForm()" style= "font-family: 'Orbitron', sans-serif;" >
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
        	<button type="submit" name="action" value="addNew" style= "font-family: 'Orbitron', sans-serif;" >Submit</button>
        </div>

        <!-- Existing Ball Drop-down for Duplicating-->
        <div id="existingBallDropdownDupe">
            <label for="selectedBallDupe">Select a Ball:</label>
            <select name="selectedBallDupe" id="selectedBallDupe" style= "font-family: 'Orbitron', sans-serif;" >
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
        	<button type="submit" name="action" value="addDuplicate" style= "font-family: 'Orbitron', sans-serif;" >Submit</button>
        </div>
        
        <!-- Existing Ball Drop-down for Deleting-->
        <div id="existingBallDropdownDelete">
            <label for="selectedBallDelete">Select a Ball:</label>
            <select name="selectedBallDelete" id="selectedBallDelete" style= "font-family: 'Orbitron', sans-serif;" >
                <c:forEach var="ball" items="${balls}">
                    <option value="${ball.brand},${ball.name},${ball.color},${ball.core},${ball.weight},${ball.diameter}">
                        ${ball.brand} - ${ball.name} - ${ball.color} - ${ball.core} - ${ball.weight} lbs - ${ball.diameter} in
                    </option>
                </c:forEach>
            </select>
            <br><br>
        	<button type="submit" name="action" value="delete" style= "font-family: 'Orbitron', sans-serif;" >Submit</button>
        </div>
        
    </form>
    <!-- Index button -->
    <br>
    <button id="indexButton" onclick="location.href= 'http://localhost:8081/lab02/index' "    style= "font-family: 'Orbitron', sans-serif;">Index</button>
     </div>
    </body>
</html>
