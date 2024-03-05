package com.learningplatform.web.DTO;

import com.learningplatform.web.models.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RegistrationDTO {
    private Long id;
    @NotEmpty (message = "Email should not be empty!")
    @Email(message = "Enter a valid email address!")
    private String email;
    @NotEmpty(message = "Username should not be empty!")
    private String username;
    @NotEmpty(message = "Password should not be empty!")
    private String password;
    @NotEmpty(message = "Choose a role!")
    private String role;

}
