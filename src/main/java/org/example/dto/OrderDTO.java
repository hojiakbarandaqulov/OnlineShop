package org.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.example.enums.OrderStatus;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    @NotBlank(message = "id required")
    private Integer id;
    @NotBlank(message = "productName required")
    private String productName;
    @NotBlank(message = "quantity required")
    private Integer quantity;
    @NotBlank(message = "orderStatus required")
    private OrderStatus orderStatus;
}
