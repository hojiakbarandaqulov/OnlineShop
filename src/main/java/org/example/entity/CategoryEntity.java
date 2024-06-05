package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "order_number")
    private Integer orderNumber;
    @Column(name = "name", columnDefinition = "text")
    private String name;
    @Column(name = "visible")
    private Boolean visible;
    @Column(name = "created_date")
    private LocalDate createdDate=LocalDate.now();

}
