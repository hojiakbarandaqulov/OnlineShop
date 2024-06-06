package org.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.example.enums.ProductCondition;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
    @NotBlank(message = "name required")
    private String name;
    @NotBlank(message = "description required")
    private String description;
    @NotBlank(message = "price required")
    private Double price;
    @NotBlank(message = "originalPrice required")
    private Double originalPrice;
    @NotBlank(message = "imageUrl required")
    private String imageUrl;
    @NotBlank(message = "condition required")
    private ProductCondition condition;
    @NotBlank(message = "discount required")
    private Integer discount;
    @NotBlank(message = "statistics required")
    private Integer statistics;

}
