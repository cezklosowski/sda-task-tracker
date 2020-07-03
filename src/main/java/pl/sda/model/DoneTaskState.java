package pl.sda.model;

import java.time.LocalDateTime;

public class DoneTaskState implements ErrandState {
    private LocalDateTime created;
    private Task baseErrand;

    public DoneTaskState(Task baseErrand, LocalDateTime created) {
        this.baseErrand = baseErrand;
        this.created = created;
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
        return "Done - " + baseErrand.getDuration() + " - " + baseErrand.getCreationTime() + " - " + created;
    }
}
