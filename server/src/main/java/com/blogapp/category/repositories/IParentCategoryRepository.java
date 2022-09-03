package com.blogapp.category.repositories;

import com.blogapp.category.dto.ParentCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IParentCategoryRepository extends JpaRepository<ParentCategory, Long> {
    boolean existsByLabel(String label);
}
