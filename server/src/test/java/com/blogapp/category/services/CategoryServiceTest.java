package com.blogapp.category.services;

import com.blogapp.category.domain.Category;
import com.blogapp.category.repositories.ICategoryDao;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock // the dependency required for the test
    private ICategoryDao categoryDao;

    @InjectMocks // the class under test
    private CategoryService categoryService;

    @Test
    @DisplayName("Should return false when the category does not exist")
    void deleteCategoryByIdWhenCategoryDoesNotExistThenReturnFalse() {
        Long categoryId = 1L;
        given(categoryDao.existsById(categoryId)).willReturn(false);
        boolean result = categoryService.deleteCategoryById(categoryId);
        assertFalse(result);
    }

    @Test
    @DisplayName("Should return true when the category exists")
    void deleteCategoryByIdWhenCategoryExistsThenReturnTrue() {
        Category category = new Category(1L, "label", true);
        given(categoryDao.existsById(1L)).willReturn(true);
        boolean result = categoryService.deleteCategoryById(1L);
        assertTrue(result);
    }

    @Test
    @DisplayName("Should return false when the label is already taken")
    void addCategoryWhenLabelIsAlreadyTakenThenReturnFalse() {
        Category category = new Category(1L, "label", true);
        given(categoryDao.existsByLabel(category.getLabel())).willReturn(true);
        boolean result = categoryService.addCategory(category);
        assertFalse(result);
    }

    @Test
    @DisplayName("Should save the category when the label is not taken")
    void addCategoryWhenLabelIsNotTakenThenSaveTheCategory() {
        Category category = new Category(1L, "label", true);
        given(categoryDao.existsByLabel(category.getLabel())).willReturn(false);
        boolean result = categoryService.addCategory(category);
        assertTrue(result);
        verify(categoryDao).save(category);
    }

    @Test
    @DisplayName("Should return all categories sorted by label")
    void getCategoriesShouldReturnAllCategoriesSortedByLabel() {
        // Given
        Category category1 = new Category(1L, "label1", true);
        Category category2 = new Category(2L, "label2", true);
        List<Category> parentCategories = Arrays.asList(category1, category2);

        // when
        when(categoryDao.findAll(Sort.by(Sort.Direction.ASC, "label")))
                .thenReturn(parentCategories);
        List<Category> result = categoryService.getCategories();

        // then
        assertEquals(parentCategories, result);
    }

    @Test
    @DisplayName("Should return the first 10 categories")
    void getTrendingCategoriesShouldReturnTheFirst10Categories() {
        Category category1 = new Category(1L, "Category 1", true);
        Category category2 = new Category(2L, "Category 2", true);
        Category category3 = new Category(3L, "Category 3", true);
        Category category4 = new Category(4L, "Category 4", true);
        Category category5 = new Category(5L, "Category 5", true);
        Category category6 = new Category(6L, "Category 6", true);
        Category category7 = new Category(7L, "Category 7", true);
        Category category8 = new Category(8L, "Category 8", true);
        Category category9 = new Category(9L, "Category 9", true);
        Category category10 = new Category(10L, "Category 10", true);
        List<Category> categories =
                Arrays.asList(
                        category1,
                        category2,
                        category3,
                        category4,
                        category5,
                        category6,
                        category7,
                        category8,
                        category9,
                        category10);

        Page<Category> page = mock(Page.class);

        when(categoryDao.findAll(PageRequest.of(0, 10))).thenReturn(page);
        when(page.toList()).thenReturn(categories);

        List<Category> result = categoryService.getTrendingCategories();

        assertEquals(10, result.size());
    }

    @Test
    @DisplayName("Should return the category matching the id")
    void getCategoryByIdShouldReturnTheCategoryMatchingTheId() {
        // given
        Optional<Category> category = Optional.of(new Category(1L, "label", true));

        // when
        when(categoryDao.findById(category.get().getId())).thenReturn(category);

        // then
        Optional<Category> expected = categoryService.getCategoryById(category.get().getId());
        assertThat(expected).isPresent();
        assertThat(expected.get().getId()).isEqualTo(1L);
        verify(categoryDao, times(1)).findById(expected.get().getId());
    }
}