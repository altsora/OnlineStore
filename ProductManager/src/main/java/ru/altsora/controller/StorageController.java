package ru.altsora.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.altsora.model.response.ProductStorageAvailable;
import ru.altsora.model.response.ProductStorageUpdate;
import ru.altsora.service.StorageService;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import static ru.altsora.util.SwaggerConstants.DESC_STORAGE;
import static ru.altsora.util.SwaggerConstants.TAG_STORAGE;

@RestController
@RequestMapping("/item/storage")
@Tag(name = TAG_STORAGE, description = DESC_STORAGE)
@Validated
public class StorageController {
    private final StorageService storageService;

    @Autowired
    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/{id}/info")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Информация по товару на складе", response = ProductStorageAvailable.class, tags = TAG_STORAGE)
    public ProductStorageAvailable productInfo(@PathVariable("id") long productId) {
        return storageService.productInfo(productId);
    }

    @PostMapping("/{id}/plus/{amount}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Увеличить количество товара на складе", response = ProductStorageUpdate.class, tags = TAG_STORAGE)
    public ProductStorageUpdate plus(
            @PathVariable("id") long productId,
            @PathVariable("amount") @NotNull @Positive(message = "Количество товара должно быть больше 0") Integer amount
    ) {
        return storageService.plus(productId, amount);
    }

    @PostMapping("/{id}/minus/{amount}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Уменьшить количество товара на складе", response = ProductStorageUpdate.class, tags = TAG_STORAGE)
    public ProductStorageUpdate minus(
            @PathVariable("id") long productId,
            @PathVariable("amount") @NotNull @Positive(message = "Количество товара должно быть больше 0") Integer amount
    ) {
        return storageService.minus(productId, amount);
    }

    @PostMapping("/{id}/remove")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Убрать весь указанный товар со склада", tags = TAG_STORAGE)
    public void removeAll(@PathVariable("id") long productId) {
        storageService.removeAll(productId);
    }
}
