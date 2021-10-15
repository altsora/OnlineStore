package ru.altsora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.altsora.domain.Category;

import java.util.Optional;

/**
 * Репозиторий сущности {@link Category}.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}
