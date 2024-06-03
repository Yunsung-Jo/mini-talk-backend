package dev.yunsung.minitalk.dto;

import dev.yunsung.minitalk.model.Job;
import dev.yunsung.minitalk.model.Personality;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FriendDTO {

    private Long id;
    private String name;
    private Job job;
    private Personality personality;
}
