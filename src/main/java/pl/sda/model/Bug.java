package pl.sda.model;

import java.time.LocalDateTime;

public class Bug extends BaseErrand{
    private LocalDateTime terminationTime;
    private ErrandState currentState;



    public LocalDateTime getTerminationTime() {
        return terminationTime;
    }

    public void setTerminationTime(LocalDateTime terminationTime) {
        this.terminationTime = terminationTime;
    }

    public ErrandState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(ErrandState currentState) {
        this.currentState = currentState;
    }
}
