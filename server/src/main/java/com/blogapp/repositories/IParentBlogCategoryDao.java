package com.blogapp.repositories;

import com.blogapp.domains.ParentBlogCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IParentBlogCategoryDao extends JpaRepository<ParentBlogCategory, Long> {
}
