package ru.altsora.service;

import ru.altsora.model.dto.SubcategoryDto;
import ru.altsora.model.request.SubcategoryAddIn;

import java.util.List;

public interface SubcategoryService {
    SubcategoryDto findByCategoryAndName(long categoryId, String name);

    List<SubcategoryDto> findAllByCategory(long categoryId);

    SubcategoryDto add(SubcategoryAddIn addIn);

    SubcategoryDto findById(long id);
}
