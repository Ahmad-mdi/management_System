package com.manage.controller.api.menu;

import com.manage.model.dto.menu.MenuDto;
import com.manage.model.mapper.menu.MenuMapper;
import com.manage.model.menu.Menu;
import com.manage.response.ApiResponse;
import com.manage.response.ApiResponseStatus;
import com.manage.service.menu.MenuServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
@AllArgsConstructor
public class MenuController {
    private final MenuServiceImpl service;
    @GetMapping("/get-all")
    public ApiResponse<MenuDto> getAll(@RequestParam Integer pageSize, @RequestParam Integer pageNumber) {
        List<Menu> dataList = service.getAll(pageSize, pageNumber);
        List<MenuDto> mappedToDTOList = MenuMapper.mapToDTOList(dataList);
        long totalCount = service.getAllCount();
        return new ApiResponse<>(mappedToDTOList, totalCount, ApiResponseStatus.SUCCESS);
    }

}
