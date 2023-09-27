package com.github.slacksentry;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentryUrlGenerator {
    private static final String PERFORMANCE_PATH = "performance";
    private static final String HTTP_PATTERN = "^(http|https)://";

    private static final String SLASH = "/";
    private static final String COLON = ":";

    private SentryUrlGenerator() {};

    public static String getSentryPerformanceUrl(String sentryBaseUrl, String sentryProjectName) {
        validateSentryBaseUrl(sentryBaseUrl);
        return sentryBaseUrl +
                SLASH +
                PERFORMANCE_PATH +
                SLASH +
                sentryProjectName +
                COLON;
    }

    private static void validateSentryBaseUrl(String sentryBaseUrl) {
        if (sentryBaseUrl == null || sentryBaseUrl.isEmpty()) {
            throw new IllegalArgumentException("sentryBaseUrl cannot be null or empty");
        }

        Pattern pattern = Pattern.compile(HTTP_PATTERN);
        Matcher matcher = pattern.matcher(sentryBaseUrl);

        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid protocol in sentryBaseUrl");
        }
    }
}
