package com.manage.service.system_management;

import com.manage.model.dto.systemManagement.SMDto;
import com.manage.model.system_management.SM;

import java.util.List;

public interface SMService {
    List<SM> getAll(Integer pageSize, Integer pageNumber);

    long getAllCount();

    SMDto add(SMDto dto);

    SMDto update(SMDto dto);

    boolean deleteById(long id);

    List<SM> getFilteredSM(SMDto filter);
}
