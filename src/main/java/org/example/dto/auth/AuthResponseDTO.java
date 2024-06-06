package org.example.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.example.enums.ProfileRole;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.example.enums.ProfileRole;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AuthResponseDTO {
    private Integer id;
    @NotBlank(message = "name required")
    private String name;
    @NotBlank(message = "surname required")
    private String surname;
    @NotBlank(message = "role required")
    private ProfileRole role;
    private String jwt;
}
