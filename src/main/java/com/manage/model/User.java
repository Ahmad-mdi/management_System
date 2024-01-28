package com.manage.model;
import com.manage.utils.customValidation.NationalCode;
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
    @NationalCode(message = "The national code is not valid")
    @Column(unique = true)
    private String nationalCode;
    private boolean enable;



}
