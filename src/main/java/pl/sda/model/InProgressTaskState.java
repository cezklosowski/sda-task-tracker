package pl.sda.model;

import java.time.LocalDateTime;

public class InProgressTaskState implements ErrandState {
    private LocalDateTime created;
    private Task baseErrand;

    public InProgressTaskState(Task baseErrand, LocalDateTime created) {
        this.baseErrand = baseErrand;
        this.created = created;
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
        return "In progress - " + LocalDateTime.now() + " - " + created;
    }
}
