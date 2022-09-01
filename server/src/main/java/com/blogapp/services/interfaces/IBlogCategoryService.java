package com.blogapp.services.interfaces;

import com.blogapp.domains.ParentBlogCategory;
import java.util.List;

public interface IBlogCategoryService {

    List<ParentBlogCategory> getBlogCategories();
    List<ParentBlogCategory> getTrendingBlogCategories();
    ParentBlogCategory getBlogCategoryById(Long theId);
    void addBlogCategory(ParentBlogCategory parentBlogCategory);
    void deleteBlogCategoryById(Long theId);

}
