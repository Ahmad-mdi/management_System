package com.manage.controller.api.menu;

import com.manage.model.dto.menu.MenuDto;
import com.manage.model.dto.sysman.SysmanDto;
import com.manage.model.mapper.menu.MenuMapper;
import com.manage.model.mapper.sysman.SysmanMapper;
import com.manage.model.menu.Menu;
import com.manage.model.sysman.Sysman;
import com.manage.response.ApiResponse;
import com.manage.response.ApiResponseStatus;
import com.manage.service.menu.MenuServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
@AllArgsConstructor
public class MenuController {
    private final MenuServiceImpl service;
    @GetMapping("/get-all")
    public ApiResponse<MenuDto> getAll(@RequestParam Integer pageSize, @RequestParam Integer pageNumber) {
        List<Menu> dataList = service.getAll(pageSize, pageNumber);
        List<MenuDto> mappedToDTOList = MenuMapper.mapToDtoList(dataList);
        long totalCount = service.getAllCount();
        return new ApiResponse<>(mappedToDTOList, totalCount, ApiResponseStatus.SUCCESS);
    }

    @GetMapping("/{id}")
    public ApiResponse<MenuDto> getById(@PathVariable long id) {
        MenuDto get = service.getById(id);
        return new ApiResponse<>(get, ApiResponseStatus.SUCCESS);
    }

    @GetMapping("/filter")
    public ApiResponse<MenuDto> filterMenus(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String org_menu,
            @RequestParam(required = false) String priority,
            @RequestParam(required = false) String menu_code,
            @RequestParam int pageSize,
            @RequestParam int pageNumber) {

        List<Menu> menuFilterd = service.filterMenu(name,org_menu,priority,menu_code, pageSize,pageNumber);
        List<MenuDto> menuDtoList = MenuMapper.mapToDtoList(menuFilterd);//mapped to dto
        long totalCont = service.countAllColumns(name,org_menu,priority,menu_code);
        return new ApiResponse<>(menuDtoList, totalCont, ApiResponseStatus.SUCCESS);
    }

    @PostMapping("/add")
    public ApiResponse<MenuDto> add(@RequestBody @Valid MenuDto dto) {
        MenuDto result = service.add(dto);
        return new ApiResponse<>(result, ApiResponseStatus.SUCCESS);
    }

    @PutMapping("/update")
    public ApiResponse<MenuDto> update(@RequestBody @Valid MenuDto menuDto){
        MenuDto dtoUpdated = service.update(menuDto);
        return new ApiResponse<>(dtoUpdated,ApiResponseStatus.SUCCESS);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Boolean> delete(@PathVariable long id){
        boolean deleted = service.delete(id);
        return new ApiResponse<>(deleted,ApiResponseStatus.SUCCESS);
    }

}
