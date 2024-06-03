package org.example.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CategoryDTO {
    private Integer id;
    private Integer orderNumber;
    private String name;
    private Boolean visible;
    private LocalDate createdDate;
}
