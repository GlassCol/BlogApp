package com.blogapp.repositories;

import com.blogapp.domains.ChildBlogCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChildCategoryDao extends JpaRepository<ChildBlogCategory, Long> {
}
