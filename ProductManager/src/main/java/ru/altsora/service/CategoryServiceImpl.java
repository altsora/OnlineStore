package ru.altsora.service;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.altsora.dto.CategoryDto;
import ru.altsora.exception.DomainNotFoundException;
import ru.altsora.repository.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

import static ru.altsora.util.Functions.CATEGORY_DOMAIN_TO_DTO;
import static ru.altsora.util.RetMessage.NOT_FOUND_CATEGORY_ID;
import static ru.altsora.util.RetMessage.NOT_FOUND_CATEGORY_NAME;

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
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(final long id) {
        return categoryRepository.findById(id)
                .map(CATEGORY_DOMAIN_TO_DTO)
                .orElseThrow(() -> new DomainNotFoundException(NOT_FOUND_CATEGORY_ID, id));
    }

    @Override
    public CategoryDto findByName(final String name) {
        return categoryRepository.findByName(name)
                .map(CATEGORY_DOMAIN_TO_DTO)
                .orElseThrow(() -> new DomainNotFoundException(NOT_FOUND_CATEGORY_NAME, name));
    }

}
