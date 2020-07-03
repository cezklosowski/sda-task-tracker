package pl.sda.model;

import java.time.LocalDateTime;

public class BaseErrand {
    private String title;
    private Priority priority;
    private LocalDateTime creationTime;
    private ErrandState currentState;

    public BaseErrand(String title, Priority priority, LocalDateTime creationTime) {
        this.title = title;
        this.priority = priority;
        this.creationTime = creationTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public ErrandState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(ErrandState currentState) {
        this.currentState = currentState;
    }
}
