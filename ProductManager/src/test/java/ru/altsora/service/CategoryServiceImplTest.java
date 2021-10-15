package ru.altsora.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ru.altsora.domain.Category;
import ru.altsora.dto.CategoryDto;
import ru.altsora.exception.DomainNotFoundException;
import ru.altsora.repository.CategoryRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
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

        final CategoryDto actual = categoryService.findById(id);
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

    @Test
    @DisplayName("Поиск категории по имени: категория найдена")
    void findByName_found() {
        final long id = 1;
        final String name = "categoryName";
        final Category category = Category.builder().id(id).name(name).build();

        doReturn(Optional.of(category)).when(categoryRepository).findByName(name);

        final CategoryDto actual = categoryService.findByName(name);
        assertNotNull(actual);
        assertEquals(id, actual.getId());
        assertEquals(name, actual.getName());

        verify(categoryRepository, times(1)).findByName(name);
    }

    @Test
    @DisplayName("Поиск категории по имени: категория не найдена")
    void findByName_notFound() {
        final String name = "categoryName";

        doReturn(Optional.empty()).when(categoryRepository).findByName(name);

        final Executable executable = () -> categoryService.findByName(name);
        assertThrows(DomainNotFoundException.class, executable);

        verify(categoryRepository, times(1)).findByName(name);
    }

    @Test
    @DisplayName("Найти все категории: список не пуст")
    void findALl_notEmpty() {
        final long id1 = 1;
        final String name1 = "name1";
        final Category category1 = Category.builder().id(id1).name(name1).build();
        final long id2 = 2;
        final String name2 = "name2";
        final Category category2 = Category.builder().id(id2).name(name2).build();
        final List<Category> listReturn = List.of(category1, category2);

        doReturn(listReturn).when(categoryRepository).findAll();

        final List<CategoryDto> actual = categoryService.findAll();
        assertNotNull(actual);
        assertFalse(actual.isEmpty());
        assertEquals(2, actual.size());
        final CategoryDto dto1 = actual.get(0);
        assertNotNull(dto1);
        assertEquals(id1, dto1.getId());
        assertEquals(name1, dto1.getName());
        final CategoryDto dto2 = actual.get(1);
        assertNotNull(dto2);
        assertEquals(id2, dto2.getId());
        assertEquals(name2, dto2.getName());

        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Найти все категории: список пуст")
    void findAll_empty() {
        doReturn(emptyList()).when(categoryRepository).findAll();

        List<CategoryDto> actual = categoryService.findAll();
        assertNotNull(actual);
        assertTrue(actual.isEmpty());

        verify(categoryRepository, times(1)).findAll();
    }
}