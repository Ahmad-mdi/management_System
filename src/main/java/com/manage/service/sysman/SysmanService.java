package com.manage.service.sysman;

import com.manage.model.dto.systemManagement.SMDto;
import com.manage.model.sysman.Sysman;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SysmanService {
    List<Sysman> getAll(Integer pageSize, Integer pageNumber);

    long getAllCount();

    SMDto add(SMDto dto);

    SMDto update(SMDto dto);

    boolean deleteById(long id);

    Page<Sysman> filterSm(String en_name, String fa_name, String route, Pageable pageable);
}
