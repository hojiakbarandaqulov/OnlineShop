package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LoginDTO {
    @NotBlank(message = "email required")
    private String email;
    /* @NotBlank(message = "phone required")
     private String phone;*/
    @NotBlank(message = "password required")
    private String password;
}
