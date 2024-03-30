package com.manage.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SystemManagementDto {
    private long id;
    private String en_name;
    private String fa_name;
    private String route;
}
