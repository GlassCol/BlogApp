package com.blogapp.services.interfaces;

import com.blogapp.domains.ParentCategory;
import java.util.List;

public interface IBlogCategoryService {

    List<ParentCategory> getBlogCategories();
    List<ParentCategory> getTrendingBlogCategories();
    ParentCategory getBlogCategoryById(Long theId);
    void addBlogCategory(ParentCategory parentCategory);
    void deleteBlogCategoryById(Long theId);

}
