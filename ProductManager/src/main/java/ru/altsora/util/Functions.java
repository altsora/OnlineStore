package ru.altsora.util;

import ru.altsora.domain.Category;
import ru.altsora.dto.CategoryDto;
import ru.altsora.dto.response.CategoryAddOut;

import java.util.function.Function;

/**
 * Утильный класс для функциональных интерфейсов
 */
public final class Functions {
    private Functions() {
        throw new IllegalStateException("Utility class");
    }

    public static final Function<Category, CategoryDto> CATEGORY_DOMAIN_TO_DTO = domain -> CategoryDto.builder()
            .id(domain.getId())
            .name(domain.getName())
            .build();

    public static final Function<CategoryDto, Category> CATEGORY_DTO_TO_DOMAIN = dto -> Category.builder()
            .id(dto.getId())
            .name(dto.getName())
            .build();

    public static final Function<Category, CategoryAddOut> CATEGORY_DOMAIN_TO_ADD_OUT = domain -> CategoryAddOut.builder()
            .id(domain.getId())
            .name(domain.getName())
            .build();

}
