package com.inventory.pruebatecnica.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;


/**
 * Domain model representing a product in the inventory.
 *
 * <p>Contains basic product properties: identifier, name, price and stock quantity.</p>
 *
 * <p>Instances are mutable and intended for use with Spring Boot data-binding and
 * serialization frameworks.</p>
 */
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {

    /** Unique identifier of the product. May be {@code null} before persistence. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Human-readable name of the product. */
    @Column(nullable = false)
    private String name;

    /** Unit price of the product. Should be non-negative. */
    @Column(nullable = false)
    private BigDecimal price;

    /** Available stock quantity. Should be zero or positive. */
    @Column(nullable = false)
    private int stock;

}
