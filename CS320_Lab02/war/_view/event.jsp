<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Events</title>
    <link href="https://fonts.googleapis.com/css2?family=Orbitron:wght@400;700&display=swap" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
body {
    font-family: 'Orbitron', sans-serif;
    background-color: #0a0a2a;
    color: #00ffcc;
    margin: 0;
    padding: 0;
    text-align: center;
}

.wrapper {
    width: 80%;
    max-width: 1000px;
    margin: 30px auto;
    padding: 20px;
    background: #1a0033;
    border-left: 10px solid #ff6600;
    border-right: 10px solid #ff6600;
    box-shadow: 0 0 15px rgba(255, 102, 0, 0.8);
    border-radius: 10px;
}

h1, h2 {
    text-align: center;
    color: #ff00ff;
    text-shadow: 2px 2px 10px #ff6600;
}

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

select {
    cursor: pointer;
}

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

.error {
    color: red;
    font-weight: bold;
}

    </style>
    <script>
        function updateForm() {
            let action = document.getElementById("action").value;
            let newEventFields = document.getElementById("newEventFields");
            let existingEventDropdownDelete = document.getElementById("existingEventDropdownDelete");

            if (action === "addNew") {
                newEventFields.style.display = "block";
                existingEventDropdownDelete.style.display = "none";
            } else if (action === "delete") {
                newEventFields.style.display = "none";
                existingEventDropdownDelete.style.display = "block";
            }
        }

        window.onload = function() {
            updateForm();
        };
    </script>
</head>
<body>
    <div class="wrapper">
        <h2>Manage Your Events</h2>
        <label for="action">Choose an action:</label>
        <select id="action" name="actionSelect" onchange="updateForm()" style= "font-family: 'Orbitron', sans-serif;">
            <option value="addNew">Add New Event</option>
            <option value="delete">Delete Existing Event</option>
        </select>
        <br><br>
        <form action="${pageContext.servletContext.contextPath}/event" method="post">
            <div id="newEventFields">
                <label>Name:</label><input type="text" name="name">
                <label>Type:</label><input type="text" name="type">
                <label>Location:</label><input type="text" name="location">
                <label>Session:</label><input type="text" name="session">
                <label>Statistics:</label><input type="number" name="eventStats" step="0.1">
                <label>Standings:</label><input type="number" name="standings" step="0.1">
                <br><br><button type="submit" name="action" value="addNew" style= "font-family: 'Orbitron', sans-serif;">Submit</button>
            </div>
            <div id="existingEventDropdownDelete">
                <label for="selectedEventDelete">Select an Event:</label>
                <select name="selectedEventDelete" id="selectedEventDelete" style= "font-family: 'Orbitron', sans-serif;">
                    <c:forEach var="event" items="${events}">
                        <option value="${event.name},${event.type},${event.location},${event.session},${event.eventStats},${event.standings}">
                            ${event.name} - ${event.type} - ${event.location} - ${event.session} - ${event.eventStats} - ${event.standings}
                        </option>
                    </c:forEach>
                </select>
                <br><br><button type="submit" name="action" value="delete" style="font-family: 'Orbitron', sans-serif;">Submit</button>
            </div>
        </form>
        <button id="indexButton" onclick="location.href='http://localhost:8081/lab02/index'" style= "font-family: 'Orbitron', sans-serif;">Index</button>
    </div>
</body>
</html>
