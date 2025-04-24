package edu.ycp.cs320.lab02.model;

//For Reverend Metrix
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ShotObject {
    // Constants
    public static final int TOTAL_PINS = 10;
    private static final int[] PIN_POSITIONS = {7, 8, 9, 10, 4, 5, 6, 2, 3, 1};

    // Properties
    private int shotNumber;
    private Set<Integer> pinsKnockedDown = new HashSet<>();
    private Set<Integer> pinsStanding = new HashSet<>();
    private boolean isFoul;
    private String specialMark;
    
    private Frame shotFrame;
    //game
    //session
    private Ball selectBall;

    public ShotObject(int shotNumber) {
        setShotNumber(shotNumber);
    }

    // Core Methods
    public void recordPins(Integer... pins) {
        if (isFoul) return;
        
        for (int pin : pins) {
            if (pin >= 1 && pin <= TOTAL_PINS) {
                pinsKnockedDown.add(pin);
            }
        }
        updateSpecialMark();
    }

    // Special Mark Button Code
    
    //Strike
    public boolean isStrike() {
        // A strike is when all pins are knocked down on first shot
        return shotNumber == 1 && pinsKnockedDown.size() == TOTAL_PINS;
    }
    public void setAsStrike() {
        if (shotNumber == 2) {
            throw new IllegalStateException("Cannot have strike on second shot");
        }
        // Clear any existing pins and knock down all
        pinsKnockedDown.clear();
        for (int i = 1; i <= TOTAL_PINS; i++) {
            pinsKnockedDown.add(i);
        }
        specialMark = "X";
    }

    //Spare
    public boolean isSpare() {
        // A spare is when all remaining pins are knocked down on second shot
        return shotNumber == 2 && pinsKnockedDown.size() == TOTAL_PINS;
    }
    public void setAsSpare() {
        if (shotNumber != 2) {
            throw new IllegalStateException("Spare can only be on second shot");
        }
        if (pinsKnockedDown.size() != TOTAL_PINS) {
            throw new IllegalStateException("Not all pins were knocked down");
        }
        specialMark = "/";
    }

    public void setAsFoul() {
        this.isFoul = true;
        specialMark = "F";
        // Foul means no pins count, even if some were physically knocked down
        pinsKnockedDown.clear();
    }

    public void setAsGutter() {
        if (!pinsKnockedDown.isEmpty()) {
            throw new IllegalStateException("Gutter ball must have no pins knocked down");
        }
        specialMark = "-";
    }

    // Pin State Methods
    public List<Integer> getStandingPins() {
        return Arrays.stream(PIN_POSITIONS)
                   .filter(pin -> !pinsKnockedDown.contains(pin))
                   .boxed()
                   .collect(Collectors.toList());
    }

    public void setStandingPins(Set<Integer> pinsStanding) {
        this.pinsKnockedDown.clear();
        for (int pin = 1; pin <= TOTAL_PINS; pin++) {
            if (!pinsStanding.contains(pin)) {
                this.pinsKnockedDown.add(pin);  // Add to knocked down if not standing
            }
        }
        updateSpecialMark();
    }

    public void setStandingPinsFromForm(String[] standingPinValues) {
        Set<Integer> standingPins = new HashSet<>();
        if (standingPinValues != null) {
            for (String pinStr : standingPinValues) {
                standingPins.add(Integer.parseInt(pinStr));
            }
        }
        setStandingPins(standingPins);
    }

    public Set<Integer> getPinsKnockedDown() {
        return new HashSet<>(pinsKnockedDown);
    }

    // Helper Methods
    private void updateSpecialMark() {
        if (isFoul) {
            specialMark = "F";
            return;
        }
        
        // Strike has highest priority
        if (isStrike()) {
            specialMark = "X";
            return;
        }
        
        // Spare comes next
        if (isSpare()) {
            specialMark = "/";
            return;
        }
        
        // Then gutter ball
        if (pinsKnockedDown.isEmpty() && !isFoul) {
            specialMark = "-";
            return;
        }
        
        // Normal shot with no special mark
        specialMark = null;
    }

    // Getters and Setters
    public int getShotNumber() {
        return shotNumber;
    }

    public void setShotNumber(int shotNumber) {
        if (shotNumber < 1 || shotNumber > 2) {
            throw new IllegalArgumentException("Shot number must be 1 or 2");
        }
        this.shotNumber = shotNumber;
    }

    public boolean isFoul() {
        return isFoul;
    }

    public String getSpecialMark() {
        return specialMark;
    }

    @Override
    public String toString() {
        return String.format("Shot %d: %s | Pins Down: %s/%d | %s",
            shotNumber,
            specialMark != null ? specialMark : "NORMAL",
            pinsKnockedDown,
            TOTAL_PINS,
            isFoul ? "FOUL" : ""
        );
    }

	public Frame getShotFrame() {
		return shotFrame;
	}

	public void setShotFrame(Frame shotFrame) {
		this.shotFrame = shotFrame;
	}

	public Ball getSelectBall() {
		return selectBall;
	}

	public void setSelectBall(Ball selectBall) {
		this.selectBall = selectBall;
	}
	
	public boolean isValidPinState() {
	    if (isStrike()) {
	        return pinsKnockedDown.size() == TOTAL_PINS;
	    }
	    if (isSpare()) {
	        return pinsKnockedDown.size() == TOTAL_PINS;
	    }
	    return true; // Normal shot
	}
}