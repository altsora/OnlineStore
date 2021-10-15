package ru.altsora.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.altsora.dto.CategoryDto;
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
}
