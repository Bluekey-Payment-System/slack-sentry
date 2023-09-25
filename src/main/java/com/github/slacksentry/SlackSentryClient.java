package com.github.slacksentry;

import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import io.sentry.Sentry;
import io.sentry.SentryTracer;

import java.io.IOException;

public class SlackSentryClient {
    private final SlackSentryCredentials slackSentryCredentials;
    private final Slack slack;
    private final SlackPayloadBuilder slackPayloadBuilder;

    public SlackSentryClient(SlackSentryCredentials slackSentryCredentials, SlackPayloadBuilder slackPayloadBuilder) {
        this.slackSentryCredentials = slackSentryCredentials;
        this.slack = Slack.getInstance();
        this.slackPayloadBuilder = slackPayloadBuilder;
    }

    public void sendMessage(Exception exception) {
        SentryTracer information = ((SentryTracer) Sentry.getCurrentHub().getSpan());
        String eventId = information.getEventId().toString();
        String name = information.getName();
        Payload payload = slackPayloadBuilder.buildPayload(name, exception.getMessage(), eventId);
        try {
            slack.send(slackSentryCredentials.getWebhookUrl(), payload);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendCustomMessage() {
        Payload payload = slackPayloadBuilder.buildCustomPayload();
        try {
            slack.send(slackSentryCredentials.getWebhookUrl(), payload);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
