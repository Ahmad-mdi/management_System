package com.manage.model.dto.sysman;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysmanDto {
    private long id;
    @NotBlank(message = "نام انگلیسی سامانه نباید خالی باشد")
    private String en_name;
    @NotBlank(message = "نام فارسی سامانه نباید خالی باشد")
    private String fa_name;
    @NotBlank(message = "مسیر سامانه نباید خالی باشد")
    private String route;
    private LocalDateTime created_date;
    private LocalDateTime updated_date;
}
