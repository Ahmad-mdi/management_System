package com.manage.models;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull(message = "the username failed is required")
    private String username;
    @NotNull(message = "the password failed is required")
    private String password;
    @NotNull(message = "the firstname failed is required")
    private String firstname;
    @NotNull(message = "the lastname failed is required")
    private String lastname;
    @NotNull(message = "the nationalCode failed is required")
    private String nationalCode;
    private boolean enable;


}
