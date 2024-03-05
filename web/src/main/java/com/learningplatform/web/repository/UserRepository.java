package com.learningplatform.web.repository;

import com.learningplatform.web.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String Email);
    UserEntity findByUsername(String username);

    UserEntity findFirstByUsername(String username);
}
