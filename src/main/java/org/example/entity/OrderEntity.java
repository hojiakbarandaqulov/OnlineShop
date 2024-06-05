package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.enums.OrderStatus;

@Getter
@Setter
@Entity
@Table(name = "order_product")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "product_name",columnDefinition = "text")
    private String productName;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
