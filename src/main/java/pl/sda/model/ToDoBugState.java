package pl.sda.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ToDoBugState implements ErrandState {
    private Bug baseErrand;
    private LocalDateTime creationBugTime;

    public ToDoBugState(Bug baseErrand, LocalDateTime creationBugTime) {
        this.baseErrand = baseErrand;
        this.creationBugTime = LocalDateTime.now();
    }

    @Override
    public ErrandState nextState() {
        InProgressBugState newState = new InProgressBugState(baseErrand, creationBugTime);
        if(baseErrand.getTerminationTime().until(LocalDateTime.now(), ChronoUnit.SECONDS) > 0) {
            FailedBugState failedBugStateState = new FailedBugState(baseErrand, LocalDateTime.now());
            baseErrand.setCurrentState(failedBugStateState);
            return failedBugStateState;
        }
        baseErrand.setCurrentState(newState);
        return newState;
    }

    @Override
    public ErrandState prevState() {
        if(baseErrand.getTerminationTime().until(LocalDateTime.now(), ChronoUnit.SECONDS) > 0) {
            FailedBugState failedBugStateState = new FailedBugState(baseErrand, LocalDateTime.now());
            baseErrand.setCurrentState(failedBugStateState);
            return failedBugStateState;
        }
        return this;
    }

    @Override
    public String getMessage() {
        return "ToDo - " + baseErrand.getCreationTime();
    }


}
