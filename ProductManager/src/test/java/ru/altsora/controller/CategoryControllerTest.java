package ru.altsora.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import ru.altsora.dto.CategoryDto;
import ru.altsora.exception.DomainNotFoundException;
import ru.altsora.service.CategoryService;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.altsora.util.RetMessage.NOT_FOUND_CATEGORY_ID;
import static ru.altsora.util.RetMessage.NOT_FOUND_CATEGORY_NAME;

class CategoryControllerTest extends AbstractWebController {
    private static final String RET_CODE = "$.retCode";
    private static final String RET_MESSAGE = "$.retMessage";

    @MockBean
    private CategoryService categoryService;

    @Test
    @DisplayName("Поиск категории по ID: категория найдена")
    void findById_found() throws Exception {
        final long id = 1L;
        final String name = "name";
        final CategoryDto categoryDto = CategoryDto.builder().id(id).name(name).build();
        doReturn(categoryDto).when(categoryService).findById(id);

        mockMvc.perform(get("/item/category/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(id), Long.class))
                .andExpect(jsonPath("$.name", notNullValue()))
                .andExpect(jsonPath("$.name", is(name)))
        ;
    }

    @Test
    @DisplayName("Поиск категории по ID: категория не найдена")
    void findById_notFound() throws Exception {
        final long id = 1L;
        final DomainNotFoundException ex = new DomainNotFoundException(NOT_FOUND_CATEGORY_ID, id);
        doThrow(ex).when(categoryService).findById(id);

        mockMvc.perform(get("/item/category/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(RET_CODE, notNullValue()))
                .andExpect(jsonPath(RET_MESSAGE, notNullValue()))
        ;
    }

    @Test
    @DisplayName("Поиск категории по имени: категория найдена")
    void findByName_found() throws Exception {
        final long id = 1L;
        final String name = "categoryName";
        final CategoryDto categoryDto = CategoryDto.builder().id(id).name(name).build();
        doReturn(categoryDto).when(categoryService).findByName(name);

        mockMvc.perform(get("/item/category/find").param("name", name))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(id), Long.class))
                .andExpect(jsonPath("$.name", notNullValue()))
                .andExpect(jsonPath("$.name", is(name)))
        ;
    }

    @Test
    @DisplayName("Поиск категории по имени: категория не найдена")
    void findByName_notFound() throws Exception {
        final String name = "categoryName";
        final DomainNotFoundException ex = new DomainNotFoundException(NOT_FOUND_CATEGORY_NAME, name);
        doThrow(ex).when(categoryService).findByName(name);

        mockMvc.perform(get("/item/category/find").param("name", name))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(RET_CODE, notNullValue()))
                .andExpect(jsonPath(RET_MESSAGE, notNullValue()))
        ;
    }

    @Test
    @DisplayName("Найти все категории: список не пуст")
    void findAll_notEmpty() throws Exception {
        final long id1 = 1;
        final String name1 = "name1";
        final CategoryDto categoryDto1 = CategoryDto.builder().id(id1).name(name1).build();
        final long id2 = 2;
        final String name2 = "name2";
        final CategoryDto categoryDto2 = CategoryDto.builder().id(id2).name(name2).build();
        final List<CategoryDto> listReturn = List.of(categoryDto1, categoryDto2);
        doReturn(listReturn).when(categoryService).findAll();

        mockMvc.perform(get("/item/category"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", notNullValue()))
                .andExpect(jsonPath("$[0].id", is(id1), Long.class))
                .andExpect(jsonPath("$[0].name", notNullValue()))
                .andExpect(jsonPath("$[0].name", is(name1)))
                .andExpect(jsonPath("$[1].id", notNullValue()))
                .andExpect(jsonPath("$[1].id", is(id2), Long.class))
                .andExpect(jsonPath("$[1].name", notNullValue()))
                .andExpect(jsonPath("$[1].name", is(name2)))
        ;
    }

    @Test
    @DisplayName("Найти все категории: список пуст")
    void findALl_empty() throws Exception {
        doReturn(emptyList()).when(categoryService).findAll();

        mockMvc.perform(get("/item/category"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(0)))
        ;
    }
}