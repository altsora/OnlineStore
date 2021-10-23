package ru.altsora.controller;

import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import ru.altsora.exception.DomainNotFoundException;
import ru.altsora.exception.InvalidDataException;
import ru.altsora.model.dto.SubcategoryDto;
import ru.altsora.model.request.SubcategoryAddIn;
import ru.altsora.service.SubcategoryService;

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

@DisplayName("Тестирование контроллера Подкатегорий ")
class SubcategoryControllerTest extends AbstractWebController {
    @MockBean
    private SubcategoryService subcategoryService;

    @AfterEach
    void afterEach() {
        verifyNoMoreInteractions(subcategoryService);
    }

    @Test
    @DisplayName("Поиск подкатегории по имени и ID категории: успешно")
    void findByCategoryAndName_OK() throws Exception {
        final long id = 1;
        final long categoryId = 1;
        final String name = "name";
        final SubcategoryDto dto = SubcategoryDto.builder().id(id).categoryId(categoryId).name(name).build();
        doReturn(dto).when(subcategoryService).findByCategoryAndName(categoryId, name);

        mockMvc.perform(get("/item/subcategory/find")
                .param("categoryId", String.valueOf(categoryId))
                .param("name", name))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.id", is(id), Long.class))
                .andExpect(jsonPath("$.categoryId", notNullValue()))
                .andExpect(jsonPath("$.categoryId", is(categoryId), Long.class))
                .andExpect(jsonPath("$.name", notNullValue()))
                .andExpect(jsonPath("$.name", is(name)))
        ;
        verify(subcategoryService, times(1)).findByCategoryAndName(categoryId, name);
    }

