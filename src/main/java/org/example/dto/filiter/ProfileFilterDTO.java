package org.example.dto.filiter;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.example.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;
import org.example.enums.ProfileRole;

import java.time.LocalDate;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileFilterDTO {

    private String name;
    private String surname;
    private String email;
    private ProfileRole role;
    private LocalDate createdDateFrom;
    private LocalDate createdDateTo;
}
