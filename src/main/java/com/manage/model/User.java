package com.manage.model;
import com.manage.utils.validation.NationalCodeNumeric;
import com.manage.utils.validation.NationalCodeValid;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "the username failed is required")
    private String username;
    @NotBlank(message = "the password failed is required")
    private String password;
//    @NotNull(message = "the firstname failed is required")
    @NotBlank(message = "the firstname failed is required")
    private String firstname;
    @NotBlank(message = "the lastname failed is required")
    private String lastname;
    @NationalCodeValid(message = "The national code is not valid")
    @NationalCodeNumeric(message = "national code must be numeric!")
    @NotBlank(message = "the national code failed is required")
    @Column(unique = true)
    private String nationalCode;
    private boolean enable;



}
