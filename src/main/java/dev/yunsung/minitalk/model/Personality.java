package dev.yunsung.minitalk.model;

import lombok.Getter;

@Getter
public enum Personality {
    EXTROVERSION(
            "외향적",
            "다른 사람들과의 교류를 즐기고, 활동적인 사람"
    ),
    INTROVERSION(
            "내향적",
            "혼자 있는 시간을 선호하고, 조용한 활동을 좋아하는 사람"
    ),
    RATIONAL(
            "이성적",
            "감정보다는 논리와 이성을 중시하는 사람"
    ),
    EMOTIONAL(
            "감성적",
            "감정 표현이 풍부하고, 감정에 쉽게 휩쓸리는 사람"
    );

    private final String name;
    private final String detail;
    Personality(String name, String detail) {
        this.name = name;
        this.detail = detail;
    }
}
