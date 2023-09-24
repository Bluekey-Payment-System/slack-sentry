package com.github.slacksentry;

public class SlackSentryCredentials {
    private final String webhookUrl;

    public SlackSentryCredentials(String webhookUrl) {
        if (webhookUrl == null) {
            throw new IllegalArgumentException("webhookUrl cannot be null.");
        }
        this.webhookUrl = webhookUrl;
    }

    public String getWebhookUrl() {
        return webhookUrl;
    }
}
