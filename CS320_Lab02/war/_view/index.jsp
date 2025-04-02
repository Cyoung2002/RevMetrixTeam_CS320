<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">

	<head>
	<link href="https://fonts.googleapis.com/css2?family=Orbitron:wght@400;700&display=swap" rel="stylesheet">
	
	    <meta charset="UTF-8">
	   <title>RevMetrix</title>
		
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
		
		.title-header {
    text-align: center;
    background: #220066;
    padding: 20px;
    box-shadow: 0 0 10px #ff6600;
    border-radius: 8px;
    margin-bottom: 20px;
}

.title-header h1 {
    color: #00ffcc;
    text-shadow: 2px 2px 10px #0a0a2a;
    font-size: 2.5em;
    
}

img {
  border: 1px solid #ddd;
  border-radius: 4px;
  padding: 5px;
  width: 300px;
  height: 300px;
  float: right;
  margin-right: 250px;
  margin-left: 50px;
}

.text h2 {
    color: #00ffcc;
    text-shadow: 2px 2px 10px #0a0a2a;
    font-size: 1em;
    text-align: left;
    margin-left: 250px;
    margin-right: 50px;
}


    #arsenalbutton {
    font-family: 'Orbitron', sans-serif;
    }
    
        #gamebutton {
    font-family: 'Orbitron', sans-serif;
    }
    
        #shotbutton {
    font-family: 'Orbitron', sans-serif;
    }
    
        #establishmentbutton {
    font-family: 'Orbitron', sans-serif;
    }
    
        #eventbutton {
    font-family: 'Orbitron', sans-serif;
    }
    
            #sessionbutton {
    font-family: 'Orbitron', sans-serif;
    }


		</style>
		
		<script
			src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
			type="text/javascript">
		</script>
		
	</head>


	<body>
	    <div class="title-header">
        <h1>RevMetrix</h1>
    </div>
    
		<div>
			<!-- <button id="location.href='addNumbers.jsp' "">Add Numbers</button>  -->
<!--			<button id="addNumbersButton" onclick="location.href= 'http://localhost:8081/lab02/addNumbers' ">Add Numbers</button>
			</>
			<button id="multiplyNumbersButton" onclick="location.href= 'http://localhost:8081/lab02/multiplyNumbers' ">Multiply Numbers</button>
			</>
			<button id="guessingGameButton" onclick="location.href= 'http://localhost:8081/lab02/guessingGame' ">Guessing Game</button> -->
			</>
			<button id="arsenalbutton" onclick="location.href= 'http://localhost:8081/lab02/arsenal' ">Arsenal</button>
			</>
			<button id="gamebutton" onclick="location.href= 'http://localhost:8081/lab02/game' ">Game</button>
			</>
			<button id="shotbutton" onclick="location.href= 'http://localhost:8081/lab02/shot' ">Log a Shot</button>
			</>
			<button id="establishmentbutton" onclick="location.href= 'http://localhost:8081/lab02/establishment' ">Establishments</button>
			</>
			<button id="eventbutton" onclick="location.href= 'http://localhost:8081/lab02/event' ">Events</button>
			</>
			<button id="sessionbutton" onclick="location.href= 'http://localhost:8081/lab02/session' ">Sessions</button>
			
			<br><br>
			<br><br>
			<br><br>
			<img src="${pageContext.request.contextPath}/_view/ball.png" alt="Rev Metrix Ball">
			
</div>

	<div class = "text">
		<h2> RevMetrix is your ultimate performance-tracking tool to elevate your game. Record scores at the shot level, automatically calculate game and session totals, gain real-time insights into your performance, and so much more with RevMetrix. Track Events, Sessions, Games, Frames, Shots, etc. all while analyzing trends and refining your skills with detailed graphical feedback.</h2>
	</div>
			
		
		

	</body>
</html>
