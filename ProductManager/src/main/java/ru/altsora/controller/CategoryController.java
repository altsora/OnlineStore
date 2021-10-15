package ru.altsora.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.altsora.dto.CategoryDto;
import ru.altsora.dto.request.CategoryAddIn;
import ru.altsora.dto.response.CategoryAddOut;
import ru.altsora.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/item")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    @ApiOperation(value = "Поиск всех категорий", response = CategoryDto.class, responseContainer = "List")
    public List<CategoryDto> findAll() {
        return categoryService.findAll();
    }

    @GetMapping("/category/{id}")
    public CategoryDto findById(@PathVariable("id") long id) {
        return categoryService.findById(id);
    }

    @GetMapping("/category/find")
    public CategoryDto findByName(@RequestParam("name") String name) {
        return categoryService.findByName(name);
    }

    @PostMapping("/category")
    @ApiOperation(value = "Добавить новую категорию", response = CategoryAddOut.class)
    @ResponseStatus(value = HttpStatus.CREATED)
    public CategoryAddOut add(
            @ApiParam(name = "addIn", required = true) @RequestBody CategoryAddIn addIn
    ) {
        return categoryService.add(addIn);
    }
}
