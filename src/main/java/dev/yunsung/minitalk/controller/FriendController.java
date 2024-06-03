package dev.yunsung.minitalk.controller;

import dev.yunsung.minitalk.dto.FriendDTO;
import dev.yunsung.minitalk.model.Job;
import dev.yunsung.minitalk.model.Personality;
import dev.yunsung.minitalk.model.User;
import dev.yunsung.minitalk.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    @GetMapping("friends")
    public List<FriendDTO> getFriends(@AuthenticationPrincipal User user) {
        return friendService.getFriends(user.getId());
    }

    @PostMapping("friend")
    public FriendDTO createFriend(
            @AuthenticationPrincipal User user,
            String name,
            Job job,
            Personality personality
    ) {
        return friendService.createFriend(user, name, job, personality);
    }
}
