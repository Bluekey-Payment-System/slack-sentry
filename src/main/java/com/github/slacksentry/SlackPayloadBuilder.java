package com.github.slacksentry;

import com.slack.api.model.Attachment;
import com.slack.api.model.Field;
import com.slack.api.webhook.Payload;
import java.util.List;

public class SlackPayloadBuilder {
	private String SENTRY_PERFORMANCE_URL_PREFIX;
	private String SLACK_MESSAGE_TEMPLATE_COLOR_CODE = "ff3399";
	private final static String SLACK_ERROR_FIELD_TITLE_API_URL = "API URL";
	private final static String SLACK_ERROR_FIELD_TITLE_ERROR_MESSAGE = "ERROR MESSAGE";
	private final static String SLACK_ERROR_FIELD_TITLE_SENTRY_LINK = "SENTRY LINK";


	public SlackPayloadBuilder(String sentryPerformanceUrlPrefix) {
		this.SENTRY_PERFORMANCE_URL_PREFIX = sentryPerformanceUrlPrefix;
	}

	public SlackPayloadBuilder(String sentryPerformanceUrlPrefix, String slackMessageTemplateColorCode) {
		this.SENTRY_PERFORMANCE_URL_PREFIX = sentryPerformanceUrlPrefix;
		this.SLACK_MESSAGE_TEMPLATE_COLOR_CODE = slackMessageTemplateColorCode;
	}

	public void changeSentryPerformanceUrlPrefix(String sentryPerformanceUrlPrefix) {
		this.SENTRY_PERFORMANCE_URL_PREFIX = sentryPerformanceUrlPrefix;
	}

	public void changeSlackMessageTemplateColorCode(String slackMessageTemplateColorCode) {
		this.SLACK_MESSAGE_TEMPLATE_COLOR_CODE = slackMessageTemplateColorCode;
	}

	public Payload buildPayload(String name, String errorMessage, String eventId) {
		return Payload.builder()
				.attachments(List.of(
						Attachment.builder()
								.color(SLACK_MESSAGE_TEMPLATE_COLOR_CODE) // color code
								.fields(List.of(
										Field.builder()
												.title(SLACK_ERROR_FIELD_TITLE_API_URL)
												.value(name)
												.build(),
										Field.builder()
												.title(SLACK_ERROR_FIELD_TITLE_ERROR_MESSAGE)
												.value(errorMessage)
												.build(),
										Field.builder()
												.title(SLACK_ERROR_FIELD_TITLE_SENTRY_LINK)
												.value(SENTRY_PERFORMANCE_URL_PREFIX + eventId)
												.build()
								))
								.build()
				))
				.build();
	}
}