    @Test
    @DisplayName("Поиск подкатегории по имени и ID категории: не найдена")
    void findByCategoryAndName_notFound() throws Exception {
        final long categoryId = 1;
        final String name = "name";
        final DomainNotFoundException ex = new DomainNotFoundException(SUBCATEGORY_NOT_FOUND, name, categoryId);
        doThrow(ex).when(subcategoryService).findByCategoryAndName(categoryId, name);

        mockMvc.perform(get("/item/subcategory/find")
                .param("categoryId", String.valueOf(categoryId))
                .param("name", name))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(RET_CODE, notNullValue()))
                .andExpect(jsonPath(ERROR_MESSAGES_LENGTH, is(1)))
                .andExpect(jsonPath(ERROR_MESSAGES.apply(0), notNullValue()))
        ;
        verify(subcategoryService, times(1)).findByCategoryAndName(categoryId, name);
    }

    @Test
    @DisplayName("Поиск подкатегорий по категории: список не пустой")
    void findAllByCategory_OK() throws Exception {
        final long categoryId = 1;
        final long id1 = 1;
        final String name1 = "name1";
        final SubcategoryDto dto1 = SubcategoryDto.builder().id(id1).categoryId(categoryId).name(name1).build();
        final long id2 = 2;
        final String name2 = "name2";
        final SubcategoryDto dto2 = SubcategoryDto.builder().id(id2).categoryId(categoryId).name(name2).build();
        final List<SubcategoryDto> returnList = List.of(dto1, dto2);
        doReturn(returnList).when(subcategoryService).findAllByCategory(categoryId);

        mockMvc.perform(get("/item/subcategory/all").param("categoryId", String.valueOf(categoryId)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].id", notNullValue()))
                .andExpect(jsonPath("$[0].id", is(id1), Long.class))
                .andExpect(jsonPath("$[0].categoryId", notNullValue()))
                .andExpect(jsonPath("$[0].categoryId", is(categoryId), Long.class))
                .andExpect(jsonPath("$[0].name", notNullValue()))
                .andExpect(jsonPath("$[0].name", is(name1)))
                .andExpect(jsonPath("$[1].id", notNullValue()))
                .andExpect(jsonPath("$[1].id", is(id2), Long.class))
                .andExpect(jsonPath("$[1].categoryId", notNullValue()))
                .andExpect(jsonPath("$[1].categoryId", is(categoryId), Long.class))
                .andExpect(jsonPath("$[1].name", notNullValue()))
                .andExpect(jsonPath("$[1].name", is(name2)))
        ;
        verify(subcategoryService, times(1)).findAllByCategory(categoryId);
    }

    @Test
    @DisplayName("Поиск подкатегорий по категории: список пустой")
    void findAllByCategory_empty() throws Exception {
        final long categoryId = 1;
        doReturn(emptyList()).when(subcategoryService).findAllByCategory(categoryId);

        mockMvc.perform(get("/item/subcategory/all").param("categoryId", String.valueOf(categoryId)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", is(0)))
        ;
        verify(subcategoryService, times(1)).findAllByCategory(categoryId);
    }

    @Test
    @DisplayName("Поиск подкатегории по ID: успешно")
    void findById_OK() throws Exception {
        final long id = 1;
        final long categoryId = 1;
        final String name = "name";
        final SubcategoryDto dto = SubcategoryDto.builder().id(id).categoryId(categoryId).name(name).build();
        doReturn(dto).when(subcategoryService).findById(id);

        mockMvc.perform(get("/item/subcategory/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.id", is(id), Long.class))
                .andExpect(jsonPath("$.categoryId", notNullValue()))
                .andExpect(jsonPath("$.categoryId", is(categoryId), Long.class))
                .andExpect(jsonPath("$.name", notNullValue()))
                .andExpect(jsonPath("$.name", is(name)))
        ;
        verify(subcategoryService, times(1)).findById(id);
    }

    @Test
    @DisplayName("Поиск подкатегории по ID: не найдена")
    void findById_notFound() throws Exception {
        final long id = 1;
        final DomainNotFoundException ex = new DomainNotFoundException(SUBCATEGORY_NOT_FOUND_ID, id);
        doThrow(ex).when(subcategoryService).findById(id);

        mockMvc.perform(get("/item/subcategory/{id}", id))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath(RET_CODE, notNullValue()))
                .andExpect(jsonPath(ERROR_MESSAGES_LENGTH, is(1)))
                .andExpect(jsonPath(ERROR_MESSAGES.apply(0), notNullValue()))
        ;
        verify(subcategoryService, times(1)).findById(id);
    }

    @Test
    @DisplayName("Создание подкатегории: успешно")
    void add_OK() throws Exception {
        final long id = 1;
        final long categoryId = 1;
        final String name = "name";
        final SubcategoryAddIn addIn = SubcategoryAddIn.builder().categoryId(categoryId).name(name).build();
        final SubcategoryDto addOut = SubcategoryDto.builder().id(id).categoryId(categoryId).name(name).build();
        doReturn(addOut).when(subcategoryService).add(addIn);

        mockMvc.perform(post("/item/subcategory")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonConverter.toJson(addIn)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.id", is(id), Long.class))
                .andExpect(jsonPath("$.categoryId", notNullValue()))
                .andExpect(jsonPath("$.categoryId", is(categoryId), Long.class))
                .andExpect(jsonPath("$.name", notNullValue()))
                .andExpect(jsonPath("$.name", is(name)))
        ;
        verify(subcategoryService, times(1)).add(addIn);
    }

    @Test
    @DisplayName("Создание подкатегории: пустое имя подкатегории")
    void add_emptyName() throws Exception {
        final long categoryId = 1;
        final String name = Strings.EMPTY;
        final SubcategoryAddIn addIn = SubcategoryAddIn.builder().categoryId(categoryId).name(name).build();
        final InvalidDataException ex = new InvalidDataException(SUBCATEGORY_EMPTY_NAME);
        doThrow(ex).when(subcategoryService).add(addIn);

        mockMvc.perform(post("/item/subcategory")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonConverter.toJson(addIn)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(RET_CODE, notNullValue()))
                .andExpect(jsonPath(ERROR_MESSAGES_LENGTH, is(1)))
                .andExpect(jsonPath(ERROR_MESSAGES.apply(0), notNullValue()))
        ;
        verify(subcategoryService, times(0)).add(addIn);
    }

    @Test
    @DisplayName("Создание подкатегории: категория не существует")
    void add_categoryNotFound() throws Exception {
        final long categoryId = 1;
        final String name = "name";
        final SubcategoryAddIn addIn = SubcategoryAddIn.builder().categoryId(categoryId).name(name).build();
        final DomainNotFoundException ex = new DomainNotFoundException(CATEGORY_NOT_FOUND_ID, categoryId);
        doThrow(ex).when(subcategoryService).add(addIn);

        mockMvc.perform(post("/item/subcategory")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonConverter.toJson(addIn)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath(RET_CODE, notNullValue()))
                .andExpect(jsonPath(ERROR_MESSAGES_LENGTH, is(1)))
                .andExpect(jsonPath(ERROR_MESSAGES.apply(0), notNullValue()))
        ;
        verify(subcategoryService, times(1)).add(addIn);
    }

    @Test
    @DisplayName("Создание подкатегории: подкатегория уже существует")
    void add_alreadyExists() throws Exception {
        final long categoryId = 1;
        final String name = "name";
        final SubcategoryAddIn addIn = SubcategoryAddIn.builder().categoryId(categoryId).name(name).build();
        final InvalidDataException ex = new InvalidDataException(SUBCATEGORY_EXISTS, name, categoryId);
        doThrow(ex).when(subcategoryService).add(addIn);

        mockMvc.perform(post("/item/subcategory")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonConverter.toJson(addIn)))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath(RET_CODE, notNullValue()))
                .andExpect(jsonPath(ERROR_MESSAGES_LENGTH, is(1)))
                .andExpect(jsonPath(ERROR_MESSAGES.apply(0), notNullValue()))
        ;
        verify(subcategoryService, times(1)).add(addIn);
    }
}