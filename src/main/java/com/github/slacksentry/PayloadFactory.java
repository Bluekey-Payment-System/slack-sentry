package com.github.slacksentry;

import com.github.slacksentry.payload.PayloadFieldTemplate;
import com.slack.api.model.Attachment;
import com.slack.api.model.Field;
import com.slack.api.webhook.Payload;

import java.util.ArrayList;
import java.util.List;

public class PayloadFactory {
    private final String slackPayloadThemeColor;
    private final List<PayloadFieldTemplate> PayloadFieldTemplates;

    public static class Builder {
        private String slackPayloadThemeColor;
        private List<PayloadFieldTemplate> PayloadFieldTemplates;

        public Builder slackPayloadThemeColor(String slackPayloadThemeColor) {
            this.slackPayloadThemeColor = slackPayloadThemeColor;
            return this;
        }

        public Builder PayloadFieldTemplates(List<PayloadFieldTemplate> PayloadFieldTemplates) {
            this.PayloadFieldTemplates = PayloadFieldTemplates;
            return this;
        }

        public PayloadFactory build() {
            return new PayloadFactory(this);
        }
    }

    private PayloadFactory(Builder builder) {
        slackPayloadThemeColor = builder.slackPayloadThemeColor;
        PayloadFieldTemplates = builder.PayloadFieldTemplates;
    }

    public static Builder builder() {
        return new Builder();
    }

    public Payload getPayload() {
        return Payload.builder()
                .attachments(generateAttachments())
                .build();
    }

    private List<Attachment> generateAttachments() {
        return List.of(
                Attachment.builder()
                        .color(slackPayloadThemeColor)
                        .fields(generateFields())
                        .build()
        );
    }

    private List<Field> generateFields() {
        List<Field> fields = new ArrayList<>();
        for(PayloadFieldTemplate payloadFieldTemplate : PayloadFieldTemplates) {
            Field field = Field.builder()
                    .title(payloadFieldTemplate.getTitle())
                    .value(payloadFieldTemplate.getMessage())
                    .build();
            fields.add(field);
        }
        return fields;
    }
}
