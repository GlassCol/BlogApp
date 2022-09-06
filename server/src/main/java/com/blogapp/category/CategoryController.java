package com.blogapp.category;

import com.blogapp.ResponseHandler;
import com.blogapp.category.domain.Category;
import com.blogapp.category.domain.CategoryDTO;
import com.blogapp.category.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
    public ResponseEntity<Object> getCategories() {
        List<Category> categories = categoryService.getCategories();

        if (categories.isEmpty()) {
            return ResponseHandler.response(null, HttpStatus.NO_CONTENT, "No categories found");
        }
        return ResponseHandler.response(categories, HttpStatus.OK);
    }

    @GetMapping("/trending")
    public ResponseEntity<Object> getTrending() {
        List<Category> trending = categoryService.getTrendingCategories();

        if (trending.isEmpty()) {
            return ResponseHandler.response(null, HttpStatus.NO_CONTENT, "No trending categories found");
        }
        return ResponseHandler.response(trending, HttpStatus.OK);
    }

    @GetMapping("/{theId}")
    public  ResponseEntity<Object> getCategoryById(@PathVariable("theId") Long theId) {
        Optional<Category> category = categoryService.getCategoryById(theId);

        if (category.isPresent()) {
            return ResponseHandler.response(category, HttpStatus.OK);
        }
        return ResponseHandler.response(null, HttpStatus.NOT_FOUND, "Category not found");
    }

    @PostMapping(path = {"/"}, consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> addCategory(@RequestBody CategoryDTO categoryDTO) {

        Category category = applicationContext.getBean(Category.class);

        category.setId(categoryDTO.getId());
        category.setLabel(categoryDTO.getLabel());
        category.setSubCategories(categoryDTO.getSubCategories());

        // add the category
        if (categoryService.addCategory(category)) {
            return ResponseHandler.response(category, HttpStatus.OK);
        }
        return ResponseHandler.response(null, HttpStatus.CONFLICT, HttpStatus.CONFLICT.getReasonPhrase());

    }

    @DeleteMapping("/{theId}")
    public ResponseEntity<Object> deleteCategoryById(@PathVariable Long theId) {

        if (categoryService.deleteCategoryById(theId)) {
            return ResponseHandler.response(null, HttpStatus.OK);
        }
        return ResponseHandler.response(null, HttpStatus.NOT_MODIFIED, "Resource not deleted");
    }

}
