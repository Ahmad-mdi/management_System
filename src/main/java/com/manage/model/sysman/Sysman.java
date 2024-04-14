package com.manage.model.sysman;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "system_managements")
public class Sysman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String en_name;
    private String fa_name;
    private String route;
    private LocalDateTime created_date;
    private LocalDateTime updated_date;
}