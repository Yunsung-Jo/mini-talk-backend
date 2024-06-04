package dev.yunsung.minitalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MiniTalkApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiniTalkApplication.class, args);
    }

}
