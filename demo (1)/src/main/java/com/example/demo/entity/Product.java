package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "product_name", nullable = false, length = 80)
    private String name;

    @Column(name = "product_picture", nullable = true, columnDefinition = "VARCHAR(MAX)")
    private String picture;

    @Column(name = "product_price", nullable = false)
    private Float price;

    @Column(name = "product_status", nullable = false)
    private Boolean status;

    @Column(name = "product_size", length = 80, nullable = false)
    private String size;

    @ManyToOne()
    @JoinColumn(name = "category_id") // thong qua khoa ngoai category_id
    private Category category;

}
