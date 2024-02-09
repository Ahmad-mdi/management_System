package com.manage.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manage.annotation.NationalCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.MessageSource;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;
    private String fullName;
    @NotBlank(message = "the username failed is required")
    private String username;
    @NationalCode(message = "The national code is not valid")
    private String nationalCode;
    private boolean enable;
    @NotBlank(message = "the firstname failed is required")
    private String firstname;
    @NotBlank(message = "the lastname failed is required")
    private String lastname;
    @NotBlank(message = "the password failed is required")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "password must be a combination of uppercase letters, lowercase letters, numbers, and special characters")
    private String password;
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "password must be a combination of uppercase letters, lowercase letters, numbers, and special characters")
    private String newPassword;
    private String token;
}

