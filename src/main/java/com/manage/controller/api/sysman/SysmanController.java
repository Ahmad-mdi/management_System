package com.manage.controller.api.sysman;

import com.manage.model.dto.systemManagement.SMDto;
import com.manage.model.mapper.systemManagement.SMMapper;
import com.manage.model.sysman.Sysman;
import com.manage.response.ApiResponse;
import com.manage.response.ApiResponseStatus;
import com.manage.service.sysman.SysmanServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/systme_management")
public class SysmanController {

    private final SysmanServiceImpl service;

    @GetMapping("get-all")
    public ApiResponse<SMDto> getAll(@RequestParam Integer pageSize, @RequestParam Integer PageNumber) {
        List<Sysman> dataList = service.getAll(pageSize, PageNumber);
        List<SMDto> mappedToDTOList = SMMapper.mapToDTOList(dataList);
        long totalCount = service.getAllCount();
        return new ApiResponse<>(mappedToDTOList, totalCount, ApiResponseStatus.SUCCESS);
    }

    @PostMapping("/add")
    public ApiResponse<SMDto> add(@RequestBody @Valid SMDto dto) {
        SMDto result = service.add(dto);
        return new ApiResponse<>(result, ApiResponseStatus.SUCCESS);
    }

    @PutMapping("/update")
    public ApiResponse<SMDto> update(@RequestBody @Valid SMDto dto) {
        SMDto getData = service.update(dto);
        return new ApiResponse<>(getData, ApiResponseStatus.SUCCESS);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Boolean> delete(@PathVariable long id) {
        boolean result = service.deleteById(id);
        return new ApiResponse<>(result, ApiResponseStatus.SUCCESS);
    }

    @GetMapping("/filter")
    public ApiResponse<Page<SMDto>> filters(
            @RequestParam(required = false) String en_name,
            @RequestParam(required = false) String fa_name,
            @RequestParam(required = false) String route, Pageable pageable) {
        Page<Sysman> data = service.filterSm(en_name, fa_name, route, pageable);
        Page<SMDto> dataDtoPage = data.map(SMMapper::mapToDTO);
        return new ApiResponse<>(dataDtoPage, ApiResponseStatus.SUCCESS);
    }

}
