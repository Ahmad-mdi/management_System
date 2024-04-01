package com.manage.model.user_trace;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "login_trace")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginTrace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String ip;

    @Column(nullable = false)
    private LocalDateTime loginTime;

    @Column(nullable = false)
    private boolean isSuccess;
}
