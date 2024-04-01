package com.manage.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String nationalCode;
    private boolean enable;
    private int tryCount;
    private LocalDateTime lockTime;
    private LocalDateTime created_user;
    private LocalDateTime updated_user;
    private LocalDateTime created_date;
    private LocalDateTime updated_date;
}
