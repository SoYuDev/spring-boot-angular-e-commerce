package com.luis.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "product_category")
//@Data el uso de @Data puede dar un bug al usar anotaciones como @OneToMany y @ManyToOne
@Getter
@Setter
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    /*   CascadeType.ALL hace que si modificamos la categoría (por ejemplo, borrarla) también se modifiquen
         los productos asociados a ella (se borraría la categoria de los productos) */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    private Set<Product> products;
}
