package com.github.slacksentry.payload;

public class PayloadFieldTemplate {
    private final String title;
    private final String message;

    public PayloadFieldTemplate(String title, String message) {
        validateTitle(title);
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    private void validateTitle(String title) {
        if (title == null) {
            throw new IllegalArgumentException("Payload field title must not be null.");
        }
    }
}
