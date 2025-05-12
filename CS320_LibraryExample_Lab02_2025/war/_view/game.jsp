<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Bowling Pin Buttons</title>
  
  		 <link href="https://fonts.googleapis.com/css2?family=Orbitron&display=swap" rel="stylesheet">
  <style>
    .pin-container {
      display: flex;
      flex-direction: column;
      align-items: center;
      gap: 10px;
      margin-top: 50px;
    }

    .pin-row {
      display: flex;
      justify-content: center;
      gap: 10px;
    }

    .pin-button {
      width: 50px;
      height: 50px;
      border-radius: 50%;
      border: 2px solid #444;
      background-color: #f0f0f0;
      font-weight: bold;
      cursor: pointer;
      transition: background-color 0.2s;
    }

    .pin-button.selected {
      background-color: black;
      color: white;
    }
  </style>
</head>
<body>

<div class="pin-container">
  <div class="pin-row">
  	<button class="pin-button" onclick="togglePin(this)">7</button>
    <button class="pin-button" onclick="togglePin(this)">8</button>
    <button class="pin-button" onclick="togglePin(this)">9</button>
    <button class="pin-button" onclick="togglePin(this)">10</button>
  </div>
  <div class="pin-row">
  	<button class="pin-button" onclick="togglePin(this)">4</button>
    <button class="pin-button" onclick="togglePin(this)">5</button>
    <button class="pin-button" onclick="togglePin(this)">6</button>
  </div>
  <div class="pin-row">
    <button class="pin-button" onclick="togglePin(this)">2</button>
    <button class="pin-button" onclick="togglePin(this)">3</button>
  </div>
  <div class="pin-row">
    <button class="pin-button" onclick="togglePin(this)">1</button>
  </div>
</div>

<script>
  function togglePin(button) {
    button.classList.toggle("selected");
  }
</script>

</body>
</html>
