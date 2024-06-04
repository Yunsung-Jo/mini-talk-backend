package dev.yunsung.minitalk.model;

import dev.yunsung.minitalk.dto.TalkDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor
public class Talk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_id")
    private Friend friend;

    @Column(nullable = false)
    private String text;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Talker talker;

    @CreatedDate
    private LocalDateTime createdDate;

    @Builder
    public Talk(Friend friend, String text, Talker talker) {
        this.friend = friend;
        this.text = text;
        this.talker = talker;
    }

    public TalkDTO toDto() {
        return TalkDTO.builder()
                .id(this.getId())
                .text(this.getText())
                .talker(this.getTalker())
                .createdDate(this.getCreatedDate())
                .build();
    }
}
