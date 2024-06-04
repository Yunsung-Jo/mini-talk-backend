package dev.yunsung.minitalk.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RequestBody {
    private List<Contents> contents;
    private SystemInstruction systemInstruction;
    private List<SafetySettings> safetySettings;

    @Data
    @Builder
    public static class Contents {
        private String role;
        private List<Parts> parts;
    }

    @Data
    @Builder
    public static class SystemInstruction {
        private String role;
        private List<Parts> parts;
    }

    @Data
    @AllArgsConstructor
    public static class SafetySettings {
        private String category;
        private String threshold;
    }
}
