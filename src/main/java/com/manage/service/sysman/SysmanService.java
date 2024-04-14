package com.manage.service.sysman;

import com.manage.model.dto.sysman.SysmanDto;
import com.manage.model.sysman.Sysman;

import java.util.List;

public interface SysmanService {
    List<Sysman> getAll(Integer pageSize, Integer pageNumber);

    long getAllCount();

    SysmanDto add(SysmanDto dto);

    SysmanDto update(SysmanDto dto);

    boolean deleteById(long id);

    SysmanDto getById(long id);

    List<Sysman> filterSysman(String en_name, String fa_name, String route, int pageSize, int pageNumber);

    long countByEn_nameOrFa_nameOrRoute(String en_name, String fa_name, String route);

}
