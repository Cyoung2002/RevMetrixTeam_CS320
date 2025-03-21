<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Shot Entry</title>
    <style>
        .container {
            display: flex;
            flex-direction: column;
            align-items: center;
        }
		
		.gameFrameHeader {
            margin-right: 20px;
			display: flex;
            align-items: center;
            justify-content: center;
            position: relative;
            font-size: 28px;
            font-weight: bold;
		}
		
		.gameFrameHeader label {
			margin-right: 10px;
			text-align: center;
		}
		
		.gameFrameHeader input {
            border: 2px solid black;
			text-align: center;
			font-weight: bold;
		}
		
        .row {
            display: flex;
            justify-content: center;
        }

        .pin {
            width: 30px;
            height: 30px;
            border: 2px solid black;
            border-radius: 50%;
            margin: 5px;
            display: flex;
            align-items: center;
            justify-content: center;
            font-weight: bold;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .firstShot {
            width: 30px;
            height: 30px;
            border: 2px solid black;
            margin: 5px;
            display: flex;
            align-items: center;
            justify-content: center;
            position: relative;
            font-size: 28px;
            font-weight: bold;
			backgroundColor = 'orange'
        }

        .secondShot {
            width: 30px;
            height: 30px;
            border: 2px solid black;
            margin: 5px;
            display: flex;
            align-items: center;
            justify-content: center;
            position: relative;
            font-size: 28px;
            font-weight: bold;
			backgroundColor = 'lightgrey'
        }

        .miss {
            width: 30px;
            height: 30px;
            border: 2px solid black;
            margin: 5px;
            display: flex;
            align-items: center;
            justify-content: center;
            position: relative;
            font-size: 28px;
            font-weight: bold;
        }
		
        .foul {
            width: 30px;
            height: 30px;
            border: 2px solid black;
            margin: 5px;
            display: flex;
            align-items: center;
            justify-content: center;
            position: relative;
            font-size: 28px;
            font-weight: bold;
        }

        .strike {
            width: 30px;
            height: 30px;
            border: 2px solid black;
            margin: 5px;
            display: flex;
            align-items: center;
            justify-content: center;
            position: relative;
            font-size: 28px;
            font-weight: bold;
        }

        .spare {
            width: 30px;
            height: 30px;
            border: 2px solid black;
            margin: 5px;
            display: flex;
            align-items: center;
            justify-content: center;
            position: relative;
            font-size: 28px;
            font-weight: bold;
        }
		
		.firstShot::before {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }		
		
        .secondShot::before {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }

       .miss::before {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }

       .foul::before {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }		

       .strike::before {
/*            content: 'X';   */
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }		

        .spare::before {
/*            content: '/';  */
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }
		
        .pin.leave {
            background-color: black;
            color: white;
        }

        .pin.leave {
            background-color: black;
            color: white;
        }

        .pin.miss {
            background-color: red;
            color: black;
        }

    </style>
</head>
<body>

<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
	
		<form action="${pageContext.servletContext.contextPath}/shot" method="post">
		
    <div class="container">
		<div class="row">
			<div class="gameFrameHeader">
				<label>Game: </label>
				<input type="text" id="gameNum" readonly size="2" />
			</div>
			<div class="gameFrameHeader">			
				<label>Frame: </label>
				<input type="text" id="frameNum" readonly size="2" />
			</div>
		</div>				
        <div class="row">
            <div class="pin" onclick="togglePin(this)"><span>7</span></div>
            <div class="pin" onclick="togglePin(this)"><span>8</span></div>
            <div class="pin" onclick="togglePin(this)"><span>9</span></div>
            <div class="pin" onclick="togglePin(this)"><span>10</span></div>
        </div>
        <div class="row">
            <div class="pin" onclick="togglePin(this)"><span>4</span></div>
            <div class="pin" onclick="togglePin(this)"><span>5</span></div>
            <div class="pin" onclick="togglePin(this)"><span>6</span></div>
        </div>
        <div class="row">
            <div class="pin" onclick="togglePin(this)"><span>2</span></div>
            <div class="pin" onclick="togglePin(this)"><span>3</span></div>
        </div>
        <div class="row">
            <div class="pin" onclick="togglePin(this)"><span>1</span></div>
        </div>
        <div class="row">
            <div class="firstShot" onClick="setFirstShot()"><span> </span></div>
            <div class="secondShot" onClick="setSecondShot()"><span> </span></div>
		</div>
        <div class="row">
            <div class="foul" onclick="setFoul()"><span>F</span></div>
			<div class="miss" onclick="setGutter()"><span>-</span></div>
            <div class="strike" onclick="setStrike()"><span>X</span></div>
			<div class="spare" onclick="setSpare()"><span>/</span></div>
        </div>
         <div class="row">
		    <label for="firstShot">First Shot:</label>
		    <input type="text" id="firstShot" name="firstShot" value = "${firstShot}" />
		    
		    <label for="secondShot">Second Shot:</label>
		    <input type="text" id="secondShot" name="secondShot" value = "${secondShot}" />
		</div>
		<div class="row">
			<input type="Button" name="submit" value="<<<< Previous Frame" onclick="getPreviousFrame()">
			<input type="Button" name="submit" value="Next Frame >>>>" onclick="getNextFrame()">
        </div>
       
    </div>

    <script>
		// constants for shot variable
		const FIRST_SHOT  = 1;
		const SECOND_SHOT = 2;
		
		// constants for NO and ALL pins kocked down
		const NO_PINS  = 0;
		const ALL_PINS = 10; 
		
		// constants for frame limits
		const MIN_FRAMES = 1;
		const MAX_FRAMES = 12;
		
		// declare and initialize global variables
		let pinCount        = NO_PINS;		// intermediate pin count
		let firstShotCount  = NO_PINS;		// count of pins knocked down on first shot
		let secondShotCount = NO_PINS;		// count of pins knocked down on second shot
		let shot 			= FIRST_SHOT;	// start with first shot
		
		// TODO: when there is a server/application setting these, remove this
		// setting these here until they are sent down by the server
		let frameNumber = 1;
		let gameNumber  = 1;
		
//********************************************************************************
// This code initializes the display values through JS upon entry to the page
//********************************************************************************

		// TODO: change this to initialize to values sent from server
		// initialize Game and Frame
		document.getElementById('frameNum').value = frameNumber;
		document.getElementById('gameNum').value = gameNumber;	

		// select first shot to start
		setFirstShot();

//************************************************************************	
		
        function togglePin(pin) {
			// toggle the class of the clicked pin
            pin.classList.toggle("leave");

            // count the number of pins knocked down
            const pins = document.querySelectorAll('.pin:not(.leave)');
			
			// update pinCount with the # of pins knocked down
            pinCount = pins.length;
			
			// first shot?
			if (shot == FIRST_SHOT) {
				// update first shot count
				firstShotCount = pinCount;

				// strike?
				if (firstShotCount == ALL_PINS) {
					// display 'X'
					setStrike();
				}
				// otherwise, display count
				else {
					// update firstShot w/pinCount
					document.querySelector('.firstShot span').textContent = firstShotCount;
			
					// clear secondShot whenever firstShot changes
					clearSecondShot();
				}
			}
			// otherwise, second shot
			else {
				// calculate second shot count
				// pins left standing after first shot - pins left standing after second shot
				// reduces to pinCount - firstShotCount
				secondShotCount = pinCount - firstShotCount
				
				// spare?
				if (pinCount == ALL_PINS) {
					// display ('/')
					document.querySelector('.secondShot span').textContent = '/';
				}
				// if missed all pins
				else if (secondShotCount == NO_PINS) {
					// display '-', rather than '0' in second shot
					document.querySelector('.secondShot span').textContent = '-';	
				}
				// otherwise, display pin count
				else {
					// update secondShot w/pinCount
					document.querySelector('.secondShot span').textContent = secondShotCount;
				}
			}
		}

        function clearPins() {
            var pins = document.querySelectorAll('.pin');
            pins.forEach(function(pin) {
                pin.classList.remove('leave');
            });
        }
		
        function setAllPinsStanding() {
            var pins = document.querySelectorAll('.pin');
            pins.forEach(function(pin) {
                pin.classList.add('leave');
            });
			
			// reset all counts
			resetPinCounts();
			
			// clear secondShot whenever firstShot changes
			clearSecondShot();
        }
		
		// set gutter for current shot
		function setGutter() {
			// gutter on first shot?
			if (shot == FIRST_SHOT) {
				// set up all pins
				setAllPinsStanding();

				// clear secondShot whenever firstShot changes
				clearSecondShot();

				// display '-', rather than '0' in first shot
				document.querySelector('.firstShot span').textContent = '-';
			} 
			// otherwise, gutter on second shot
			else {
				// display '-', rather than '0' in second shot
				document.querySelector('.secondShot span').textContent = '-';	
			}				
		}

		// set foul for current shot
		function setFoul() {
			// foul on first shot?
			if (shot == FIRST_SHOT) {
				// set up all pins
				setAllPinsStanding();

				// clear secondShot whenever firstShot changes
				clearSecondShot();
				
				// display 'F', rather than '0' in first shot
				document.querySelector('.firstShot span').textContent = 'F';
			}
			// otherwise, foul on second shot
			else {
				// display 'F', rather than '0' in second shot
				document.querySelector('.secondShot span').textContent = 'F';
			}
		}

		function setFirstShot() {
			// set the shot # to first
			shot = FIRST_SHOT;
            document.querySelector('.firstShot').style.backgroundColor = 'orange';
            document.querySelector('.secondShot').style.backgroundColor = 'lightgrey';
		}
		
		function setSecondShot() {
			// set the shot # to second
			shot = SECOND_SHOT;
			
			document.querySelector('.secondShot').style.backgroundColor = 'orange';
            document.querySelector('.firstShot').style.backgroundColor = 'lightgrey';
		}
		
		function clearFirstShot() {
            // Clear the content of firstShot
            document.querySelector('.firstShot span').textContent = ' ';
        }
		
		function clearSecondShot() {
            // Clear the content of secondShot
            document.querySelector('.secondShot span').textContent = ' ';
        }
		
		function resetPinCounts() {
			pinCount        = NO_PINS;
			firstShotCount  = NO_PINS;
			secondShotCount = NO_PINS;
		}
		
		function setSpare() {
			// clear all pins
			clearPins();

			// switch to second shot
			setSecondShot();

            // update firstShot with most recent result
			// keep 'F' or '-'
            if (document.querySelector('.firstShot span').textContent !== '-') {
				if (document.querySelector('.firstShot span').textContent !== 'F') {					// update firstShot with most recent pinCount (from first ball)
					document.querySelector('.firstShot span').textContent = firstShotCount;
				}
			}
            // set secondShot for a spare ('/')
            document.querySelector('.secondShot span').textContent = '/';
        }
		
		function setStrike() {	
			// clear all pins, and firstShot, then put 'X' in secondShot
			clearPins();
			
			// switch to first shot
			setFirstShot();

			// reset all counts;
			resetPinCounts();
			
			// 'X' is displayed in second square
			clearFirstShot();
			
            // set secondShot for a strike ('X')
            document.querySelector('.secondShot span').textContent = 'X';
        }
		
		function getPreviousShot() {
			// unused - does nothing, yet
		}
		
		function getNextShot() {
			// unused - does nothing, yet
		}
		
		function getPreviousFrame() {
			// clear all pins
			clearPins();
			
			//clear all pin counts
			resetPinCounts();
			
	        // clear the content of firstShot
            document.querySelector('.firstShot span').textContent = ' ';
			
            // clear the content of secondShot
            document.querySelector('.secondShot span').textContent = ' ';
            
            document.getElementById('firstShot').value = '';  // Reset first shot text box
            document.getElementById('secondShot').value = ''; // Reset second shot text box
			
			// start with first shot
			setFirstShot();
			
			// TODO: remove this when server/application is calculating it
			//       for now, update frame number
			if (frameNumber > MIN_FRAMES) {
				frameNumber--;
				document.getElementById('frameNum').value = frameNumber;
			}
			
			// TODO: this will have to pull from the DB to get the previous shot info,
			//       and then reconstruct the frame
			//         what do you do with the existing frame data?
		}
		function getNextFrame() {
			// clear all pins
			clearPins();
			
			//clear all pin counts
			resetPinCounts();
			
	        // clear the content of firstShot
            document.querySelector('.firstShot span').textContent = ' ';
			
            // clear the content of secondShot
            document.querySelector('.secondShot span').textContent = ' ';	
            
            document.getElementById('firstShot').value = '';  // Reset first shot text box
            document.getElementById('secondShot').value = ''; // Reset second shot text box
			
			// start with first shot
			setFirstShot();
			
			// TODO: remove this when server/application is calculating it
			//       for now, update frame number
			if (frameNumber < MAX_FRAMES) {
				frameNumber++;
				document.getElementById('frameNum').value = frameNumber;
			}

			// TODO: if this is a new frame, then gather the info for the frame,
			//          submit it to the server, and start a new frame
			//       if this is an existing frame, then gather the info for the frame,
			//          and get the next frame from the server
			//		 if this is the last frame of the game, then what?
			//          next game in Session, what if it is the last game of the Session?
		}
		
    </script>
				
		</form>
		
		<button id="indexButton" onclick="location.href= 'http://localhost:8081/lab02/index' ">Index</button>
	</body>
</html>