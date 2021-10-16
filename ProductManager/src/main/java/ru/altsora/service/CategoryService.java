package ru.altsora.service;

import ru.altsora.model.dto.CategoryDto;
import ru.altsora.model.request.CategoryAddIn;
import ru.altsora.model.response.CategoryAddOut;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();

    CategoryDto findById(long id);

    CategoryDto findByName(String name);

    CategoryAddOut add(CategoryAddIn addIn);
}
