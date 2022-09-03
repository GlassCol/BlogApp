package com.blogapp.category;

import com.blogapp.ResponseHandler;
import com.blogapp.category.dto.ParentCategory;
import com.blogapp.category.dto.ParentCategoryDTO;
import com.blogapp.category.services.ICategoryService;
import org.apache.naming.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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
    public ResponseEntity<Object> getParentCategories() {
        List<ParentCategory> categories = categoryService.getParentCategories();

        if (categories.isEmpty()) {
            return ResponseHandler.response(null, HttpStatus.NO_CONTENT, "No categories found");
        }
        return ResponseHandler.response(categories, HttpStatus.OK);
    }

    @GetMapping("/trending")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getTrending() {
        List<ParentCategory> trending = categoryService.getTrendingCategories();

        if (trending.isEmpty()) {
            return ResponseHandler.response(null, HttpStatus.NO_CONTENT, "No trending categories found");
        }
        return ResponseHandler.response(trending, HttpStatus.OK);
    }


    @GetMapping("/{theId}")
    @ResponseStatus(HttpStatus.OK)
    public  ResponseEntity<Object> getParentCategoryById(@PathVariable("theId") Long theId) {
        ParentCategory parentCategory = categoryService.getParentCategoryById(theId);

        if (Objects.isNull(parentCategory)) {
            return ResponseHandler.response(null, HttpStatus.NOT_FOUND, "Category not found");
        }
        return ResponseHandler.response(parentCategory, HttpStatus.OK);
    }

    @PostMapping(path = {"/"}, consumes = {APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> addParentCategory(@RequestBody ParentCategoryDTO parentCategoryDTO) {
        // requires a plain pojo without annotations in order tor prevent injection
        ParentCategory parentCategory = applicationContext.getBean(ParentCategory.class);

        parentCategory.setId(parentCategoryDTO.getId());
        parentCategory.setLabel(parentCategoryDTO.getLabel());
        parentCategory.setSubCategories(parentCategoryDTO.getSubCategories());
        categoryService.addParentCategory(parentCategory);

        return ResponseHandler.response(parentCategory, HttpStatus.OK);

    }

    @DeleteMapping("/{theId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteParentCategoryById(@PathVariable Long theId) {
        categoryService.deleteParentCategoryById(theId);

        return ResponseHandler.response(null, HttpStatus.NOT_MODIFIED, "Resource not deleted");
    }

}
