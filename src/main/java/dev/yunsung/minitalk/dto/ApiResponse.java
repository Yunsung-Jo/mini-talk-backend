package dev.yunsung.minitalk.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ApiResponse {
    private List<Candidates> candidates;

    @Data
    @NoArgsConstructor
    public static class Candidates {
        private Content content;

        @Data
        @NoArgsConstructor
        public static class Content {
            private String role;
            private List<Parts> parts;
        }
    }

    public String getText() {
        return this.candidates.get(0).getContent().getParts().get(0).getText();
    }
}
