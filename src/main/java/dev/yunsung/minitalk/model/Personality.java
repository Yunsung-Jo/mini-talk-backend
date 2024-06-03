package dev.yunsung.minitalk.model;

import lombok.Getter;

@Getter
public enum Personality {
    EXTROVERSION("외향적"),
    INTROVERSION("내향적"),
    RATIONAL("이성적"),
    EMOTIONAL("감성적");

    private final String personality;
    Personality(String personality) {
        this.personality = personality;
    }
}
