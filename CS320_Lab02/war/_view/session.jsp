<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    
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
    
    <title>Sessions</title>
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <script>
    function updateForm() {
        let action = document.getElementById("action").value;
        //let action1 = document.getElementById("action1").value;
        //let action2 = document.getElementById("action2").value;
        //let action3 = document.getElementById("action3").value;
        //let action4 = document.getElementById("action4").value;
        let newSessionFields = document.getElementById("newSessionFields");
        let existingSessionDropdownDelete = document.getElementById("existingSessionDropdownDelete");
		
     	// Hide both sections first
        newSessionFields.style.display = "none";
        existingSessionDropdownDelete.style.display = "none";
        
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
        	
			<label>Session List:</label><br>
        	<c:forEach var="session" items="${sessions}">
        		<label>${session.establishment} - ${session.date} - ${session.time} - ${session.oppoTeam} - ${session.oppoPlayer} - ${session.numGames} - ${session.startLane}<br></label>
        	</c:forEach>
        	<br>
        	
        	<label for="action1">Choose an Establishment:</label>
    
    		<select id="action1" name="establishment">
        		<option value="Bowlerama" ${param.actionSelect == 'addNew' ? 'selected' : ''}>Bowlerama</option>
        		<option value="BowlyBowly" ${param.actionSelect == 'delete' ? 'selected' : ''}>BowlyBowly</option>
        		<option value="BowlingFun" ${param.actionSelect == 'delete' ? 'selected' : ''}>BowlingFun</option>
    		</select>
            
            <label>Date:</label>
            <input type="text" name="date">
            <label>Time:</label>
            <input type="text" name="time">
            
            <label for="action2">Choose an Opposing Team:</label>
    
    		<select id="action2" name="oppoTeam">
        		<option value="Lucky Strikes" ${param.actionSelect == 'addNew' ? 'selected' : ''}>Lucky Strikes</option>
        		<option value="Spare Change" ${param.actionSelect == 'delete' ? 'selected' : ''}>Spare Change</option>
        		<option value="Gutter Gang" ${param.actionSelect == 'delete' ? 'selected' : ''}>Gutter Gang</option>
    		</select>
    		
    		<label for="action3">Choose an Opposing Player:</label>
    
    		<select id="action3" name="oppoPlayer">
        		<option value="Caroline" ${param.actionSelect == 'addNew' ? 'selected' : ''}>Caroline</option>
        		<option value="Olivia" ${param.actionSelect == 'delete' ? 'selected' : ''}>Olivia</option>
        		<option value="Tanner" ${param.actionSelect == 'delete' ? 'selected' : ''}>Tanner</option>
        		<option value="Collin" ${param.actionSelect == 'delete' ? 'selected' : ''}>Collin</option>
    		</select>
    		
    		<label>Number of Games:</label>
            <input type="number" name="numGames">
            <label>Starting Lane Number:</label>
            <input type="number" name="startLane">
            
            <br><br>
        	<button type="submit" name="action" value="addNew">Submit</button>
        </div>


        <!-- Existing Establishment Drop-down for Deleting-->
        <div id="existingSessionDropdownDelete">
            <label for="selectedSessionDelete">Select a Session:</label>
            <select name="selectedSessionDelete" id="selectedSessionDelete">
                <c:forEach var="session" items="${sessions}">
                    <option value="${session.establishment},${session.date},${session.time},${session.oppoTeam},${session.oppoPlayer},${session.numGames},${session.startLane}">
                        ${session.establishment} - ${session.date} - ${session.time} - ${session.oppoTeam} - ${session.oppoPlayer} - ${session.numGames} - ${session.startLane}
                    </option>
                </c:forEach>
            </select>
            <br><br>
        	<button type="submit" name="action" value="delete">Submit</button>
        </div>
        
    </form>
    <!-- Index button -->
    <button id="indexButton" onclick="location.href= 'http://localhost:8081/lab02/index' ">Index</button>
	<button id="shotButton" onclick="location.href= 'http://localhost:8081/lab02/shot' ">Begin Session</button>
</body>
</html>