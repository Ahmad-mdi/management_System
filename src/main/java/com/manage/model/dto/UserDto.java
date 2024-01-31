package com.manage.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manage.annotation.NationalCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
//    @JsonIgnore
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
    private String password;
    private String token;

}
