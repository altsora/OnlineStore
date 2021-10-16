package ru.altsora.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.altsora.exception.DomainNotFoundException;
import ru.altsora.exception.InvalidDataException;
import ru.altsora.model.domain.Category;
import ru.altsora.model.domain.Subcategory;
import ru.altsora.model.dto.SubcategoryDto;
import ru.altsora.model.request.SubcategoryAddIn;
import ru.altsora.repository.CategoryRepository;
import ru.altsora.repository.SubcategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.altsora.util.Functions.SUBCATEGORY_DOMAIN_TO_DTO;
import static ru.altsora.util.RetMessage.*;

@Primary
@Service
@RequiredArgsConstructor
public class SubcategoryServiceImpl implements SubcategoryService {
    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public SubcategoryDto findByCategoryAndName(final long categoryId, final String name) {
        return subcategoryRepository.findByCategoryAndName(categoryId, name)
                .map(SUBCATEGORY_DOMAIN_TO_DTO)
                .orElseThrow(() -> new DomainNotFoundException(SUBCATEGORY_NOT_FOUND, name, categoryId));
    }

    @Override
    public List<SubcategoryDto> findAllByCategory(final long categoryId) {
        return subcategoryRepository.findAllByCategory(categoryId)
                .stream()
                .map(SUBCATEGORY_DOMAIN_TO_DTO)
                .collect(Collectors.toList());
    }

    @Override
    public SubcategoryDto findById(final long id) {
        return subcategoryRepository.findById(id)
                .map(SUBCATEGORY_DOMAIN_TO_DTO)
                .orElseThrow(() -> new DomainNotFoundException(SUBCATEGORY_NOT_FOUND_ID, id));
    }

    @Override
    public SubcategoryDto add(final SubcategoryAddIn addIn) {
        final String name = addIn.getName();
        if (Strings.isEmpty(name)) {
            throw new InvalidDataException(SUBCATEGORY_EMPTY_NAME);
        }

        final long categoryId = addIn.getCategoryId();
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isEmpty()) {
            throw new DomainNotFoundException(CATEGORY_NOT_FOUND_ID, categoryId);
        }

        if (subcategoryRepository.existsByCategoryAndName(categoryId, name)) {
            throw new InvalidDataException(SUBCATEGORY_EXISTS, name, categoryId);
        }

        final Subcategory subcategory = Subcategory.builder()
                .category(categoryOptional.get())
                .name(name)
                .build();
        final Subcategory save = subcategoryRepository.saveAndFlush(subcategory);
        return SUBCATEGORY_DOMAIN_TO_DTO.apply(save);
    }


}
