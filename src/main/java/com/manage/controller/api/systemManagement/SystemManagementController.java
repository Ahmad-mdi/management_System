package com.manage.controller.api.systemManagement;

import com.manage.model.dto.SystemManagementDto;
import com.manage.service.systemManagement.SystemManagementServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/sys_management")
public class SystemManagementController {

    private final SystemManagementServiceImpl service;
    @PostMapping("/add")
    public SystemManagementDto add(@RequestBody SystemManagementDto dto){
        return service.add(dto);
    }
}
