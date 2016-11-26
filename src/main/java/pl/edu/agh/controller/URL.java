package pl.edu.agh.controller;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class URL {

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ThreadGroup {
        public static final String ALL = "/threadGroups";
        public static final String BY_ID = ALL + "/{threadGroupId}";
    }

    public static class Request {
        public static final String ALL = "/requests";
        public static final String BY_ID = ALL + "/{requestId}";
    }
}
