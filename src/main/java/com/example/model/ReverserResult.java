package com.example.model;

public class ReverserResult {
    private String originalString;
    private String reversedString;

    public ReverserResult(String original, String reverse) {
        this.originalString = original;
        this.reversedString = reverse;
    }

    public String getOriginalString() {
        return originalString;
    }

    public String getReversedString() {
        return reversedString;
    }
}
