package com.blogapp.services.interfaces;

import com.blogapp.domains.ParentBlogCategory;
import java.util.List;

public interface IBlogCategoryService {

    List<ParentBlogCategory> getBlogCategories();
    ParentBlogCategory getBlogCategoryById(Long theId);
    void addBlogCategory(ParentBlogCategory parentBlogCategory);
    void deleteBlogCategoryById(Long theId);

}
