package ru.altsora.model.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Доменная сущность "Склад товаров".
 */
@Entity
@Table(name = "storage")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Storage {
    /**
     * Товар на складе.
     */
    @Id
    @Column(name = "product_id")
    @EqualsAndHashCode.Include
    private long productId;

    /**
     * Количество товара на складе.
     */
    @Column(name = "amount")
    private int amount;
}
