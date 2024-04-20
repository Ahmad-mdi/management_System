package com.manage.model.dto.menu;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manage.model.dto.sysman.SysmanDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {
    private long id;
    private String name;
    private String menu_code;
    private String org_menu;
    private String priority;
    private LocalDateTime created_date;
    private LocalDateTime updated_date;

    @JsonProperty("sysman")
    private SysmanDto sysman;
}
