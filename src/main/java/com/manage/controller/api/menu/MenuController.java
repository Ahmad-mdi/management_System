package com.manage.controller.api.menu;

import com.manage.model.menu.Menu;
import com.manage.service.menu.MenuServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@AllArgsConstructor
public class MenuController {
    private final MenuServiceImpl service;

    @GetMapping
    public List<Menu> getAll(){
        return service.listOfMenu();
    }
}
