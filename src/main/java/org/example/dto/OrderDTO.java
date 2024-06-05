package org.example.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.enums.OrderStatus;

@Getter
@Setter
public class OrderDTO {
    private Integer id;
    private String productName;
    private Integer quantity;
    private OrderStatus orderStatus;
}
