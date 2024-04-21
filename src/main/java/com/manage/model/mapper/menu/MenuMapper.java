package com.manage.model.mapper.menu;

import com.manage.model.dto.menu.MenuDto;
import com.manage.model.dto.sysman.SysmanDto;
import com.manage.model.menu.Menu;
import com.manage.model.sysman.Sysman;
import java.util.List;
import java.util.stream.Collectors;

public class MenuMapper {
    public static MenuDto mapToDTO(Menu menu) {
        if (menu == null) {
            return null; // Handle the case where menu is null
        }

        MenuDto menuDto = new MenuDto();
        menuDto.setId(menu.getId());
        menuDto.setName(menu.getName());
        menuDto.setPriority(menu.getPriority());
        menuDto.setMenu_code(menu.getMenu_code());
        menuDto.setOrg_menu(menu.getOrg_menu());
        menuDto.setCreated_date(menu.getCreated_date());
        menuDto.setUpdated_date(menu.getUpdated_date());

        SysmanDto sysmanDto = new SysmanDto();
        if (menu.getSysman() != null) {
            sysmanDto.setId(menu.getSysman().getId());
            sysmanDto.setEn_name(menu.getSysman().getEn_name());
            sysmanDto.setFa_name(menu.getSysman().getFa_name());
            sysmanDto.setRoute(menu.getSysman().getRoute());
        } else {
            // Handle the case where sysman is null
            sysmanDto.setId(0); // Set default values or handle as needed
            sysmanDto.setEn_name("سامانه تعریف نشده");
            sysmanDto.setFa_name("سامانه تعریف نشده");
            sysmanDto.setRoute("سامانه تعریف نشده");
        }

        menuDto.setSysman(sysmanDto);

        return menuDto;
    }

    public static Menu mapToEntity(MenuDto menuDto) {
        Menu menu = new Menu();
        menu.setId(menuDto.getId());
        menu.setName(menuDto.getName());
        menu.setPriority(menuDto.getPriority());
        menu.setMenu_code(menuDto.getMenu_code());
        menu.setOrg_menu(menuDto.getOrg_menu());
        menu.setCreated_date(menuDto.getCreated_date());
        menu.setUpdated_date(menuDto.getUpdated_date());

        Sysman sysman = new Sysman();
        sysman.setId(menuDto.getSysman().getId());
        sysman.setEn_name(menuDto.getSysman().getEn_name());
        sysman.setFa_name(menuDto.getSysman().getFa_name());
        sysman.setRoute(menuDto.getSysman().getRoute());

        menu.setSysman(sysman);

        return menu;
    }

    public static List<MenuDto> mapToDtoList(List<Menu> menuList) {
        return menuList.stream().map(MenuMapper::mapToDTO).collect(Collectors.toList());
    }

    public static List<Menu> mapToEntityList(List<MenuDto> menuDtoList) {
        return menuDtoList.stream().map(MenuMapper::mapToEntity).collect(Collectors.toList());
    }
}
