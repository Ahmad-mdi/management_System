package com.manage.model.dto.systemManagement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SMDto {
    private long id;
    @NotBlank(message = "نام انگلیسی سامانه نباید خالی باشد")
    private String en_name;
    @NotBlank(message = "نام فارسی سامانه نباید خالی باشد")
    private String fa_name;
    @NotBlank(message = "مسیر سامانه نباید خالی باشد")
    private String route;
}
