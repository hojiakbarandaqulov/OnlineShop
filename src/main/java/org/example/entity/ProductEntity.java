package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.enums.ProductCondition;


@Getter
@Setter
@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "description",columnDefinition = "text")
    private String description;
    @Column(name = "price")
    private Double price;
    @Column(name = "original_price")
    private Double originalPrice;
    @Column(name = "image_url", columnDefinition = "text")
    private String imageUrl;
    @Column(name = "condition")
    private ProductCondition condition;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "statistics")
    private Integer statistics;

    @Column(name = "visible")
    private Boolean visible=Boolean.TRUE;


}
