package dev.yunsung.minitalk.repository;

import dev.yunsung.minitalk.model.Talk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TalkRepository extends JpaRepository<Talk, Long> {

    List<Talk> findByFriend_Id(Long id);
}
