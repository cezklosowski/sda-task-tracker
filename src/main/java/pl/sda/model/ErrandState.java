package pl.sda.model;

public interface ErrandState {
    ErrandState nextState();
    ErrandState prevState();
    String getMessage();
}
