package org.example.dto.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import org.example.enums.ProductCondition;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductFilterDTO {
    @NotBlank(message = "name required")
    private String name;
    @NotBlank(message = "description required")
    private String description;
    @NotBlank(message = "condition required")
    private ProductCondition condition;
    @NotBlank(message = "email required")
    private String email;
    @NotBlank(message = "discount required")
    private Integer discount;
    @NotBlank(message = "statistics required")
    private Integer statistics;
    @NotBlank(message = "priceFrom required")
    private Double priceFrom;
    @NotBlank(message = "priceTo required")
    private Double priceTo;
}
