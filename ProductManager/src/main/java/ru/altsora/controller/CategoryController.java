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

import javax.validation.Valid;
import java.util.List;

import static ru.altsora.util.SwaggerConstants.DESC_CATEGORY;
import static ru.altsora.util.SwaggerConstants.TAG_CATEGORY;

@RestController
@RequestMapping("/item/category")
@Tag(name = TAG_CATEGORY, description = DESC_CATEGORY)
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Поиск всех категорий", response = CategoryDto.class, responseContainer = "List", tags = TAG_CATEGORY)
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Найти категорию по ID", response = CategoryDto.class, tags = TAG_CATEGORY)
    public CategoryDto findById(@PathVariable("id") long id) {
        return categoryService.findById(id);
    }

    @GetMapping("/find")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Найти категорию по имени", response = CategoryDto.class, tags = TAG_CATEGORY)
    public CategoryDto findByName(@RequestParam("name") String name) {
        return categoryService.findByName(name);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    @ApiOperation(value = "Добавить новую категорию", response = CategoryAddOut.class, tags = TAG_CATEGORY)
    public CategoryAddOut add(
            @ApiParam(name = "addIn", required = true) @Valid @RequestBody CategoryAddIn addIn
    ) {
        return categoryService.add(addIn);
    }
}
