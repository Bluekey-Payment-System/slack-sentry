package com.github.slacksentry;

public class SentryUrlGenerator {
    private static final String URL_PROTOCOL = "https";
    private static final String HOST = "sentry.io";
    private static final String PERFORMANCE_PATH = "performance";
    private static final String SLASH = "/";
    private static final String COLON = ":";

    public static String getSentryPerformanceUrl(String sentryAccountName, String sentryProjectName) {
        return URL_PROTOCOL +
                COLON + SLASH + SLASH +
                sentryAccountName +
                HOST +
                SLASH +
                PERFORMANCE_PATH +
                SLASH +
                sentryProjectName +
                COLON;
    }
}
