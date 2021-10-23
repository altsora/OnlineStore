package ru.altsora.controller;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import ru.altsora.exception.DomainNotFoundException;
import ru.altsora.exception.InvalidDataException;
import ru.altsora.model.dto.CategoryDto;
import ru.altsora.model.request.CategoryAddIn;
import ru.altsora.model.response.CategoryAddOut;
import ru.altsora.service.CategoryService;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.altsora.util.RetMessage.*;

@DisplayName("Тестирование контроллера Категорий ")
class CategoryControllerTest extends AbstractWebController {
    @MockBean
    private CategoryService categoryService;

    @AfterEach
    void afterEach() {
        verifyNoMoreInteractions(categoryService);
    }

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
        verify(categoryService, times(1)).findById(id);
    }

    @Test
    @DisplayName("Поиск категории по ID: категория не найдена")
    void findById_notFound() throws Exception {
        final long id = 1L;
        final DomainNotFoundException ex = new DomainNotFoundException(CATEGORY_NOT_FOUND_ID, id);
        doThrow(ex).when(categoryService).findById(id);

        mockMvc.perform(get("/item/category/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(RET_CODE, notNullValue()))
                .andExpect(jsonPath(ERROR_MESSAGES_LENGTH, is(1)))
                .andExpect(jsonPath(ERROR_MESSAGES.apply(0), notNullValue()))
        ;
        verify(categoryService, times(1)).findById(id);
    }

    @Test
    @DisplayName("Поиск категории по имени: категория найдена")
    void findByName_OK() throws Exception {
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
        verify(categoryService, times(1)).findByName(name);
    }

    @Test
    @DisplayName("Поиск категории по имени: категория не найдена")
    void findByName_notFound() throws Exception {
        final String name = "categoryName";
        final DomainNotFoundException ex = new DomainNotFoundException(CATEGORY_NOT_FOUND_NAME, name);
        doThrow(ex).when(categoryService).findByName(name);

        mockMvc.perform(get("/item/category/find").param("name", name))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(RET_CODE, notNullValue()))
                .andExpect(jsonPath(ERROR_MESSAGES_LENGTH, is(1)))
                .andExpect(jsonPath(ERROR_MESSAGES.apply(0), notNullValue()))
        ;
        verify(categoryService, times(1)).findByName(name);
    }

    @Test
    @DisplayName("Найти все категории: список не пуст")
    void findAll_OK() throws Exception {
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
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].id", notNullValue()))
                .andExpect(jsonPath("$[0].id", is(id1), Long.class))
                .andExpect(jsonPath("$[0].name", notNullValue()))
                .andExpect(jsonPath("$[0].name", is(name1)))
                .andExpect(jsonPath("$[1].id", notNullValue()))
                .andExpect(jsonPath("$[1].id", is(id2), Long.class))
                .andExpect(jsonPath("$[1].name", notNullValue()))
                .andExpect(jsonPath("$[1].name", is(name2)))
        ;
        verify(categoryService, times(1)).findAll();
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
        verify(categoryService, times(1)).findAll();
    }

    @Test
    @DisplayName("Создание категории: успешно")
    void addCategory_OK() throws Exception {
        final long id = 1;
        final String name = "categoryName";
        final CategoryAddIn addIn = CategoryAddIn.builder().name(name).build();
        final CategoryAddOut addOut = CategoryAddOut.builder().id(id).name(name).build();

        doReturn(addOut).when(categoryService).add(addIn);

        mockMvc.perform(post("/item/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonConverter.toJson(addIn)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(id), Long.class))
                .andExpect(jsonPath("$.name", is(name)))
        ;

        verify(categoryService, times(1)).add(addIn);
    }

    @Test
    @DisplayName("Создание категории: пустое имя")
    void addCategory_emptyName() throws Exception {
        final String name = Strings.EMPTY;
        final CategoryAddIn addIn = CategoryAddIn.builder().name(name).build();
        final InvalidDataException ex = new InvalidDataException(CATEGORY_EMPTY_NAME);
        doThrow(ex).when(categoryService).add(addIn);

        mockMvc.perform(post("/item/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonConverter.toJson(addIn)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(RET_CODE, notNullValue()))
                .andExpect(jsonPath(ERROR_MESSAGES_LENGTH, is(1)))
                .andExpect(jsonPath(ERROR_MESSAGES.apply(0), notNullValue()))
        ;

        verify(categoryService, times(0)).add(addIn);
    }

    @Test
    @DisplayName("Создание категории: имя уже существует")
    void addCategory_nameExists() throws Exception {
        final String name = "categoryName";
        final CategoryAddIn addIn = CategoryAddIn.builder().name(name).build();
        final InvalidDataException ex = new InvalidDataException(String.format(CATEGORY_EXISTS_NAME, name));
        doThrow(ex).when(categoryService).add(addIn);

        mockMvc.perform(post("/item/category")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonConverter.toJson(addIn)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(RET_CODE, notNullValue()))
                .andExpect(jsonPath(ERROR_MESSAGES_LENGTH, is(1)))
                .andExpect(jsonPath(ERROR_MESSAGES.apply(0), notNullValue()))
        ;

        verify(categoryService, times(1)).add(addIn);
    }
}