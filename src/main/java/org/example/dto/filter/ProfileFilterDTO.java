package org.example.dto.filter;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.example.enums.ProfileRole;

import java.time.LocalDate;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileFilterDTO {
    @NotBlank(message = "name required")
    private String name;
    @NotBlank(message = "surname required")
    private String surname;
    @NotBlank(message = "email required")
    private String email;
    @NotBlank(message = "role required")
    private ProfileRole role;
    @NotBlank(message = "createdDateFrom required")
    private LocalDate createdDateFrom;
    @NotBlank(message = "createdDateTo required")
    private LocalDate createdDateTo;
}
