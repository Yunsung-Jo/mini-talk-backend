package dev.yunsung.minitalk.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "external")
public class ExternalProperties {

    private String clientId;
    private String geminiApiKey;
}
