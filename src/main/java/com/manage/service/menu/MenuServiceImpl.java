package com.manage.service.menu;

import com.manage.model.dto.menu.MenuDto;
import com.manage.model.mapper.menu.MenuMapper;
import com.manage.model.mapper.sysman.SysmanMapper;
import com.manage.model.menu.Menu;
import com.manage.model.sysman.Sysman;
import com.manage.repository.menu.MenuRepository;
import com.manage.repository.sysman.SysmanRepository;
import com.manage.utils.exception.DataNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

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
    public long getAllCount() {
        return repository.count();
    }

    @Override
    public MenuDto add(MenuDto dto) {
        Menu menu = MenuMapper.mapToEntity(dto);
        menu.setCreated_date(LocalDateTime.now());

        if (menu.getSysman().getId() != 0) { // Adjusted condition
            Sysman managedSysman = sysmanRepository.findById(menu.getSysman().getId())
                    .orElseThrow(() -> new DataNotFoundException("data.not.found"));
            menu.setSysman(managedSysman);
        }

        Menu savedData = repository.save(menu);
        return MenuMapper.mapToDTO(savedData);
    }



}
