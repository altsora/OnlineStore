package ru.altsora.service;

import ru.altsora.model.dto.ProductDto;
import ru.altsora.model.request.ProductUpdateIn;

import java.util.Collection;

public interface ProductService {
    ProductDto findById(long id);

    void setAvailable(long id);

    void setNoAvailable(long id);

    void updatePrice(long id, Double price);

    void addSubcategories(long productId, Collection<Long> subcategoryIds);

    void deleteSubcategories(long productId, Collection<Long> subcategoryIds);

    void update(ProductUpdateIn in);
}
