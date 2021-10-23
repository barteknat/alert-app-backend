package com.alert.app.backend.repository;

import com.alert.app.backend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getByEmail(String email);
    User getByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
