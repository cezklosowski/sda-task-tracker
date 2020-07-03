package pl.sda.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DoneTaskState implements ErrandState {
    private LocalDateTime creationTaskTime;
    private Task baseErrand;

    public DoneTaskState(Task baseErrand, LocalDateTime creationTaskTime) {
        this.baseErrand = baseErrand;
        this.creationTaskTime = creationTaskTime;
    }

    @Override
    public ErrandState nextState() {
        return this;
    }

    @Override
    public ErrandState prevState() {
        InProgressTaskState newState = new InProgressTaskState(baseErrand, LocalDateTime.now());
        baseErrand.setCurrentState(newState);
        return newState;
    }

    @Override
    public String getMessage() {
        Long timeFromCreationTaskToCreationTastState = baseErrand.getCreationTime().until(creationTaskTime, ChronoUnit.SECONDS);
        return "Done - " + (baseErrand.getDuration().getSeconds() - baseErrand.getCreationTime().until(creationTaskTime, ChronoUnit.SECONDS)) + "s";

    }
}
