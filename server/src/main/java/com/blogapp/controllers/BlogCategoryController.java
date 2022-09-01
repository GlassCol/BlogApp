package com.blogapp.controllers;

import com.blogapp.domains.ParentBlogCategory;
import com.blogapp.services.interfaces.IBlogCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = {"/categories"}, produces = APPLICATION_JSON_VALUE)
@CrossOrigin
public class BlogCategoryController {

    private final IBlogCategoryService blogCategoryService;

    @Autowired
    BlogCategoryController(IBlogCategoryService blogCategoryService) {
        this.blogCategoryService = blogCategoryService;
    }

    @GetMapping({"", "/"})
    @ResponseStatus(HttpStatus.OK)
    public List<ParentBlogCategory> getBlogCategories() {
        return blogCategoryService.getBlogCategories();
    }

    @GetMapping("/trending")
    @ResponseStatus(HttpStatus.OK)
    public List<ParentBlogCategory> getTrending() {
        return blogCategoryService.getTrendingBlogCategories();
    }


    @GetMapping("/{theId}")
    @ResponseStatus(HttpStatus.OK)
    public ParentBlogCategory getBlogCategoryById(@PathVariable("theId") Long theId) {
        return blogCategoryService.getBlogCategoryById(theId);
    }

    @PostMapping(path = {"/"}, consumes = {APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void addBlogCategory(@RequestBody ParentBlogCategory parentBlogCategory) {
        blogCategoryService.addBlogCategory(parentBlogCategory);
    }

    @DeleteMapping("/{theId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBlogCategoryById(@PathVariable Long theId) {
        blogCategoryService.deleteBlogCategoryById(theId);
    }

}
