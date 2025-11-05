package com.luis.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "product")
@Data // Generates Getters & Setters
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id") // Nombramos a todas las columnas de la misma manera que lo tenemos en nuestra DB.
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id") //FK
    private ProductCategory category;

    @Column(name = "sku")
    private String sku;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "active")
    private boolean active;

    @Column(name = "units_in_stock")
    private int unitsInStock;

    @Column(name = "date_created")
    @CreationTimestamp // Hibernate se encarga automáticamente de lidiar con los TimeStamps
    private Date dateCreated;

    @Column(name = "last_updated")
    @UpdateTimestamp
    private Date dateUpdated;
}
