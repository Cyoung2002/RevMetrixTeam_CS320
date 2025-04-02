<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Establishments</title>
    <link href="https://fonts.googleapis.com/css2?family=Orbitron:wght@400;700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">
    
    
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


    
    
    </style>
    <script>
    function updateForm() {
        let action = document.getElementById("action").value;
        let newEstablishmentFields = document.getElementById("newEstablishmentFields");
        let existingEstablishmentDropdownDelete = document.getElementById("existingEstablishmentDropdownDelete");

        if (action === "addNew") {
            newEstablishmentFields.style.display = "block";
            existingEstablishmentDropdownDelete.style.display = "none";
        } else if (action === "delete") {
            newEstablishmentFields.style.display = "none";
            existingEstablishmentDropdownDelete.style.display = "block";
        }
    }
        window.onload = function() {
            updateForm(); // Set correct form state on page load
        };

    </script>
</head>
<body>
    <div class="wrapper">
        <h2>Manage Your Establishments</h2>

        <!-- Action Selection -->
        <label for="action">Choose an action:</label>
        <select id="action" name="actionSelect" onchange="updateForm()" style= "font-family: 'Orbitron', sans-serif;">
            <option value="addNew" ${param.actionSelect == 'addNew' ? 'selected' : ''}>Add New Establishment</option>
            <option value="delete" ${param.actionSelect == 'delete' ? 'selected' : ''}>Delete Existing Establishment</option>
        </select>

        <br><br>

        <!-- Form -->
        <form action="${pageContext.servletContext.contextPath}/establishment" method="post">

            <!-- Add New Establishment Fields -->
            <div id="newEstablishmentFields">
                <c:forEach var="establishment" items="${establishments}">
                    <label>${establishment.name} - ${establishment.location} - ${establishment.phoneNumber} - ${establishment.hours}</label><br>
                </c:forEach>
                <br>

                <label for="name">Name:</label>
                <input type="text" name="name" id="name">
                
                <label for="location">Location:</label>
                <input type="text" name="location" id="location">
                
                <label for="phoneNumber">Phone Number:</label>
                <input type="text" name="phoneNumber" id="phoneNumber">
                
                <label for="hours">Hours:</label>
                <input type="text" name="hours" id="hours">
                
                <br><br>
                <button type="submit" name="action" value="addNew" style= "font-family: 'Orbitron', sans-serif;">Submit</button>
            </div>

            <!-- Existing Establishment Drop-down for Deleting -->
            <div id="existingEstablishmentDropdownDelete">
                <label for="selectedEstablishmentDelete">Select an Establishment:</label>
                <select name="selectedEstablishmentDelete" id="selectedEstablishmentDelete" style= "font-family: 'Orbitron', sans-serif;">
                    <c:forEach var="establishment" items="${establishments}">
                        <option value="${establishment.name},${establishment.location},${establishment.phoneNumber},${establishment.hours}">
                            ${establishment.name} - ${establishment.location} - ${establishment.phoneNumber} - ${establishment.hours}
                        </option>
                    </c:forEach>
                </select>
                <br><br>
                <button type="submit" name="action" value="delete" style= "font-family: 'Orbitron', sans-serif;">Submit</button>
            </div>
        </form>

        <!-- Index Button -->
        <button id="indexButton" onclick="location.href='http://localhost:8081/lab02/index'"style= "font-family: 'Orbitron', sans-serif;">Index</button>
    </div>
</body>

</html>
