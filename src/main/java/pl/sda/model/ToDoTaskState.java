package pl.sda.model;

import java.time.LocalDateTime;

public class ToDoTaskState implements ErrandState {
    private Task baseErrand;
    private LocalDateTime creationTaskTime;

    public ToDoTaskState(Task baseErrand, LocalDateTime creationTaskTime) {
        this.baseErrand = baseErrand;
        this.creationTaskTime = LocalDateTime.now();
    }

    @Override
    public ErrandState nextState() {
        InProgressTaskState newState = new InProgressTaskState(baseErrand, creationTaskTime);
        baseErrand.setCurrentState(newState);
        return newState;
    }

    @Override
    public ErrandState prevState() {
        return this;
    }

    @Override
    public String getMessage() {
        return "ToDo - " + baseErrand.getCreationTime();
    }
}
