package com.manage.model.menu;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.manage.model.sysman.Sysman;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Entity
@Table(name = "menu_managements")
public class Menu {
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String menu_code;
    private String org_menu;
    private String Priority;
    private LocalDateTime created_date;
    private LocalDateTime updated_date;

    @ManyToOne
    @JoinColumn(name = "sysman_id", referencedColumnName = "id")
    @JsonProperty
    private Sysman sysman;

}
