package ru.altsora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.altsora.model.domain.Product;

import javax.transaction.Transactional;

/**
 * Репозиторий сущности {@link Product}.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.isAvailable = true WHERE p.id = :id")
    void setAvailable(@Param("id") long id);

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.price = :price WHERE p.id = :id")
    void updatePrice(@Param("id") long id, @Param("price") double price);
}
