package pl.edu.agh.app.requestor.model.enums;


public enum TestExecutionState {
    IN_PROGRESS, //test is executing
    TIMED_OUT,
    ERROR,
    COLLECTING_METRICS,
    FINISHED //test finished successfully
}
