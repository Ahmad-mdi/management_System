package com.manage.model.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {
    private long id;
    @
    private LocalDateTime created_date;
    private LocalDateTime updated_date;
}
