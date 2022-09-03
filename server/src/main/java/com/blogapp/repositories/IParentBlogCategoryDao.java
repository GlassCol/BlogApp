package com.blogapp.repositories;

import com.blogapp.domains.ParentCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IParentBlogCategoryDao extends JpaRepository<ParentCategory, Long> {
    boolean existsByLabel(String label);
}
