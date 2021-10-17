package ru.altsora.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.altsora.model.dto.ProductDto;
import ru.altsora.model.request.ProductUpdateIn;
import ru.altsora.service.ProductService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/item/product")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Найти товар по ID")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto findById(@PathVariable("id") long id) {
        return productService.findById(id);
    }

    @PutMapping("/{id}/available")
    @ApiOperation(value = "Сделать товар доступным")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setAvailable(@PathVariable("id") long id) {
        productService.setAvailable(id);
    }

    @PutMapping("/{id}/no-available")
    @ApiOperation(value = "Сделать товар недоступным")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void setNoAvailable(@PathVariable("id") long id) {
        productService.setNoAvailable(id);
    }

    @PutMapping("/{id}/price/{price}")
    @ApiOperation(value = "Обновить цену товара")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePrice(
            @PathVariable("id") long id,
            @PathVariable("price") double price
    ) {
        productService.updatePrice(id, price);
    }

    @PutMapping("/{id}/subcategory/add")
    @ApiOperation(value = "Добавить товар в указанные подкатегории")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addSubcategories(
            @PathVariable("id") long id,
            @RequestParam("ids") List<Long> subcategoryIds
    ) {
        productService.addSubcategories(id, subcategoryIds);
    }

    @PutMapping("/{id}/subcategory/del")
    @ApiOperation(value = "Убрать товар из указанных подкатегорий")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSubcategories(
            @PathVariable("id") long id,
            @RequestParam("ids") List<Long> subcategoryIds
    ) {
        productService.deleteSubcategories(id, subcategoryIds);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Обновить товар")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@Valid @RequestBody ProductUpdateIn updateIn) {
        productService.update(updateIn);
    }
}
