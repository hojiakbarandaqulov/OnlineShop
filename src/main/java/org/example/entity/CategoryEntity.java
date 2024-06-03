package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@Entity
@Table(name = "category")
public class CategoryEntity {
    @Id
    private Integer id;
    @Column(name = "order_number")
    private Integer orderNumber;

    @Column(name = "visible")
    private Boolean visible;

    @Column(name = "created_date")
    private LocalDate createdDate;
}
