package ru.altsora.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ru.altsora.domain.Category;
import ru.altsora.dto.CategoryDto;
import ru.altsora.exception.DomainNotFoundException;
import ru.altsora.repository.CategoryRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Тестирование сервиса по работе с категориями")
class CategoryServiceImplTest {
    private final CategoryRepository categoryRepository = mock(CategoryRepository.class);
    private final CategoryService categoryService = new CategoryServiceImpl(
            categoryRepository
    );

    @AfterEach
    void afterEach() {
        verifyNoMoreInteractions(categoryRepository);
    }

    @Test
    @DisplayName("Поиск категории по ID: категория найдена")
    void findById_found() {
        final long id = 1;
        final String name = "categoryName";
        final Category category = Category.builder().id(id).name(name).build();

        doReturn(Optional.of(category)).when(categoryRepository).findById(id);

        CategoryDto actual = categoryService.findById(id);
        assertNotNull(actual);
        assertEquals(id, actual.getId());
        assertEquals(name, actual.getName());

        verify(categoryRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Поиск категории по ID: категория не найдена")
    void findById_notFound() {
        final long id = 1;

        doReturn(Optional.empty()).when(categoryRepository).findById(id);

        final Executable executable = () -> categoryService.findById(id);
        assertThrows(DomainNotFoundException.class, executable);

        verify(categoryRepository, times(1)).findById(id);
    }
}