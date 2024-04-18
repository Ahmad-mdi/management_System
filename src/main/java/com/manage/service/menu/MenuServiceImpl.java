package com.manage.service.menu;

import com.manage.model.menu.Menu;
import com.manage.repository.menu.MenuRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService{
    private final MenuRepository repository;
    @Override
    public List<Menu> listOfMenu(){
        return repository.findAll();
    }
}
