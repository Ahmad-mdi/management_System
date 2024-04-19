package com.manage.model.mapper.menu;

import com.manage.model.dto.menu.MenuDto;
import com.manage.model.dto.sysman.SysmanDto;
import com.manage.model.menu.Menu;
import com.manage.model.sysman.Sysman;
import java.util.ArrayList;
import java.util.List;


public class MenuMapper {
    public static MenuDto mapToDTO(Menu menu) {
        MenuDto menuDto = new MenuDto();
        menuDto.setId(menu.getId());
        menuDto.setName(menu.getName());
        menuDto.setPriority(menu.getPriority());
        menuDto.setMenu_code(menu.getMenu_code());
        menuDto.setOrg_menu(menu.getOrg_menu());
        menuDto.setCreated_date(menu.getCreated_date());
        menuDto.setUpdated_date(menu.getUpdated_date());
        //get relationShip
        SysmanDto sysmanDto = new SysmanDto();
        sysmanDto.setId(menu.getSysman().getId());
        sysmanDto.setEn_name(menu.getSysman().getEn_name());
        sysmanDto.setFa_name(menu.getSysman().getFa_name());
        sysmanDto.setRoute(menu.getSysman().getRoute());

        menuDto.setSysman(sysmanDto);
        return menuDto;
    }



    public static Menu mapToEntity(MenuDto menuDto) {
        Menu menu = new Menu();
        menu.setName(menuDto.getName());
        menu.setPriority(menuDto.getPriority());
        menu.setOrg_menu(menuDto.getOrg_menu());
        menu.setMenu_code(menuDto.getMenu_code());
        menu.setCreated_date(menuDto.getCreated_date());
        menu.setUpdated_date(menuDto.getUpdated_date());

        // Assuming SysmanDto is a DTO for Sysman entity
        SysmanDto sysmanDto = menuDto.getSysman();
        if (sysmanDto != null) {
            Sysman sysman = new Sysman();
            sysman.setId(sysmanDto.getId());
            sysman.setEn_name(sysmanDto.getEn_name());
            sysman.setFa_name(sysmanDto.getFa_name());
            sysman.setRoute(sysmanDto.getRoute());

            // Set the Sysman entity on the Menu object
            menu.setSysman(sysman);
        }

        return menu;
    }


    public static List<MenuDto> mapToDTOList(List<Menu> list) {
        List<MenuDto> dtoList = new ArrayList<>();
        for (Menu menu : list) {
            MenuDto menuDto = mapToDTO(menu);
            dtoList.add(menuDto);
        }
        return dtoList;
    }

    public static List<Menu> mapToEntityList(List<MenuDto> list) {
        List<Menu> menuEntityList = new ArrayList<>();
        for (MenuDto dto : list) {
            Menu menu = mapToEntity(dto);
            menuEntityList.add(menu);
        }
        return menuEntityList;
    }
}
