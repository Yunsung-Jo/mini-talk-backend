package dev.yunsung.minitalk.controller;

import dev.yunsung.minitalk.jwt.JwtTokenUtil;
import dev.yunsung.minitalk.model.User;
import dev.yunsung.minitalk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public String login(String idToken) {
        User user = userService.loadUser(idToken);
        if (user == null) {
            return "";
        }
        return jwtTokenUtil.createToken(user.getUserId());
    }
}
