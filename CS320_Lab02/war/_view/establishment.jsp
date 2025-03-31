<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Establishments</title>
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
body {
    font-family: Arial, sans-serif;
    background-color: #0a0a2a;
    color: #00ffcc;
    margin: 0;
    padding: 0;
    text-align: center;
}

/* Container for form and content */
.container {
    width: 80%;
    margin: 20px auto;
    background: #1a0033;
    padding: 20px;
    box-shadow: 0 0 15px #ff6600;
    border-radius: 8px;
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
    margin: 20px auto;
    padding: 15px;
    background: #330066;
    border-radius: 5px;
    box-shadow: 0 0 10px #00ffcc;
    width: 50%;
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
    padding: 10px;
    margin: 5px 0;
    width: 100%;
    border: 1px solid #00ffcc;
    border-radius: 5px;
    background: #220066;
    color: #00ffcc;
    font-size: 16px;
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

/* Hide initially hidden form sections */
#newEstablishmentFields, #existingEstablishmentDropdownDupe, #existingEstablishmentDropdownDelete {
    display: none;
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
    <h2>Manage Your Establishments</h2>

    <!-- Action Selection -->
    <label for="action">Choose an action:</label>
    
    <select id="action" name="actionSelect" onchange="updateForm()">
        <option value="addNew" ${param.actionSelect == 'addNew' ? 'selected' : ''}>Add New Establishment</option>
        <option value="delete" ${param.actionSelect == 'delete' ? 'selected' : ''}>Delete Existing Establishment</option>
    </select>

    <br><br>

    <!-- Form -->
    <form action="${pageContext.servletContext.contextPath}/establishment" method="post">

		<!-- Add New Establishment Fields -->
        <div id="newEstablishmentFields">
        
        	<c:forEach var="establishment" items="${establishments}">
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
    <br>
    <button id="indexButton" onclick="location.href= 'http://localhost:8081/lab02/index' ">Index</button>
</body>
</html>
