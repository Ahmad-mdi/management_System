package com.manage.service.menu;

import com.manage.model.dto.menu.MenuDto;
import com.manage.model.dto.sysman.SysmanDto;
import com.manage.model.menu.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> getAll(Integer pageSize, Integer pageNumber);

    long getAllCount();

    MenuDto add(MenuDto dto);
}
