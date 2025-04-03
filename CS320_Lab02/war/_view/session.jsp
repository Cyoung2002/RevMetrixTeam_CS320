<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
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
        		<label>${session.establishment} - ${session.date} - ${session.time} - ${session.oppoTeam} - ${session.oppoPlayer} - ${session.numGames}<br></label>
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
            
            <br><br>
        	<button type="submit" name="action" value="addNew">Submit</button>
        </div>


        <!-- Existing Establishment Drop-down for Deleting-->
        <div id="existingSessionDropdownDelete">
            <label for="selectedSessionDelete">Select a Session:</label>
            <select name="selectedSessionDelete" id="selectedSessionDelete">
                <c:forEach var="session" items="${sessions}">
                    <option value="${session.establishment},${session.date},${session.time},${session.oppoTeam},${session.oppoPlayer},${session.numGames}">
                        ${session.establishment} - ${session.date} - ${session.time} - ${session.oppoTeam} - ${session.oppoPlayer} - ${session.numGames}
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