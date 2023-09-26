package com.github.slacksentry.payload;

public enum PayloadColorCode {
    ERROR_COLOR_CODE("ff3399");

    private final String colorCode;
    PayloadColorCode(final String colorCode) {
        this.colorCode = colorCode;
    }

    public String getColorCode() {
        return colorCode;
    }
}
