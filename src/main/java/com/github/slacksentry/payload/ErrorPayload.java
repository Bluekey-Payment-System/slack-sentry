package com.github.slacksentry.payload;

import com.github.slacksentry.PayloadFactory;
import com.github.slacksentry.SentryUrlGenerator;
import com.slack.api.webhook.Payload;
import io.sentry.SentryTracer;

import java.util.ArrayList;
import java.util.List;

public class ErrorPayload implements PayloadTemplate{

    private final String sentryBaseUrl;
    private final String sentryProjectName;
    private final SentryTracer sentryTracer;
    private final Exception exception;

    public ErrorPayload(String sentryBaseUrl, String sentryProjectName, SentryTracer sentryTracer, Exception exception) {
        this.sentryBaseUrl = sentryBaseUrl;
        this.sentryProjectName = sentryProjectName;
        this.sentryTracer = sentryTracer;
        this.exception = exception;
    }

    @Override
    public PayloadType getPayloadType() {
        return PayloadType.ERROR;
    }

    public Payload getPayload() {
        PayloadFactory payloadFactory = PayloadFactory.builder()
                .slackPayloadThemeColor(PayloadColorCode.ERROR_COLOR_CODE.getColorCode())
                .PayloadFieldTemplates(getPayloadTemplates())
                .build();
        return payloadFactory.getPayload();
    }

    private String getSentryPerformanceUrl() {
        return SentryUrlGenerator.getSentryPerformanceUrl(sentryBaseUrl, sentryProjectName);
    }

    private PayloadFieldTemplate getAPIUrl() {
        String apiUrl = sentryTracer.getName();
        return new PayloadFieldTemplate(PayloadTitleType.SLACK_ERROR_TITLE_API_URL.getValue(), apiUrl);
    }

    private PayloadFieldTemplate getErrorMessage() {
        String errorMessage = exception.getMessage();
        return new PayloadFieldTemplate(PayloadTitleType.SLACK_ERROR_TITLE_ERROR_MESSAGE.getValue(), errorMessage);
    }

    private PayloadFieldTemplate getSentryLink() {
        String eventId = sentryTracer.getEventId().toString();
        return new PayloadFieldTemplate(PayloadTitleType.SLACK_ERROR_TITLE_SENTRY_LINK.getValue(), getSentryPerformanceUrl() + eventId);
    }

    private List<PayloadFieldTemplate> getPayloadTemplates() {
        List<PayloadFieldTemplate> payloadFieldTemplates = new ArrayList<>();
        payloadFieldTemplates.add(getAPIUrl());
        payloadFieldTemplates.add(getErrorMessage());
        payloadFieldTemplates.add(getSentryLink());
        return payloadFieldTemplates;
    }
}
