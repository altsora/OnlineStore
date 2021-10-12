package ru.altsora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.altsora.domain.Category;

/**
 * Репозиторий сущности {@link Category}.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
