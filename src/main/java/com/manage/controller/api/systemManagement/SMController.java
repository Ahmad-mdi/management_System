package com.manage.controller.api.systemManagement;

import com.manage.model.dto.systemManagement.SMDto;
import com.manage.service.systemManagement.SMServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/sys_management")
public class SMController {

    private final SMServiceImpl service;
    @PostMapping("/add")
    public SMDto add(@RequestBody SMDto dto){
        return service.add(dto);
    }
}
