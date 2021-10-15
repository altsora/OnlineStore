package ru.altsora.service;

import ru.altsora.dto.CategoryDto;
import ru.altsora.dto.request.CategoryAddIn;
import ru.altsora.dto.response.CategoryAddOut;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();

    CategoryDto findById(long id);

    CategoryDto findByName(String name);

    CategoryAddOut add(CategoryAddIn addIn);
}
