package pl.sda.model;

import java.time.Duration;
import java.time.LocalDateTime;

public class Task extends BaseErrand{
    private Duration duration;


    public Task(String title, Priority priority, LocalDateTime creationTime, Duration duration) {
        super(title, priority, creationTime);
        this.duration = duration;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public ErrandState getCurrentState() {
        return super.getCurrentState();
    }

    public void setCurrentState(ErrandState currentState) {
        super.setCurrentState(currentState);
    }
}
