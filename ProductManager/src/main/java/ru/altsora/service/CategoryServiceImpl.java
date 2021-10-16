package ru.altsora.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.altsora.domain.Category;
import ru.altsora.dto.CategoryDto;
import ru.altsora.dto.request.CategoryAddIn;
import ru.altsora.dto.response.CategoryAddOut;
import ru.altsora.exception.DomainNotFoundException;
import ru.altsora.exception.InvalidDataException;
import ru.altsora.repository.CategoryRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static ru.altsora.util.Functions.CATEGORY_DOMAIN_TO_ADD_OUT;
import static ru.altsora.util.Functions.CATEGORY_DOMAIN_TO_DTO;
import static ru.altsora.util.RetMessage.*;

@Primary
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final Logger logger = LogManager.getLogger(CategoryServiceImpl.class);

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryDto> findAll() {
        return categoryRepository.findAll()
                .stream()
                .map(CATEGORY_DOMAIN_TO_DTO)
                .sorted(Comparator.comparing(CategoryDto::getName))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(final long id) {
        return categoryRepository.findById(id)
                .map(CATEGORY_DOMAIN_TO_DTO)
                .orElseThrow(() -> new DomainNotFoundException(CATEGORY_NOT_FOUND_ID, id));
    }

    @Override
    public CategoryDto findByName(final String name) {
        return categoryRepository.findByName(name)
                .map(CATEGORY_DOMAIN_TO_DTO)
                .orElseThrow(() -> new DomainNotFoundException(CATEGORY_NOT_FOUND_NAME, name));
    }

    @Override
    public CategoryAddOut add(final CategoryAddIn addIn) {
        final String name = addIn.getName();

        if (Strings.isEmpty(name)) {
            throw new InvalidDataException(CATEGORY_EMPTY_NAME);
        }
        if (categoryRepository.existsByName(name)) {
            throw new InvalidDataException(String.format(CATEGORY_EXISTS_NAME, name));
        }

        final Category category = Category.builder().name(name).build();
        final Category save = categoryRepository.saveAndFlush(category);
        return CATEGORY_DOMAIN_TO_ADD_OUT.apply(save);
    }
}
