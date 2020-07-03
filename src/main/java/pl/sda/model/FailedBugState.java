package pl.sda.model;

import java.time.LocalDateTime;

public class FailedBugState implements ErrandState {
    private Bug baseErrand;
    private LocalDateTime creationBugTime;

    public FailedBugState(Bug baseErrand, LocalDateTime creationBugTime) {
        this.baseErrand = baseErrand;
        this.creationBugTime = LocalDateTime.now();
    }

    @Override
    public ErrandState nextState() {

        return this;
    }

    @Override
    public ErrandState prevState() {
        return this;
    }

    @Override
    public String getMessage() {
        return "Failed - " + baseErrand.getCreationTime();
    }
}
