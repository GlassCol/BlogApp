package com.blogapp.user.repositories;

import com.blogapp.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserDao extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    User findByEmail(String email);

    boolean existsByUsername(String username);
}
