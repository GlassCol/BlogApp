package com.blogapp.category.services;

import com.blogapp.category.domain.Category;
import com.blogapp.category.repositories.ICategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryService implements ICategoryService {

    private final ICategoryDao categoryDao;

    @Autowired
    public CategoryService(ICategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Category> getCategories() {
        return categoryDao.findAll(Sort.by(Sort.Direction.ASC, "label"));
    }

    @Override
    public List<Category> getTrendingCategories() {
        return categoryDao.findAll(PageRequest.of(0, 10)).toList();
    }

    @Override
    public Optional<Category> getCategoryById(Long theId) {
        return categoryDao.findById(theId);
    }

    @Override
    public boolean addCategory(Category category) {
        if (categoryDao.existsByLabel(category.getLabel())) {
            return false;
        }
        categoryDao.save(category);
        return true;
    }

    @Override
    public boolean deleteCategoryById(Long theId) {
        if (categoryDao.existsById(theId)) {
            categoryDao.deleteById(theId);
            return true;
        }
        return false;
    }
}
