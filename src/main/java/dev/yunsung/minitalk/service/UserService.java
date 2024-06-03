package dev.yunsung.minitalk.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import dev.yunsung.minitalk.config.ExternalProperties;
import dev.yunsung.minitalk.model.User;
import dev.yunsung.minitalk.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ExternalProperties properties;

    public User loadUser(String idToken) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier
                    .Builder(new NetHttpTransport(), new GsonFactory())
                    .setAudience(Collections.singletonList(properties.getClientId()))
                    .build();
            GoogleIdToken googleIdToken = verifier.verify(idToken);
            Payload payload = googleIdToken.getPayload();

            String userId = payload.getSubject();
            String name = (String) payload.get("name");
            String email = payload.getEmail();
            String picture = (String) payload.get("picture");

            User user = userRepository.findByUserId(userId)
                    .map(entity -> entity.update(name, picture))
                    .orElse(User.builder()
                            .userId(userId)
                            .name(name)
                            .email(email)
                            .picture(picture)
                            .build());
            return userRepository.save(user);
        } catch (Exception e) {
            return null;
        }
    }

    public User getUser(String userId) {
        return userRepository.findByUserId(userId).orElse(null);
    }
}
