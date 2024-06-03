package dev.yunsung.minitalk.service;

import dev.yunsung.minitalk.dto.FriendDTO;
import dev.yunsung.minitalk.model.Friend;
import dev.yunsung.minitalk.model.Job;
import dev.yunsung.minitalk.model.Personality;
import dev.yunsung.minitalk.model.User;
import dev.yunsung.minitalk.repository.FriendRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;

    public List<FriendDTO> getFriends(Long id) {
        return friendRepository.findByUser_Id(id)
                .stream()
                .map(Friend::toDto)
                .collect(Collectors.toList());
    }

    public FriendDTO createFriend(User user, String name, Job job, Personality personality) {
        Friend friend = Friend.builder()
                .user(user)
                .name(name)
                .job(job)
                .personality(personality)
                .build();
        friendRepository.save(friend);
        return friend.toDto();
    }
}
