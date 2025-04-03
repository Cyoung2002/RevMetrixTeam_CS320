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

    // Special Mark Setters
    public void setAsStrike() {
        if (shotNumber == 2) throw new IllegalStateException("Cannot have strike on second shot");
        pinsKnockedDown.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        specialMark = "X";
    }

    public void setAsSpare() {
        if (shotNumber == 1) throw new IllegalStateException("Cannot have spare on first shot");
        specialMark = "/";
    }

    public void setAsFoul() {
        this.isFoul = true;
        specialMark = "F";
    }

    public void setAsGutter() {
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
                this.pinsStanding.add(pin);
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
        
        if (pinsKnockedDown.size() == TOTAL_PINS) {
            specialMark = (shotNumber == 1) ? "X" : "/";
        } else if (pinsKnockedDown.isEmpty()) {
            specialMark = "-";
        } else {
            specialMark = null;
        }
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
}