package com.manage.model.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manage.model.sysman.Sysman;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;


@Data
@Entity
@Table(name = "menu_managements")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(unique = true)
    private String menu_code;
    private String org_menu;
    private String priority;
    private LocalDateTime created_date;
    private LocalDateTime updated_date;

    @ManyToOne/*(cascade = {CascadeType.ALL})*/
    @JoinColumn(name = "sysman_id", referencedColumnName = "id")
    @JsonProperty("sysman")
    private Sysman sysman;

    //for deleted by id from Sysman (relation supported by Set<> interface) and set hashcode for objects:
    @Override
    public int hashCode() {
        return Objects.hash(id, name, menu_code, org_menu);
    }

}
