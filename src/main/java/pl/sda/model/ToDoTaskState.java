package pl.sda.model;

import java.time.LocalDateTime;

public class ToDoTaskState implements ErrandState {
    private Task baseErrand;
    private LocalDateTime created;

    public ToDoTaskState(Task baseErrand, LocalDateTime created) {
        this.baseErrand = baseErrand;
        this. created = created;
    }

    @Override
    public ErrandState nextState() {
        InProgressTaskState newState = new InProgressTaskState(baseErrand, LocalDateTime.now());
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
