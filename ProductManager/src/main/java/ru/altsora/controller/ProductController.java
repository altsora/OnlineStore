package ru.altsora.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.altsora.model.dto.ProductDto;
import ru.altsora.model.request.ProductUpdateIn;
import ru.altsora.service.ProductService;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

import static ru.altsora.util.SwaggerConstants.DESC_PRODUCT;
import static ru.altsora.util.SwaggerConstants.TAG_PRODUCT;

@RestController
@RequestMapping("/item/product")
@Tag(name = TAG_PRODUCT, description = DESC_PRODUCT)
@Validated
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Найти товар по ID", response = ProductDto.class, tags = TAG_PRODUCT)
    public ProductDto findById(@PathVariable("id") long id) {
        return productService.findById(id);
    }

    @PutMapping("/{id}/available")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Сделать товар доступным", tags = TAG_PRODUCT)
    public void setAvailable(@PathVariable("id") long id) {
        productService.setAvailable(id);
    }

    @PutMapping("/{id}/no-available")
    @ApiOperation(value = "Сделать товар недоступным", tags = TAG_PRODUCT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setNoAvailable(@PathVariable("id") long id) {
        productService.setNoAvailable(id);
    }

    @PutMapping("/{id}/price/{price}")
    @ApiOperation(value = "Обновить цену товара", tags = TAG_PRODUCT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePrice(
            @PathVariable("id") long id,
            @PathVariable("price") @PositiveOrZero Double price
    ) {
        productService.updatePrice(id, price);
    }

    @PutMapping("/{id}/subcategory/add")
    @ApiOperation(value = "Добавить товар в указанные подкатегории", tags = TAG_PRODUCT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addSubcategories(
            @PathVariable("id") long id,
            @RequestParam("ids") List<Long> subcategoryIds
    ) {
        productService.addSubcategories(id, subcategoryIds);
    }

    @PutMapping("/{id}/subcategory/del")
    @ApiOperation(value = "Убрать товар из указанных подкатегорий", tags = TAG_PRODUCT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubcategories(
            @PathVariable("id") long id,
            @RequestParam("ids") List<Long> subcategoryIds
    ) {
        productService.deleteSubcategories(id, subcategoryIds);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Обновить товар", tags = TAG_PRODUCT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@RequestBody @Valid ProductUpdateIn updateIn) {
        productService.update(updateIn);
    }
}
