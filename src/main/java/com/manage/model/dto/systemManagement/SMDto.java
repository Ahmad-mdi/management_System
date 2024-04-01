package com.manage.model.dto.systemManagement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SMDto {
    private long id;
    private String en_name;
    private String fa_name;
    private String route;
}
