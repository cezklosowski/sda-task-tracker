package pl.sda.model;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class DoneBugState implements ErrandState {
    private LocalDateTime creationBugTime;
    private Bug baseErrand;

    public DoneBugState(Bug baseErrand, LocalDateTime creationBugTime) {
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
        return this;
    }

    @Override
    public ErrandState prevState() {
        if(baseErrand.getTerminationTime().until(LocalDateTime.now(), ChronoUnit.SECONDS) > 0) {
            FailedBugState failedBugStateState = new FailedBugState(baseErrand, LocalDateTime.now());
            baseErrand.setCurrentState(failedBugStateState);
            return failedBugStateState;
        }
        InProgressBugState newState = new InProgressBugState(baseErrand, LocalDateTime.now());
        baseErrand.setCurrentState(newState);
        return newState;
    }

    @Override
    public String getMessage() {
        return "Done - " + baseErrand.getTerminationTime().until(LocalDateTime.now(), ChronoUnit.SECONDS) + "s";

    }
}
