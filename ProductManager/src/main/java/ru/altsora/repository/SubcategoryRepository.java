package ru.altsora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.altsora.model.domain.Subcategory;

import java.util.List;
import java.util.Optional;

/**
 * Репозиторий сущности {@link Subcategory}.
 */
@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Subcategory s WHERE s.category.id = :categoryId " +
            "AND s.name = :name")
    boolean existsByCategoryAndName(
            @Param("categoryId") long categoryId,
            @Param("name") String name
    );

    @Query("SELECT s FROM Subcategory s WHERE s.category.id = :categoryId AND s.name = :name")
    Optional<Subcategory> findByCategoryAndName(
            @Param("categoryId") long categoryId,
            @Param("name") String name
    );

    @Query("SELECT s FROM Subcategory s WHERE s.category.id = :categoryId")
    List<Subcategory> findAllByCategory(@Param("categoryId") long categoryId);
}
