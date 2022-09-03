package com.blogapp.category;

import com.blogapp.category.dto.ParentCategory;
import com.blogapp.category.dto.ParentCategoryDTO;
import com.blogapp.category.services.ICategoryService;
import org.apache.naming.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = {"/categories"}, produces = APPLICATION_JSON_VALUE)
public class CategoryController {

    private final ICategoryService categoryService;
    private final ApplicationContext applicationContext;

    @Autowired
    CategoryController(ICategoryService categoryService, ApplicationContext applicationContext) {
        this.categoryService = categoryService;
        this.applicationContext = applicationContext;
    }

    @GetMapping({"", "/"})
    @ResponseStatus(HttpStatus.OK)
    public List<ParentCategory> getParentCategories() {
        return categoryService.getParentCategories();
    }

    @GetMapping("/trending")
    @ResponseStatus(HttpStatus.OK)
    public List<ParentCategory> getTrending() {
        return categoryService.getTrendingCategories();
    }


    @GetMapping("/{theId}")
    @ResponseStatus(HttpStatus.OK)
    public ParentCategory getParentCategoryById(@PathVariable("theId") Long theId) {
        return categoryService.getParentCategoryById(theId);
    }

    @PostMapping(path = {"/"}, consumes = {APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void addParentCategory(@RequestBody ParentCategoryDTO parentCategoryDTO) {
        // requires a plain pojo without annotations in order tor prevent injection
        ParentCategory parentCategory = applicationContext.getBean(ParentCategory.class);
        parentCategory.setId(parentCategoryDTO.getId());
        parentCategory.setLabel(parentCategoryDTO.getLabel());
        parentCategory.setSubCategories(parentCategoryDTO.getSubCategories());

        categoryService.addParentCategory(parentCategory);
    }

    @DeleteMapping("/{theId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteParentCategoryById(@PathVariable Long theId) {
        categoryService.deleteParentCategoryById(theId);
    }

}
