package dev.yunsung.minitalk.repository;

import dev.yunsung.minitalk.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserId(String userId);
}
