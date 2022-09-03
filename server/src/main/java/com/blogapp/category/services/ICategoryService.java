package com.blogapp.category.services;

import com.blogapp.category.dto.ParentCategory;
import java.util.List;

public interface ICategoryService {

    List<ParentCategory> getParentCategories();
    List<ParentCategory> getTrendingCategories();
    ParentCategory getParentCategoryById(Long theId);
    void addParentCategory(ParentCategory parentCategory);
    void deleteParentCategoryById(Long theId);

}
