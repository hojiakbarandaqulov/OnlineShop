package org.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {
    @NotNull
    private Integer id;
    @NotBlank(message = "name required")
    private String name;
    @NotBlank(message = "orderNumber required")
    private Integer orderNumber;
    @NotBlank(message = "visible required")
    private Boolean visible;
    @NotBlank(message = "createdDate required")
    private LocalDate createdDate;
}
