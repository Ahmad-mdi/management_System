package com.manage.service.menu;

import com.manage.model.dto.menu.MenuDto;
import com.manage.model.mapper.menu.MenuMapper;
import com.manage.model.menu.Menu;
import com.manage.model.sysman.Sysman;
import com.manage.repository.menu.MenuRepository;
import com.manage.repository.sysman.SysmanRepository;
import com.manage.utils.exception.DataNotFoundException;
import com.manage.utils.specification.MenuSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MenuServiceImpl implements MenuService{
    private final MenuRepository repository;
    private final SysmanRepository sysmanRepository;
    @Override
    public List<Menu> getAll(Integer pageSize, Integer pageNumber) {
        Pageable pagination = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Menu> all = repository.findAll(pagination);
        return all.getContent();
    }

    @Override
    public MenuDto getById(long id) {
        Optional<Menu> data = repository.findById(id);
        if (data.isEmpty())
            throw new DataNotFoundException("data.not.found");
        return MenuMapper.mapToDTO(data.get());
    }

    @Override
    public long getAllCount() {
        return repository.count();
    }

    @Override
    public MenuDto add(MenuDto dto) {
        Menu menu = MenuMapper.mapToEntity(dto);
        menu.setCreated_date(LocalDateTime.now());

        if (menu.getSysman() != null && menu.getSysman().getId() != 0) {
            Sysman managedSysman = sysmanRepository.findById(menu.getSysman().getId())
                    .orElseThrow(() -> new DataNotFoundException("data.not.found"));
            menu.setSysman(managedSysman);
        } else
            menu.setSysman(null);

        Menu savedData = repository.save(menu);
        return MenuMapper.mapToDTO(savedData);
    }

    @Override
    public MenuDto update(MenuDto dto) {

        Menu existingMenu = repository.findById(dto.getId())
                .orElseThrow(() -> new DataNotFoundException("data.not.found"));

        existingMenu.setName(dto.getName());
        existingMenu.setPriority(dto.getPriority());
        existingMenu.setMenu_code(dto.getMenu_code());
        existingMenu.setOrg_menu(dto.getOrg_menu());
        existingMenu.setUpdated_date(LocalDateTime.now());

        if (dto.getSysman().getId() != 0) {
            Sysman managedSysman = sysmanRepository.findById(dto.getSysman().getId())
                    .orElseThrow(() -> new DataNotFoundException("data.not.found"));
            existingMenu.setSysman(managedSysman);
        } else
            existingMenu.setSysman(null);

        Menu updatedMenu = repository.save(existingMenu);

        return MenuMapper.mapToDTO(updatedMenu);
    }

    @Override
    public boolean delete(Long id) {
        Menu existingMenu = repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("data.not.found"));

        repository.delete(existingMenu);
        return true;
    }


    @Override
    public List<Menu> filterMenu(String name, String menu_code, String org_menu, String priority, String sysman, int pageSize, int pageNumber) {
        Pageable pagination = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Menu> pageList = repository.findAll(MenuSpecification.filterBy(name, menu_code, org_menu,priority,sysman), pagination);
        return pageList.getContent();
    }

    @Override
    public long countAllColumns(String name, String org_menu, String priority, String menu_code, String sysman) {
        return repository.countBynameOrOrg_menuOrPriorityOrMenu_code(name,org_menu,priority,menu_code,sysman);
    }

}
