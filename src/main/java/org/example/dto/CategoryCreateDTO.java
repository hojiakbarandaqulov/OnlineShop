package org.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryCreateDTO {
    private Integer orderNumber;
    private String name;
    private Boolean visible;

}
