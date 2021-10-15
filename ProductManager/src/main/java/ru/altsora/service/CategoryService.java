package ru.altsora.service;

import ru.altsora.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> findAll();

    CategoryDto findById(long id);

    CategoryDto findByName(String name);
}
