package com.github.slacksentry;

public class SentryUrlGenerator {
    private static final String PERFORMANCE_PATH = "performance";

    private static final String SLASH = "/";
    private static final String COLON = ":";

    private SentryUrlGenerator() {};

    public static String getSentryPerformanceUrl(String sentryBaseUrl, String sentryProjectName) {
        return sentryBaseUrl +
                SLASH +
                PERFORMANCE_PATH +
                SLASH +
                sentryProjectName +
                COLON;
    }
}
