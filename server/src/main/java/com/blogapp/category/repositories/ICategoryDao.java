package com.blogapp.category.repositories;

import com.blogapp.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryDao extends JpaRepository<Category, Long> {
    boolean existsByLabel(String label);
}
