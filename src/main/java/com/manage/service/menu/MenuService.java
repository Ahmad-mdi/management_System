package com.manage.service.menu;

import com.manage.model.dto.menu.MenuDto;
import com.manage.model.menu.Menu;
import com.manage.model.sysman.Sysman;

import java.util.List;

public interface MenuService {

    List<Menu> getAll(Integer pageSize, Integer pageNumber);

    long getAllCount();
}
