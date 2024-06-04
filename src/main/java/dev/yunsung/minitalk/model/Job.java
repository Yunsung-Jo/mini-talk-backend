package dev.yunsung.minitalk.model;

import lombok.Getter;

@Getter
public enum Job {
    STUDENT("대학생"),
    DEVELOPER("개발자"),
    UNEMPLOYED("백수");

    private final String name;
    Job(String name) {
        this.name = name;
    }
}
