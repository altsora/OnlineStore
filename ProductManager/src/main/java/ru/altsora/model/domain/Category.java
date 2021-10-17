package ru.altsora.model.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Доменная сущность "Категория товара".
 */
@Entity
@Table(name = "category")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    /**
     * Суррогатный первичный ключ.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_id_seq")
    @SequenceGenerator(name = "category_id_seq", sequenceName = "category_id_seq", allocationSize = 1)
    @Column(name = "id")
    @EqualsAndHashCode.Exclude
    private long id;
    /**
     * Название категории товара.
     */
    @Column(name = "name")
    @EqualsAndHashCode.Include
    private String name;
}
