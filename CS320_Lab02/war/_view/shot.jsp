<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bowling Shot Entry</title>
    <style>
        body {
            display: flex;
            flex-direction: column;
            align-items: center;
            font-family: Arial, sans-serif;
        }
        .header {
            text-align: center;
            margin-bottom: 20px;
        }
        .game-info {
            display: flex;
            justify-content: space-between;
            width: 100%;
            max-width: 600px;
            margin-bottom: 10px;
        }
        .frame-info {
            text-align: center;
            margin-bottom: 20px;
            font-size: 1.2em;
            font-weight: bold;
        }
        .content {
            display: flex;
            justify-content: center;
            align-items: flex-start;
            width: 100%;
            max-width: 800px;
        }
        .pin-layout {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin: 0 20px;
        }
        .pin-row {
            display: flex;
            margin-bottom: 10px;
            justify-content: center;
        }
        .pin {
            width: 40px;
            height: 40px;
            border-radius: 50%;
            border: 1px solid #333;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 3px;
            cursor: pointer;
            background-color: white;
            font-size: 1.0em;
        }
        /* First shot - pins start white, turn black when selected (standing) */
		.pin.first-shot {
		    background-color: white;
		    color: black;
		}
		.pin.first-shot.selected {
		    background-color: black;
		    color: white;
		}
		
		/* Second shot - pins knocked down in first shot (grayed out) */
		.pin.second-shot.knocked-down {
		    background-color: #cccccc;
		    color: #666666;
		    cursor: not-allowed;
		    pointer-events: none;
		}
		
		/* Second shot - pins standing after first shot (selectable) */
		.pin.second-shot.standing {
		    background-color: black;
		    color: white;
		    cursor: pointer;
		}
		
		/* Second shot - pins selected in current shot (will remain standing) */
		.pin.second-shot.selected {
		    background-color: #ff4444;
		    color: white;
		}
        .options {
            display: flex;
            flex-direction: column;
            margin-left: 30px;
        }
        .option-btn {
            margin: 5px;
            padding: 10px 15px;
            font-size: 16px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .strike { background: #4CAF50; color: white; }
        .spare { background: #2196F3; color: white; }
        .foul { background: #f44336; color: white; }
        .gutter { background: #9E9E9E; color: white; }
        .disabled-btn {
            opacity: 0.5;
            cursor: not-allowed;
        }
        .submit-btn {
            margin-top: 15px;
            padding: 8px 20px;
            background-color: #333;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .ball-selection {
	    margin-right: 30px;
	    text-align: center;
		}
	
		.shot-label {
		    font-weight: bold;
		    margin-bottom: 5px;
		    font-size: 1.2em;
		}
		
		.ball-dropdown {
		    padding: 8px 12px;
		    font-size: 16px;
		    border: 1px solid #333;
		    border-radius: 4px;
		    min-width: 150px;
		}
		/* Add this new style for the second ball dropdown */
        .second-ball-dropdown {
            margin-top: 10px;
        }
        
        /*attempted to fix to the css buttons */
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
		#indexbutton {
	    	font-family: 'Orbitron', sans-serif;
	    }
        
    </style>
</head>
<body>
    <div class="header">
        <div class="game-info">
            <div><fmt:formatDate value="${gameDate}" pattern="MM/dd/yyyy" /></div>
            <div>${eventType}</div>
        </div>
        <div class="game-info">
            <div>Game: ${gameNumber} - Frame: ${frameNumber} - Shot: ${shotNumber}</div>
        </div>
    </div>

    <form action="shot" method="post">
        <input type="hidden" name="shotNumber" value="${shotNumber}">
        <input type="hidden" name="frameNumber" value="${frameNumber}">
        
        <div class="content">
        
	        <div class="ball-selection">
			    <div class="shot-label">1st</div>
			    <select name="firstBall" id="firstBall" class="ball-dropdown">
			        <option value="">Select Ball</option>
			        <c:forEach items="${arsenalBalls}" var="ball">
			            <c:set var="ballValue" value="${ball.brand},${ball.name},${ball.color},${ball.core},${ball.weight},${ball.diameter}"/>
			            <option value="${ballValue}" 
			                <c:if test="${not empty firstBall and firstBall eq ballValue}">selected</c:if>>
			                ${ball.brand} - ${ball.name} - ${ball.color} - ${ball.core} - ${ball.weight} lbs - ${ball.diameter} in
			            </option>
			        </c:forEach>
			    </select>
			    
			    <div class="shot-label second-ball-dropdown">2nd</div>    
			    <select name="secondBall" id="secondBall" class="ball-dropdown">
			        <option value="">Select Ball</option>
			        <c:forEach items="${arsenalBalls}" var="ball">
			            <c:set var="ballValue" value="${ball.brand},${ball.name},${ball.color},${ball.core},${ball.weight},${ball.diameter}"/>
			            <option value="${ballValue}" 
			                <c:if test="${not empty secondBall and secondBall eq ballValue}">selected</c:if>>
			                ${ball.brand} - ${ball.name} - ${ball.color} - ${ball.core} - ${ball.weight} lbs - ${ball.diameter} in
			            </option>
			        </c:forEach>
			    </select>
	    	</div>            
	    	<div class="pin-layout">
                <!-- Row 1 (Pins 7-8-9-10) -->
                <div class="pin-row">
                    <c:forEach var="pin" items="7,8,9,10">
                        <div class="pin ${shotNumber == 1 ? 'first-shot' : standingPins.contains(pin) ? 'second-shot standing' : 'second-shot knocked-down'}"
						     data-pin="${pin}">
						     ${pin}
						</div>
                    </c:forEach>
                </div>
                <!-- Row 2 (Pins 4-5-6) -->
                <div class="pin-row">
                    <c:forEach var="pin" items="4,5,6">
                        <div class="pin ${shotNumber == 1 ? 'first-shot' : standingPins.contains(pin) ? 'second-shot standing' : 'second-shot knocked-down'}"
						     data-pin="${pin}">
						     ${pin}
						</div>
                    </c:forEach>
                </div>
                <!-- Row 3 (Pins 2-3) -->
                <div class="pin-row">
                    <c:forEach var="pin" items="2,3">
                        <div class="pin ${shotNumber == 1 ? 'first-shot' : standingPins.contains(pin) ? 'second-shot standing' : 'second-shot knocked-down'}"
						     data-pin="${pin}">
						     ${pin}
						</div>
                    </c:forEach>
                </div>
                <!-- Row 4 (Pin 1) -->
                <div class="pin-row">
                    <div class="pin ${shotNumber == 1 ? 'first-shot' : standingPins.contains(pin) ? 'second-shot standing' : 'second-shot knocked-down'}"
					     data-pin="1">1
					</div>
                </div>
                
                <button type="submit" class="submit-btn">Submit Shot</button>
            </div>

            <div class="options">
                <button type="submit" name="specialMark" value="X" 
                    class="option-btn strike ${shotNumber == 2 ? 'disabled-btn' : ''}">X (Strike)</button>
                <button type="submit" name="specialMark" value="/" 
                    class="option-btn spare ${shotNumber == 1 ? 'disabled-btn' : ''}">/ (Spare)</button>
                <button type="submit" name="specialMark" value="-" 
                    class="option-btn gutter">- (Gutter)</button>
                <button type="submit" name="specialMark" value="F" 
                    class="option-btn foul">F (Foul)</button>
            </div>
        </div>

        <input type="hidden" name="standingPins" id="standingPins" value="${standingPinsString}">
    </form>
        <!-- Index button -->
	<br>
	<button id="indexButton" onclick="location.href= 'http://localhost:8081/lab02/index' "    style= "font-family: 'Orbitron', sans-serif;">Index</button>
	<button id="gamebutton" onclick="location.href= 'http://localhost:8081/lab02/game' ">Game</button>
	    

     <script>
    document.addEventListener('DOMContentLoaded', function() {
        // Initialize from server data
        var standingPinsInput = document.getElementById('standingPins');
        var currentShotPins = new Set();
        if (standingPinsInput.value) {
            standingPinsInput.value.split(',').forEach(function(pin) {
                if (pin) currentShotPins.add(pin);
            });
        }
        
        // Set up pin click handlers
        document.querySelectorAll('.pin').forEach(function(pin) {
            // Only make standing pins clickable
            if (pin.classList.contains('first-shot') || 
                (pin.classList.contains('second-shot') && pin.classList.contains('standing'))) {
                
                pin.addEventListener('click', function() {
                    var pinNum = this.getAttribute('data-pin');
                    
                    if (currentShotPins.has(pinNum)) {
                        currentShotPins.delete(pinNum);
                        this.classList.remove('selected');
                    } else {
                        currentShotPins.add(pinNum);
                        this.classList.add('selected');
                    }
                    
                    // Update hidden input
                    standingPinsInput.value = Array.from(currentShotPins).join(',');
                });
                
                // Initialize selected state if needed
                if (currentShotPins.has(pin.getAttribute('data-pin'))) {
                    pin.classList.add('selected');
                }
            }
        });

        // Ball selection logic
        const firstBallSelect = document.getElementById('firstBall');
        const secondBallSelect = document.getElementById('secondBall');

        // When first ball changes, update second ball if it's empty
        firstBallSelect.addEventListener('change', function() {
            if (this.value && secondBallSelect.value === '') {
                secondBallSelect.value = this.value;
            }
        });

        // Initialize the second ball if we're on the second shot and first ball is set
        if (${shotNumber == 2} && firstBallSelect.value) {
            secondBallSelect.value = firstBallSelect.value;
        }
    });
    </script>
</body>
</html>