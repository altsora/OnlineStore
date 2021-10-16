package ru.altsora.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.altsora.model.dto.SubcategoryDto;
import ru.altsora.model.request.SubcategoryAddIn;
import ru.altsora.service.SubcategoryService;

import java.util.List;

@RestController
@RequestMapping("/item/subcategory")
public class SubcategoryController {
    private final SubcategoryService subcategoryService;

    @Autowired
    public SubcategoryController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @GetMapping("/find")
    public SubcategoryDto findByCategoryAndName(
            @RequestParam("categoryId") long categoryId,
            @RequestParam("name") String name
    ) {
        return subcategoryService.findByCategoryAndName(categoryId, name);
    }

    @GetMapping("/all")
    public List<SubcategoryDto> findAllByCategory(@RequestParam("categoryId") long categoryId) {
        return subcategoryService.findAllByCategory(categoryId);
    }

    @GetMapping("/{id}")
    public SubcategoryDto findById(@PathVariable("id") long id) {
        return subcategoryService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubcategoryDto add(@RequestBody SubcategoryAddIn addIn) {
        return subcategoryService.add(addIn);
    }
}
