package com.github.slacksentry;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SlackSentryCredentials {
    private static final String HTTP_PATTERN = "^(http|https)://";
    private static final String SENTRY_DOMAIN_PATTERN = "sentry\\.io";
    private final String webhookUrl;
    private final String sentryBaseUrl;

    public SlackSentryCredentials(String webhookUrl, String sentryBaseUrl) {
        validateSlackWebhookUrl(webhookUrl);
        validateSentryBaseUrl(sentryBaseUrl);

        this.webhookUrl = webhookUrl;
        this.sentryBaseUrl = sentryBaseUrl;
    }

    public String getWebhookUrl() {
        return webhookUrl;
    }

    public String getSentryBaseUrl() {
        return sentryBaseUrl;
    }

    private void validateSlackWebhookUrl(String webhookUrl) {
        if (webhookUrl == null || webhookUrl.isEmpty()) {
            throw new IllegalArgumentException("webhookUrl cannot be null or empty.");
        }
    }

    private void validateSentryBaseUrl(String sentryBaseUrl) {
        if (sentryBaseUrl == null || sentryBaseUrl.isEmpty()) {
            throw new IllegalArgumentException("sentryBaseUrl cannot be null or empty.");
        }

        Pattern httpPattern = Pattern.compile(HTTP_PATTERN);
        Matcher httpMatcher = httpPattern.matcher(sentryBaseUrl);

        if (!httpMatcher.find()) {
            throw new IllegalArgumentException("Invalid protocol in sentryBaseUrl.");
        }

        Pattern sentryDomainPattern = Pattern.compile(SENTRY_DOMAIN_PATTERN);
        Matcher sentryDomainMatcher = sentryDomainPattern.matcher(sentryBaseUrl);

        if (!sentryDomainMatcher.find()) {
            throw new IllegalArgumentException("sentryBaseUrl must contain 'sentry.io'");
        }
    }
}
