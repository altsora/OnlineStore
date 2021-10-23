package ru.altsora.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.altsora.model.dto.SubcategoryDto;
import ru.altsora.model.request.SubcategoryAddIn;
import ru.altsora.service.SubcategoryService;

import javax.validation.Valid;
import java.util.List;

import static ru.altsora.util.SwaggerConstants.DESC_SUBCATEGORY;
import static ru.altsora.util.SwaggerConstants.TAG_SUBCATEGORY;

@RestController
@RequestMapping("/item/subcategory")
@Tag(name = TAG_SUBCATEGORY, description = DESC_SUBCATEGORY)
public class SubcategoryController {
    private final SubcategoryService subcategoryService;

    @Autowired
    public SubcategoryController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @GetMapping("/find")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Поиск подкатегории по её имени и по имени категории", response = SubcategoryDto.class, tags = TAG_SUBCATEGORY)
    public SubcategoryDto findByCategoryAndName(
            @RequestParam("categoryId") long categoryId,
            @RequestParam("name") String name
    ) {
        return subcategoryService.findByCategoryAndName(categoryId, name);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Поиск всех подкатегорий по имени категории", response = SubcategoryDto.class, responseContainer = "List", tags = TAG_SUBCATEGORY)
    public List<SubcategoryDto> findAllByCategory(@RequestParam("categoryId") long categoryId) {
        return subcategoryService.findAllByCategory(categoryId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Поиск подкатегории по её ID", response = SubcategoryDto.class, tags = TAG_SUBCATEGORY)
    public SubcategoryDto findById(@PathVariable("id") long id) {
        return subcategoryService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Добавить новую подкатегорию", response = SubcategoryDto.class, tags = TAG_SUBCATEGORY)
    public SubcategoryDto add(@RequestBody @Valid SubcategoryAddIn addIn) {
        return subcategoryService.add(addIn);
    }
}
