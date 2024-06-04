package dev.yunsung.minitalk.service;

import dev.yunsung.minitalk.config.ExternalProperties;
import dev.yunsung.minitalk.dto.ApiResponse;
import dev.yunsung.minitalk.dto.Parts;
import dev.yunsung.minitalk.dto.TalkDTO;
import dev.yunsung.minitalk.model.Friend;
import dev.yunsung.minitalk.model.Talk;
import dev.yunsung.minitalk.model.Talker;
import dev.yunsung.minitalk.repository.FriendRepository;
import dev.yunsung.minitalk.repository.TalkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

import static dev.yunsung.minitalk.dto.RequestBody.*;

@Service
@RequiredArgsConstructor
public class TalkService {
    private final FriendRepository friendRepository;
    private final TalkRepository talkRepository;
    private final ExternalProperties properties;

    public TalkDTO createTalk(Long id, String text) {
        Friend friend = friendRepository.findById(id)
                .orElse(null);
        if (friend == null) return null;
        Talk userTalk = Talk.builder()
                .friend(friend)
                .text(text)
                .talker(Talker.USER)
                .build();
        talkRepository.save(userTalk);

        List<Talk> talks = talkRepository.findByFriend_Id(friend.getId());
        ApiResponse response = sendPostRequest(friend, talks).block();
        if (response == null) return null;

        Talk aiTalk = Talk.builder()
                .friend(friend)
                .text(response.getText())
                .talker(Talker.MODEL)
                .build();
        talkRepository.save(aiTalk);

        return aiTalk.toDto();
    }

    public Mono<ApiResponse> sendPostRequest(Friend friend, List<Talk> talks) {
        String systemInstruction =
                """
                너는 나의 친구이다.
                너는 인공지능이 아니고 사람이다.
                장문의 대답은 하지 않는다.
                다른 대상을 말할 때 OO, XX 같은 표현 대신 정확하게 표현한다.
                
                너의 이름은 "%s" 입니다.
                나의 이름은 "%s" 입니다.
                %s의 직업은 "%s" 입니다.
                %s의 성격은 "%s" 이고 성격에 어울리는 말투를 사용합니다.
                %s는 %s입니다.
                """.formatted(
                        friend.getName(),
                        friend.getUser().getName(),
                        friend.getName(),
                        friend.getJob().getName(),
                        friend.getName(),
                        friend.getPersonality().getName(),
                        friend.getName(),
                        friend.getPersonality().getDetail()
                );
        List<Contents> contents = new ArrayList<>();
        talks.forEach(talk -> contents.add(
                Contents.builder()
                        .role(talk.getTalker().name())
                        .parts(List.of(new Parts(talk.getText())))
                        .build()
        ));
        return WebClient.builder().build().post()
                .uri("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent")
                .headers(header -> header.add("x-goog-api-key", properties.getGeminiApiKey()))
                .bodyValue(builder()
                        .contents(contents)
                        .systemInstruction(SystemInstruction.builder()
                                .role("system")
                                .parts(List.of(new Parts(systemInstruction)))
                                .build())
                        .safetySettings(List.of(
                                new SafetySettings("HARM_CATEGORY_HARASSMENT", "BLOCK_NONE"),
                                new SafetySettings("HARM_CATEGORY_HATE_SPEECH", "BLOCK_NONE"),
                                new SafetySettings("HARM_CATEGORY_SEXUALLY_EXPLICIT", "BLOCK_NONE"),
                                new SafetySettings("HARM_CATEGORY_DANGEROUS_CONTENT", "BLOCK_NONE")
                        ))
                        .build())
                .retrieve()
                .bodyToMono(ApiResponse.class);
    }
}
