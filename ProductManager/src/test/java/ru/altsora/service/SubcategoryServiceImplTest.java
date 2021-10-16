package ru.altsora.service;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import ru.altsora.exception.DomainNotFoundException;
import ru.altsora.exception.InvalidDataException;
import ru.altsora.model.domain.Category;
import ru.altsora.model.domain.Subcategory;
import ru.altsora.model.dto.SubcategoryDto;
import ru.altsora.model.request.SubcategoryAddIn;
import ru.altsora.repository.CategoryRepository;
import ru.altsora.repository.SubcategoryRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Тестирование сервиса по работе с подкатегориями")
class SubcategoryServiceImplTest {
    private final SubcategoryRepository subcategoryRepository = mock(SubcategoryRepository.class);
    private final CategoryRepository categoryRepository = mock(CategoryRepository.class);

    private final SubcategoryService subcategoryService = new SubcategoryServiceImpl(
            subcategoryRepository,
            categoryRepository
    );

    @AfterEach
    void afterEach() {
        verifyNoMoreInteractions(subcategoryRepository);
        verifyNoMoreInteractions(categoryRepository);
    }

    @Test
    @DisplayName("Поиск подкатегории по имени и ID категории: успешно")
    void findByCategoryAndName_OK() {
        final long subcategoryId = 1;
        final long categoryId = 1;
        final String name = "name";
        final Category category = Category.builder().id(categoryId).build();
        final Subcategory subcategory = Subcategory.builder().id(subcategoryId).category(category).name(name).build();

        doReturn(Optional.of(subcategory)).when(subcategoryRepository).findByCategoryAndName(categoryId, name);

        final SubcategoryDto actual = subcategoryService.findByCategoryAndName(categoryId, name);
        assertNotNull(actual);
        assertEquals(subcategoryId, actual.getId());
        assertEquals(categoryId, actual.getCategoryId());
        assertEquals(name, actual.getName());

        verify(subcategoryRepository, times(1)).findByCategoryAndName(categoryId, name);
    }

    @Test
    @DisplayName("Поиск подкатегории по имени и ID категории: не найдена")
    void findByCategoryAndName_notFound() {
        final long categoryId = 1;
        final String name = "name";

        doReturn(Optional.empty()).when(subcategoryRepository).findByCategoryAndName(categoryId, name);

        final Executable executable = () -> subcategoryService.findByCategoryAndName(categoryId, name);
        assertThrows(DomainNotFoundException.class, executable);

        verify(subcategoryRepository, times(1)).findByCategoryAndName(categoryId, name);
    }

    @Test
    @DisplayName("Поиск подкатегорий по категории: список не пустой")
    void findAllByCategory_OK() {
        final long categoryId = 1;
        final Category category = Category.builder().id(categoryId).build();
        final long id1 = 1;
        final String name1 = "name1";
        final Subcategory subcategory1 = Subcategory.builder().id(id1).category(category).name(name1).build();
        final long id2 = 2;
        final String name2 = "name2";
        final Subcategory subcategory2 = Subcategory.builder().id(id2).category(category).name(name2).build();
        final List<Subcategory> returnDomainList = List.of(subcategory1, subcategory2);

        doReturn(returnDomainList).when(subcategoryRepository).findAllByCategory(categoryId);

        final List<SubcategoryDto> actual = subcategoryService.findAllByCategory(categoryId);
        assertNotNull(actual);
        assertFalse(actual.isEmpty());
        assertEquals(2, actual.size());
        final SubcategoryDto dto1 = actual.get(0);
        assertNotNull(dto1);
        assertEquals(id1, dto1.getId());
        assertEquals(categoryId, dto1.getCategoryId());
        assertEquals(name1, dto1.getName());
        final SubcategoryDto dto2 = actual.get(1);
        assertNotNull(dto2);
        assertEquals(id2, dto2.getId());
        assertEquals(categoryId, dto2.getCategoryId());
        assertEquals(name2, dto2.getName());

        verify(subcategoryRepository, times(1)).findAllByCategory(categoryId);
    }

