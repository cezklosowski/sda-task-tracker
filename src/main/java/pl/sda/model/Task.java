package pl.sda.model;

import java.time.Duration;

public class Task extends BaseErrand{
    private Duration duration;
    private ErrandState currentState;

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public ErrandState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(ErrandState currentState) {
        this.currentState = currentState;
    }
}
