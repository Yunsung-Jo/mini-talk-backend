package dev.yunsung.minitalk.repository;

import dev.yunsung.minitalk.model.Friend;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@NonNullApi
public interface FriendRepository extends JpaRepository<Friend, Long> {

    Optional<Friend> findById(Long id);
    List<Friend> findByUser_Id(Long id);
}
