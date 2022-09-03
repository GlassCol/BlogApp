package com.blogapp.services;

import com.blogapp.domains.ParentCategory;
import com.blogapp.repositories.IChildCategoryDao;
import com.blogapp.repositories.IParentBlogCategoryDao;
import com.blogapp.services.interfaces.IBlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogCategoryService implements IBlogCategoryService {

    private final IParentBlogCategoryDao parentBlogCategoryDao;
    private final IChildCategoryDao childCategoryDao;

    @Autowired
    BlogCategoryService(IParentBlogCategoryDao parentBlogCategoryDao, IChildCategoryDao childCategoryDao) {
        this.parentBlogCategoryDao = parentBlogCategoryDao;
        this.childCategoryDao = childCategoryDao;
    }

    @Override
    public List<ParentCategory> getBlogCategories() {
        return parentBlogCategoryDao.findAll(
                Sort.by(Sort.Direction.ASC, "label"));
    }

    public List<ParentCategory> getTrendingBlogCategories() {
        return parentBlogCategoryDao.findAll(
                PageRequest.of(0, 10)).stream().toList();
    }


    @Override
    public ParentCategory getBlogCategoryById(Long theId) {
        return parentBlogCategoryDao.findById(theId).orElse(null);
    }

    @Override
    public void addBlogCategory(ParentCategory parentCategory) {
        if (!parentBlogCategoryDao.existsByLabel(parentCategory.getLabel())) {
            parentBlogCategoryDao.save(parentCategory);
        }
    }

    @Override
    public void deleteBlogCategoryById(Long theId) {
        parentBlogCategoryDao.deleteById(theId);
    }
}
