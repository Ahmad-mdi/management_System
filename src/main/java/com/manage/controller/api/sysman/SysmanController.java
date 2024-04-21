package com.manage.controller.api.sysman;

import com.manage.model.dto.sysman.SysmanDto;
import com.manage.model.mapper.sysman.SysmanMapper;
import com.manage.model.sysman.Sysman;
import com.manage.response.ApiResponse;
import com.manage.response.ApiResponseStatus;
import com.manage.service.sysman.SysmanServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/sysman")
public class SysmanController {

    private final SysmanServiceImpl service;

    @GetMapping("/get-all")
    public ApiResponse<SysmanDto> getAll(@RequestParam Integer pageSize, @RequestParam Integer pageNumber) {
        List<Sysman> dataList = service.getAll(pageSize, pageNumber);
        List<SysmanDto> mappedToDTOList = SysmanMapper.mapToDTOList(dataList);
        long totalCount = service.getAllCount();
        return new ApiResponse<>(mappedToDTOList, totalCount, ApiResponseStatus.SUCCESS);
    }

    @GetMapping("/list-for-add-menu")
    public ApiResponse<SysmanDto> getAllSysmansForMenu() {
        List<Sysman> sysmans = service.findAllForMenu();
        List<SysmanDto> dtoList = SysmanMapper.mapToDTOList(sysmans);
        return new ApiResponse<>(dtoList,ApiResponseStatus.SUCCESS);
    }

    @PostMapping("/add")
    public ApiResponse<SysmanDto> add(@RequestBody @Valid SysmanDto dto) {
        SysmanDto result = service.add(dto);
        return new ApiResponse<>(result, ApiResponseStatus.SUCCESS);
    }

    @PutMapping("/update")
    public ApiResponse<SysmanDto> update(@RequestBody @Valid SysmanDto dto) {
        SysmanDto getData = service.update(dto);
        return new ApiResponse<>(getData, ApiResponseStatus.SUCCESS);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Boolean> delete(@PathVariable long id) {
        boolean result = service.deleteById(id);
        return new ApiResponse<>(result, ApiResponseStatus.SUCCESS);
    }

    @GetMapping("/{id}")
    public ApiResponse<SysmanDto> getById(@PathVariable long id) {
        SysmanDto get = service.getById(id);
        return new ApiResponse<>(get, ApiResponseStatus.SUCCESS);
    }


    @GetMapping("/filter")
    public ApiResponse<SysmanDto> filterUsers(
            @RequestParam(required = false) String en_name,
            @RequestParam(required = false) String fa_name,
            @RequestParam(required = false) String route,
            @RequestParam int pageSize,
            @RequestParam int pageNumber) {
        List<Sysman> sysmanFilterd = service.filterSysman(en_name,fa_name,route, pageSize,pageNumber);
        List<SysmanDto> sysmanDtoList = SysmanMapper.mapToDTOList(sysmanFilterd);//mapped to dto
        long totalCont = service.countByEn_nameOrFa_nameOrRoute(en_name,fa_name,route);
        return new ApiResponse<>(sysmanDtoList, totalCont, ApiResponseStatus.SUCCESS);
    }

}
