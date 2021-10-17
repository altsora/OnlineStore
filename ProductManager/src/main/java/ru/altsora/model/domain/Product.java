package ru.altsora.model.domain;

import lombok.*;

import javax.persistence.*;
import java.util.*;

/**
 * Доменная сущность "Товар".
 */
@Entity
@Table(name = "product")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    /**
     * Суррогатный первичный ключ.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", allocationSize = 1)
    @Column(name = "id")
    @EqualsAndHashCode.Include
    private long id;
    /**
     * Название товара.
     */
    @Column(name = "name")
    @EqualsAndHashCode.Include
    private String name;
    /**
     * Цена товара.
     */
    @Column(name = "price")
    @EqualsAndHashCode.Exclude
    private double price;
    /**
     * Флаг доступности товара.
     */
    @Column(name = "is_available")
    @EqualsAndHashCode.Exclude
    private boolean isAvailable;
    /**
     * Описание товара.
     */
    @Column(name = "description")
    @EqualsAndHashCode.Exclude
    private String description;

    /**
     * Подкатегории товара.
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "subcategory_product_link", joinColumns = @JoinColumn(name = "product_id"))
    @Column(name = "subcategory_id")
    @EqualsAndHashCode.Exclude
    private Set<Long> subcategories = new HashSet<>();

    public Product addSubcategories(final Collection<Long> subcategories) {
        this.subcategories.addAll(subcategories);
        return this;
    }

    public Product deleteSubcategories(final Collection<Long> subcategories) {
        this.subcategories.removeAll(subcategories);
        return this;
    }
}
