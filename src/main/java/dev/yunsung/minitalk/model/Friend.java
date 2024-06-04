package dev.yunsung.minitalk.model;

import dev.yunsung.minitalk.dto.FriendDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Job job;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Personality personality;

    @Builder
    public Friend(User user, String name, Job job, Personality personality) {
        this.user = user;
        this.name = name;
        this.job = job;
        this.personality = personality;
    }

    public FriendDTO toDto() {
        return FriendDTO.builder()
                .id(this.getId())
                .name(this.getName())
                .job(this.getJob())
                .personality(this.getPersonality())
                .build();
    }
}
