package com.blogapp.repositories;

import com.blogapp.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends JpaRepository<User, Long> {
}
