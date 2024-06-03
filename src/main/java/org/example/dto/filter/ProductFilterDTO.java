package org.example.dto.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.example.enums.ProductCondition;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductFilterDTO {

    private String name;
    private String description;
    private ProductCondition condition;
    private String email;
    private Integer discount;
    private Integer statistics;
    private Double priceFrom;
    private Double priceTo;
}
