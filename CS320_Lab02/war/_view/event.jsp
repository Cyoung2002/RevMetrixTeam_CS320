<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Event</title>
    <script>
        function updateForm() {
            let action = document.getElementById("action").value;
            let newEventFields = document.getElementById("newEventFields");
            let existingEventDropdownDelete = document.getElementById("existingEventDropdownDelete");

            if (action === "addNew") {
                newEventFields.style.display = "block";
                existingEventDropdownDelete.style.display = "none";
            } else if (action == "delete") {
            	newEventFields.style.display = "none";
                existingEventDropdownDelete.style.display = "block";
            }
        }

        window.onload = function() {
            updateForm(); // Set correct form state on page load
        };

    </script>
</head>
<body>
    <h2>Manage Your Events</h2>

    <!-- Action Selection -->
    <label for="action">Choose an action:</label>
    
    <select id="action" name="actionSelect" onchange="updateForm()">
        <option value="addNew" ${param.actionSelect == 'addNew' ? 'selected' : ''}>Add New Event</option>
        <option value="delete" ${param.actionSelect == 'delete' ? 'selected' : ''}>Delete Existing Event</option>
    </select>

    <br><br>

    <!-- Form -->
    <form action="${pageContext.servletContext.contextPath}/event" method="post">

		<!-- Add New Ball Fields -->
        <div id="newEventFields">
        
        	<c:forEach var="event" items="${events}">
        		<label>${event.name} - ${event.type} - ${event.location} - ${event.session} - ${event.eventStats} - ${event.standings}<br></label>
        	</c:forEach>
        	<br>
        	
            <label>Name:</label>
            <input type="text" name="name">
            <label>Type:</label>
            <input type="text" name="type">
            <label>Location:</label>
            <input type="text" name="location">
            <label>Session:</label>
            <input type="text" name="session">
            <label>Statistics:</label>
            <input type="number" name="eventStats" step="0.1">
            <label>Standings:</label>
            <input type="number" name="standings" step="0.1">
            
            <br><br>
        	<button type="submit" name="action" value="addNew">Submit</button>
        </div>
        
        <!-- Existing Ball Drop-down for Deleting-->
        <div id="existingEventDropdownDelete">
            <label for="selectedEventDelete">Select a Ball:</label>
            <select name="selectedEventDelete" id="selectedEventDelete">
                <c:forEach var="event" items="${events}">
                    <option value="${event.name},${event.type},${event.location},${event.session},${event.eventStats},${event.standings}">
                        ${event.name} - ${event.type} - ${event.location} - ${event.session} - ${event.eventStats} - ${event.standings}
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