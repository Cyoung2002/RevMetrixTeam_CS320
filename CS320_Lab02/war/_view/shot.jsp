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
            width: 30px;
            height: 30px;
            border-radius: 50%;
            border: 1px solid #333;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 3px;
            cursor: pointer;
            background-color: white;
            font-size: 0.7em;
        }
        /* First shot - selected pins (standing) are black */
        .pin.first-shot.selected {
            background-color: black;
            color: white;
        }
        /* Second shot - previously standing pins from first shot */
        .pin.second-shot.previous-standing {
            background-color: #cccccc;
            color: #666666;
            cursor: not-allowed;
        }
        /* Second shot - newly selected pins (standing) are red */
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
    </style>
</head>
<body>
    <div class="header">
        <div class="game-info">
            <div><fmt:formatDate value="${gameDate}" pattern="MM/dd/yyyy" /></div>
            <div>${eventType}</div>
        </div>
        <div class="game-info">
            <div>Game ${gameNumber}</div>
            <div>Frame ${frameNumber}</div>
        </div>
    </div>

    <form action="shot" method="post">
        <input type="hidden" name="shotNumber" value="${shotNumber}">
        <input type="hidden" name="frameNumber" value="${frameNumber}">
        
        <div class="content">
            <div class="pin-layout">
                <!-- Row 1 (Pins 7-8-9-10) -->
                <div class="pin-row">
                    <div class="pin ${shotNumber == 1 ? 'first-shot' : 'second-shot'} 
                        ${standingPins.contains(7) ? 'selected' : ''}
                        ${shotNumber == 2 && !standingPins.contains(7) ? 'previous-standing' : ''}"
                        data-pin="7">7
                    </div>
                    <div class="pin ${shotNumber == 1 ? 'first-shot' : 'second-shot'} 
                        ${standingPins.contains(8) ? 'selected' : ''}
                        ${shotNumber == 2 && !standingPins.contains(8) ? 'previous-standing' : ''}"
                        data-pin="8">8
                    </div>
                    <div class="pin ${shotNumber == 1 ? 'first-shot' : 'second-shot'} 
                        ${standingPins.contains(9) ? 'selected' : ''}
                        ${shotNumber == 2 && !standingPins.contains(9) ? 'previous-standing' : ''}"
                        data-pin="9">9
                    </div>
                    <div class="pin ${shotNumber == 1 ? 'first-shot' : 'second-shot'} 
                        ${standingPins.contains(10) ? 'selected' : ''}
                        ${shotNumber == 2 && !standingPins.contains(10) ? 'previous-standing' : ''}"
                        data-pin="10">10
                    </div>
                </div>
                <!-- Row 2 (Pins 4-5-6) -->
                <div class="pin-row">
                    <div class="pin ${shotNumber == 1 ? 'first-shot' : 'second-shot'} 
                        ${standingPins.contains(4) ? 'selected' : ''}
                        ${shotNumber == 2 && !standingPins.contains(4) ? 'previous-standing' : ''}"
                        data-pin="4">4
                    </div>
                    <div class="pin ${shotNumber == 1 ? 'first-shot' : 'second-shot'} 
                        ${standingPins.contains(5) ? 'selected' : ''}
                        ${shotNumber == 2 && !standingPins.contains(5) ? 'previous-standing' : ''}"
                        data-pin="5">5
                    </div>
                    <div class="pin ${shotNumber == 1 ? 'first-shot' : 'second-shot'} 
                        ${standingPins.contains(6) ? 'selected' : ''}
                        ${shotNumber == 2 && !standingPins.contains(6) ? 'previous-standing' : ''}"
                        data-pin="6">6
                    </div>
                </div>
                <!-- Row 3 (Pins 2-3) -->
                <div class="pin-row">
                    <div class="pin ${shotNumber == 1 ? 'first-shot' : 'second-shot'} 
                        ${standingPins.contains(2) ? 'selected' : ''}
                        ${shotNumber == 2 && !standingPins.contains(2) ? 'previous-standing' : ''}"
                        data-pin="2">2
                    </div>
                    <div class="pin ${shotNumber == 1 ? 'first-shot' : 'second-shot'} 
                        ${standingPins.contains(3) ? 'selected' : ''}
                        ${shotNumber == 2 && !standingPins.contains(3) ? 'previous-standing' : ''}"
                        data-pin="3">3
                    </div>
                </div>
                <!-- Row 4 (Pin 1) -->
                <div class="pin-row">
                    <div class="pin ${shotNumber == 1 ? 'first-shot' : 'second-shot'} 
                        ${standingPins.contains(1) ? 'selected' : ''}
                        ${shotNumber == 2 && !standingPins.contains(1) ? 'previous-standing' : ''}"
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

        <!-- Hidden field for standing pins -->
        <input type="hidden" name="standingPins" id="standingPins" value="${standingPinsString}">
    </form>

    <script>
        // Track STANDING pins (selected pins)
        let standingPins = new Set();
        
        // Initialize from server if available
        <c:if test="${not empty standingPinsString}">
            standingPins = new Set("${standingPinsString}".split(','));
        </c:if>
        
        // Set up pin click handlers
        document.querySelectorAll('.pin:not(.previous-standing)').forEach(pin => {
            pin.addEventListener('click', function() {
                if (this.classList.contains('previous-standing')) return;
                
                const pinNum = this.getAttribute('data-pin');
                
                if (standingPins.has(pinNum)) {
                    standingPins.delete(pinNum);
                    this.classList.remove('selected');
                } else {
                    standingPins.add(pinNum);
                    this.classList.add('selected');
                }
                
                // Update hidden input
                document.getElementById('standingPins').value = 
                    Array.from(standingPins).join(',');
            });
        });
        
        // Disable special buttons appropriately
        document.addEventListener('DOMContentLoaded', function() {
            const strikeBtn = document.querySelector('.option-btn.strike');
            const spareBtn = document.querySelector('.option-btn.spare');
            
            if (${shotNumber == 1}) {
                spareBtn.classList.add('disabled-btn');
            } else {
                strikeBtn.classList.add('disabled-btn');
            }
        });
    </script>
</body>
</html>