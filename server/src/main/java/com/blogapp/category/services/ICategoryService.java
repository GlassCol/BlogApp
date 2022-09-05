package com.blogapp.category.services;

import com.blogapp.category.domain.Category;
import java.util.List;
import java.util.Optional;

public interface ICategoryService {

    List<Category> getCategories();
    List<Category> getTrendingCategories();
    Optional<Category> getCategoryById(Long theId);
    boolean addCategory(Category category);
    boolean deleteCategoryById(Long theId);

}
