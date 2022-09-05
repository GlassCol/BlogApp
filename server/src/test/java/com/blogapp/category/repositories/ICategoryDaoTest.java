package com.blogapp.category.repositories;

import com.blogapp.category.domain.Category;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest // adds the configuration required to run tests
@ActiveProfiles("test")
class ICategoryDaoTest {

    @Autowired
    private ICategoryDao categoryDao;

    @AfterEach
    void tearDown() {
        categoryDao.deleteAll();
    }

    @Test
    @DisplayName("Should return true when the label is found")
    void existsByLabelWhenLabelIsFoundThenReturnTrue() {
        // given
        Category category = new Category(1L, "label", true);
        categoryDao.save(category);
        // when
        boolean expected = categoryDao.existsByLabel(category.getLabel());
        // then
        assertThat(expected).isTrue();
    }

    @Test
    @DisplayName("Should return false when the label is not found")
    void existsByLabelWhenLabelIsNotFoundThenReturnFalse() {
        // given
        String label = "label";
        // when
        boolean expected = categoryDao.existsByLabel(label);
        // then
        assertThat(expected).isFalse();
    }

}
