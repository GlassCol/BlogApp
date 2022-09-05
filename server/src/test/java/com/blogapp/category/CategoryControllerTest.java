package com.blogapp.category;

import com.blogapp.category.domain.Category;
import com.blogapp.category.services.ICategoryService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    private ICategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @Test
    @DisplayName("Should return the category when the id is found")
    void getCategoryByIdWhenIdIsFound() {
        Category category = new Category(1L, "Test", true);
        when(categoryService.getCategoryById(1L)).thenReturn(java.util.Optional.of(category));

        ResponseEntity<Object> response = categoryController.getCategoryById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Should return a 404 when the id is not found")
    void getCategoryByIdWhenIdIsNotFoundThenReturn404() {
        when(categoryService.getCategoryById(anyLong())).thenReturn(java.util.Optional.empty());

        ResponseEntity<Object> response = categoryController.getCategoryById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Should return trending categories when there are trending categories")
    void getTrendingWhenThereAreTrendingCategoriesThenReturnTrendingCategories() {
        List<Category> trendingCategories = new ArrayList<>();
        trendingCategories.add(new Category(1L, "Trending", true));
        trendingCategories.add(new Category(2L, "Trending", true));

        when(categoryService.getTrendingCategories()).thenReturn(trendingCategories);

        ResponseEntity<Object> response = categoryController.getTrending();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Should return no content when there are no trending categories")
    void getTrendingWhenThereAreNoTrendingCategoriesThenReturnNoContent() {
        when(categoryService.getTrendingCategories()).thenReturn(Collections.emptyList());

        ResponseEntity<Object> response = categoryController.getTrending();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Should return a list of categories when there are categories")
    void getCategoriesWhenThereAreCategoriesThenReturnListOfCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1L, "Test", true));
        when(categoryService.getCategories()).thenReturn(categories);

        ResponseEntity<Object> response = categoryController.getCategories();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    @DisplayName("Should return an empty list when there are no categories")
    void getCategoriesWhenThereAreNoCategoriesThenReturnEmptyList() {
        when(categoryService.getCategories()).thenReturn(Collections.emptyList());

        ResponseEntity<Object> response = categoryController.getCategories();

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}