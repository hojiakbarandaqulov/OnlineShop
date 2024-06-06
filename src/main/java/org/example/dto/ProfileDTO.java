package org.example.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import org.example.enums.ProfileRole;
import org.example.enums.ProfileStatus;
import lombok.Getter;
import lombok.Setter;
import org.example.enums.ProfileRole;
import org.example.enums.ProfileStatus;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {
    @NotNull
    private Integer id;
    @NotBlank(message = "name required")
    private String name;
    @NotBlank(message = "surname required")
    private String surname;
    @NotBlank(message = "email required")
    private String email;
    @NotBlank(message = "phone required")
    private String phone;
    @NotBlank(message = "password required")
    private String password;
    @NotBlank(message = "status required")
    private ProfileStatus status;
    @NotBlank(message = "role required")
    private ProfileRole role;
    @NotBlank(message = "visible required")
    private Boolean visible;
    @NotBlank(message = "createdDate required")
    private LocalDate createdDate;
    @NotBlank(message = "photoId required")
    private Integer photoId;
    @NotBlank(message = "jwt required")
    private String jwt;
}
