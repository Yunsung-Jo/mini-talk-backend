package dev.yunsung.minitalk.dto;

import dev.yunsung.minitalk.model.Talker;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class TalkDTO {

    private Long id;
    private String text;
    private Talker talker;
    private LocalDateTime createdDate;
}
