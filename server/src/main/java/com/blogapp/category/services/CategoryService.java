package com.blogapp.category.services;

import com.blogapp.category.dto.ParentCategory;
import com.blogapp.category.repositories.IParentCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    private final IParentCategoryRepository parentCategoryRepository;

    @Autowired
    CategoryService(IParentCategoryRepository parentCategoryRepository) {
        this.parentCategoryRepository = parentCategoryRepository;
    }

    @Override
    public List<ParentCategory> getParentCategories() {
        return parentCategoryRepository.findAll(
                Sort.by(Sort.Direction.ASC, "label"));
    }

    public List<ParentCategory> getTrendingCategories() {
        return parentCategoryRepository.findAll(
                PageRequest.of(0, 10)).stream().toList();
    }

    @Override
    public ParentCategory getParentCategoryById(Long theId) {
        return parentCategoryRepository.findById(theId).orElse(null);
    }

    @Override
    public void addParentCategory(ParentCategory parentCategory) {
        if (!parentCategoryRepository.existsByLabel(parentCategory.getLabel())) {
            parentCategoryRepository.save(parentCategory);
        }
    }

    @Override
    public void deleteParentCategoryById(Long theId) {
        parentCategoryRepository.deleteById(theId);
    }
}
