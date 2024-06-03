package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.ProductCondition;

@Getter
@Setter
public class ProductDTO {
    private String name;
    private String description;
    private Double price;
    private Double originalPrice;
    private String imageUrl;
    private ProductCondition condition;
    private Integer discount;
    private Integer statistics;

}
