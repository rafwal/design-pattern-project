package pl.edu.agh.app.util;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class URL {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ThreadGroup {
        public static final String ALL = "/threadGroups";
        public static final String BY_ID = ALL + "/{threadGroupId}";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Test {
        public static final String ALL = "/tests";
        public static final String BY_ID = ALL + "/{requestId}";
        public static final String EXECUTE_BY_ID = BY_ID + "/runTests";
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class TimersGatherer {
        public static final String START = "/gatherer/start";
        public static final String STOP = "/gatherer/stop";
    }

    public static final String EXECUTE = "/runTests";


}
