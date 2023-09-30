package com.github.slacksentry.payload;

public enum PayloadTitleType {
    SLACK_ERROR_TITLE_API_URL("API URL"),
    SLACK_ERROR_TITLE_ERROR_MESSAGE("ERROR MESSAGE"),
    SLACK_ERROR_TITLE_SENTRY_LINK("SENTRY LINK");

    private final String value;

    PayloadTitleType(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
