package com.blogapp.controllers;

import com.blogapp.domains.ParentCategory;
import com.blogapp.services.interfaces.IBlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@RequestMapping(path = {"/categories"}, produces = APPLICATION_JSON_VALUE)
public class BlogCategoryController {

    private final IBlogCategoryService blogCategoryService;

    @Autowired
    BlogCategoryController(IBlogCategoryService blogCategoryService) {
        this.blogCategoryService = blogCategoryService;
    }

    @GetMapping({"", "/"})
    @ResponseStatus(HttpStatus.OK)
    public List<ParentCategory> getBlogCategories() {
        return blogCategoryService.getBlogCategories();
    }

    @GetMapping("/trending")
    @ResponseStatus(HttpStatus.OK)
    public List<ParentCategory> getTrending() {
        return blogCategoryService.getTrendingBlogCategories();
    }


    @GetMapping("/{theId}")
    @ResponseStatus(HttpStatus.OK)
    public ParentCategory getBlogCategoryById(@PathVariable("theId") Long theId) {
        return blogCategoryService.getBlogCategoryById(theId);
    }

    @PostMapping(path = {"/"}, consumes = {APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void addBlogCategory(@RequestBody ParentCategory parentCategory) {
        blogCategoryService.addBlogCategory(parentCategory);
    }

    @DeleteMapping("/{theId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlogCategoryById(@PathVariable Long theId) {
        blogCategoryService.deleteBlogCategoryById(theId);
    }

}
