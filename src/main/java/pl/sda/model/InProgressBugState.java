package pl.sda.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class InProgressBugState implements ErrandState {
    private LocalDateTime creationBugTime;
    private Bug baseErrand;

    public InProgressBugState(Bug baseErrand, LocalDateTime creationBugTime) {
        this.baseErrand = baseErrand;
        this.creationBugTime = creationBugTime;
    }

    @Override
    public ErrandState nextState() {
        if(baseErrand.getTerminationTime().until(LocalDateTime.now(), ChronoUnit.SECONDS) > 0) {
            FailedBugState failedBugStateState = new FailedBugState(baseErrand, LocalDateTime.now());
            baseErrand.setCurrentState(failedBugStateState);
            return failedBugStateState;
        }
        DoneBugState newState = new DoneBugState(baseErrand, LocalDateTime.now());
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
        ToDoBugState newState = new ToDoBugState(baseErrand, LocalDateTime.now());
        baseErrand.setCurrentState(newState);
        return newState;
    }

    @Override
    public String getMessage() {
        return "In progress - " + creationBugTime.until(LocalDateTime.now(),ChronoUnit.SECONDS) + "s";
    }
}