    @Test
    @DisplayName("Поиск подкатегорий по категории: список пустой")
    void findAllByCategory_empty() {
        final long categoryId = 1;

        doReturn(emptyList()).when(subcategoryRepository).findAllByCategory(categoryId);

        final List<SubcategoryDto> actual = subcategoryService.findAllByCategory(categoryId);
        assertNotNull(actual);
        assertTrue(actual.isEmpty());

        verify(subcategoryRepository, times(1)).findAllByCategory(categoryId);
    }

    @Test
    @DisplayName("Поиск подкатегории по ID: успешно")
    void findById_OK() {
        final long id = 1;
        final long categoryId = 1;
        final String name = "name";
        final Category category = Category.builder().id(categoryId).build();
        final Subcategory subcategory = Subcategory.builder().id(id).category(category).name(name).build();

        doReturn(Optional.of(subcategory)).when(subcategoryRepository).findById(id);

        final SubcategoryDto actual = subcategoryService.findById(id);
        assertNotNull(actual);
        assertEquals(id, actual.getId());
        assertEquals(categoryId, actual.getCategoryId());
        assertEquals(name, actual.getName());

        verify(subcategoryRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Поиск подкатегории по ID: не найдена")
    void findById_notFound() {
        final long id = 1;

        doReturn(Optional.empty()).when(subcategoryRepository).findById(id);

        final Executable executable = () -> subcategoryService.findById(id);
        assertThrows(DomainNotFoundException.class, executable);

        verify(subcategoryRepository, times(1)).findById(id);
    }

    @Test
    @DisplayName("Создание подкатегории: успешно")
    void add_OK() {
        final long id = 1;
        final long categoryId = 1;
        final String name = "name";
        final Category category = Category.builder().id(categoryId).build();
        final SubcategoryAddIn addIn = SubcategoryAddIn.builder().categoryId(categoryId).name(name).build();

        final Subcategory beforeSave = Subcategory.builder().category(category).name(name).build();
        final Subcategory afterSave = Subcategory.builder().id(id).category(category).name(name).build();

        doReturn(Optional.of(category)).when(categoryRepository).findById(categoryId);
        doReturn(false).when(subcategoryRepository).existsByCategoryAndName(categoryId, name);
        doReturn(afterSave).when(subcategoryRepository).saveAndFlush(beforeSave);

        final SubcategoryDto actual = subcategoryService.add(addIn);
        assertNotNull(actual);
        assertNotEquals(0, actual.getId());

        verify(categoryRepository, times(1)).findById(categoryId);
        verify(subcategoryRepository, times(1)).existsByCategoryAndName(categoryId, name);
        verify(subcategoryRepository, times(1)).saveAndFlush(beforeSave);
    }

    @Test
    @DisplayName("Создание подкатегории: пустое имя подкатегории")
    void add_emptyName() {
        final long categoryId = 1;
        final String name = Strings.EMPTY;
        final SubcategoryAddIn addIn = SubcategoryAddIn.builder().categoryId(categoryId).name(name).build();

        final Executable executable = () -> subcategoryService.add(addIn);
        assertThrows(InvalidDataException.class, executable);
    }

    @Test
    @DisplayName("Создание подкатегории: категория не существует")
    void add_categoryNotFound() {
        final long categoryId = 1;
        final String name = "name";
        final SubcategoryAddIn addIn = SubcategoryAddIn.builder().categoryId(categoryId).name(name).build();

        doReturn(Optional.empty()).when(categoryRepository).findById(categoryId);

        final Executable executable = () -> subcategoryService.add(addIn);
        assertThrows(DomainNotFoundException.class, executable);

        verify(categoryRepository, times(1)).findById(categoryId);
    }

    @Test
    @DisplayName("Создание подкатегории: подкатегория уже существует")
    void add_alreadyExists() {
        final long categoryId = 1;
        final String name = "name";
        final SubcategoryAddIn addIn = SubcategoryAddIn.builder().categoryId(categoryId).name(name).build();
        final Category category = Category.builder().id(categoryId).build();

        doReturn(Optional.of(category)).when(categoryRepository).findById(categoryId);
        doReturn(true).when(subcategoryRepository).existsByCategoryAndName(categoryId, name);

        final Executable executable = () -> subcategoryService.add(addIn);
        assertThrows(InvalidDataException.class, executable);

        verify(categoryRepository, times(1)).findById(categoryId);
        verify(subcategoryRepository, times(1)).existsByCategoryAndName(categoryId, name);
    }
}