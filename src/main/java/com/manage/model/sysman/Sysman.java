package com.manage.model.sysman;

import com.manage.model.menu.Menu;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

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

    @OneToMany(mappedBy = "sysman", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Menu> menus;
}
