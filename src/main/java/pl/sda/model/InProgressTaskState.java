package pl.sda.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class InProgressTaskState implements ErrandState {
    private LocalDateTime creationTaskTime;
    private Task baseErrand;

    public InProgressTaskState(Task baseErrand, LocalDateTime creationTaskTime) {
        this.baseErrand = baseErrand;
        this.creationTaskTime = creationTaskTime;
    }

    @Override
    public ErrandState nextState() {
        DoneTaskState newState = new DoneTaskState(baseErrand, LocalDateTime.now());
        baseErrand.setCurrentState(newState);
        return newState;
    }

    @Override
    public ErrandState prevState() {
        ToDoTaskState newState = new ToDoTaskState(baseErrand, LocalDateTime.now());
        baseErrand.setCurrentState(newState);
        return newState;
    }

    @Override
    public String getMessage() {
        return "In progress - " + creationTaskTime.until(LocalDateTime.now(),ChronoUnit.SECONDS) + "s";
    }
}
