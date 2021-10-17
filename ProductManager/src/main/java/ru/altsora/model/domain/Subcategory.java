package ru.altsora.model.domain;

import lombok.*;

import javax.persistence.*;

/**
 * Доменная сущность "Подкатегория товара".
 */
@Entity
@Table(name = "subcategory")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subcategory {
    /**
     * Суррогатный первичный ключ.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subcategory_id_seq")
    @SequenceGenerator(name = "subcategory_id_seq", sequenceName = "subcategory_id_seq", allocationSize = 1)
    @Column(name = "id")
    @EqualsAndHashCode.Exclude
    private long id;
    /**
     * Категория товара.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    @EqualsAndHashCode.Include
    private Category category;
    /**
     * Название подкатегории товара.
     */
    @Column(name = "name")
    @EqualsAndHashCode.Include
    private String name;
}
