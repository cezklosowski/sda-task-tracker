package pl.sda.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Bug extends BaseErrand{
    private LocalDateTime terminationTime;


    public Bug(String title, Priority priority, LocalDateTime creationTime, LocalDateTime terminationTime) {
        super(title, priority, creationTime);
        this.terminationTime = terminationTime;
    }

    public LocalDateTime getTerminationTime() {
        return terminationTime;
    }

    public void setTerminationTime(LocalDateTime terminationTime) {
        this.terminationTime = terminationTime;
    }

    public ErrandState getCurrentState() {
        return super.getCurrentState();
    }

    public void setCurrentState(ErrandState currentState) {
        super.setCurrentState(currentState);
    }
}
