package ru.altsora.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.altsora.model.domain.Storage;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Репозиторий сущности {@link Storage}.
 */
@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {
    @Query("SELECT s FROM Storage s WHERE s.productId = :id")
    Optional<Storage> findByProductId(@Param("id") long productId);

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Storage s WHERE s.productId = :id")
    boolean productExists(@Param("id") long productId);

    @Query("SELECT s.amount FROM Storage s WHERE s.productId = :id")
    Optional<Integer> amountByProductId(@Param("id") long productId);

    @Transactional
    @Modifying
    @Query("UPDATE Storage s SET s.amount = 0 WHERE s.productId = :id")
    void removeAllByProductId(@Param("id") long productId);
}
