package ru.altsora.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.altsora.model.dto.CategoryDto;
import ru.altsora.model.request.CategoryAddIn;
import ru.altsora.model.response.CategoryAddOut;
import ru.altsora.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/item/category")
@Tag(name = "Категория", description = "Работа с категорией товаров")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ApiOperation(value = "Поиск всех категорий", response = CategoryDto.class, responseContainer = "List", tags = "Категория")
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Найти категорию по ID", response = CategoryDto.class, tags = "Категория")
    public CategoryDto findById(@PathVariable("id") long id) {
        return categoryService.findById(id);
    }

    @GetMapping("/find")
    @ApiOperation(value = "Найти категорию по имени", response = CategoryDto.class, tags = "Категория")
    @ResponseStatus(value = HttpStatus.OK)
    public CategoryDto findByName(@RequestParam("name") String name) {
        return categoryService.findByName(name);
    }

    @PostMapping
    @ApiOperation(value = "Добавить новую категорию", response = CategoryAddOut.class, tags = "Категория")
    @ResponseStatus(value = HttpStatus.CREATED)
    public CategoryAddOut add(
            @ApiParam(name = "addIn", required = true) @RequestBody CategoryAddIn addIn
    ) {
        return categoryService.add(addIn);
    }
}
