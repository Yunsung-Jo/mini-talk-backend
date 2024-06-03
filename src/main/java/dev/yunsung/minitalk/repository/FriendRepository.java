package dev.yunsung.minitalk.repository;

import dev.yunsung.minitalk.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    List<Friend> findByUser_Id(Long id);
}
