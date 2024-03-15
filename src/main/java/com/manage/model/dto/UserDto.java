package com.manage.model.dto;

import com.manage.annotation.NationalCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;
    private String fullName;
    @NotBlank(message = "the username failed is required")
    private String username;
//    @NationalCode(message = "The national code is not valid")
    @NationalCode
    private String nationalCode;
    private boolean enable;
    @NotBlank(message = "the firstname failed is required")
    private String firstname;
    @NotBlank(message = "the lastname failed is required")
    private String lastname;
    @NotBlank(message = "the password failed is required")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "کلمه عبور باید ترکیبی از عدد حروف کوچک و بزرگ و علامت مشخص باشد!")
    private String password;
    private String newPassword;
    private String token;
}

