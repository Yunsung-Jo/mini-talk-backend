package dev.yunsung.minitalk.controller;

import dev.yunsung.minitalk.dto.TalkDTO;
import dev.yunsung.minitalk.service.TalkService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TalkController {

    private final TalkService talkService;

    @PostMapping("talk/{id}")
    public TalkDTO talk(@PathVariable("id") Long id, @NonNull String text) {
        return talkService.createTalk(id, text);
    }
}
