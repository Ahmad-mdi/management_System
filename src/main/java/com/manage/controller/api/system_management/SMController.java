package com.manage.controller.api.system_management;

import com.manage.model.dto.systemManagement.SMDto;
import com.manage.model.dto.user.UserDto;
import com.manage.model.mapper.systemManagement.SMMapper;
import com.manage.model.mapper.user.UserMapper;
import com.manage.model.system_management.SM;
import com.manage.model.user.User;
import com.manage.response.ApiResponse;
import com.manage.response.ApiResponseStatus;
import com.manage.service.system_management.SMServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/sm")
public class SMController {

    private final SMServiceImpl service;
    @GetMapping("get-all")
    public ApiResponse<SMDto> getAll(@RequestParam Integer pageSize,@RequestParam Integer PageNumber){
        List<SM> dataList = service.getAll(pageSize,PageNumber);
        List<SMDto> mappedToDTOList = SMMapper.mapToDTOList(dataList);
        long totalCount = service.getAllCount();
        return new ApiResponse<>(mappedToDTOList,totalCount,ApiResponseStatus.SUCCESS);
    }

    @PostMapping("/add")
    public ApiResponse<SMDto> add(@RequestBody @Valid SMDto dto){
        SMDto result = service.add(dto);
        return new ApiResponse<>(result, ApiResponseStatus.SUCCESS);
    }
}
