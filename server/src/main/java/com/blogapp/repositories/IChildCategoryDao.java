package com.blogapp.repositories;

import com.blogapp.domains.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IChildCategoryDao extends JpaRepository<SubCategory, Long> {
}
