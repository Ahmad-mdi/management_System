package com.manage.service.menu;

import com.manage.model.dto.menu.MenuDto;
import com.manage.model.dto.sysman.SysmanDto;
import com.manage.model.menu.Menu;
import com.manage.model.sysman.Sysman;

import java.util.List;

public interface MenuService {

    List<Menu> getAll(Integer pageSize, Integer pageNumber);

    MenuDto getById(long id);

    long getAllCount();

    MenuDto add(MenuDto dto);

    MenuDto update(MenuDto dto);

    boolean delete(Long id);


    List<Menu> filterMenu(String name, String menu_code, String org_menu, String priority,String sysman, int pageSize, int pageNumber);

    long countAllColumns(String name, String menu_code, String org_menu, String priority,String sysman);
}
