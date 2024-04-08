package com.manage.service.system_management;

import com.manage.model.dto.systemManagement.SMDto;
import com.manage.model.system_management.SM;
import com.manage.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SMService {
    List<SM> getAll(Integer pageSize, Integer pageNumber);

    long getAllCount();

    SMDto add(SMDto dto);

    SMDto update(SMDto dto);

    boolean deleteById(long id);

    Page<SM> filterSm(String en_name, String fa_name, String route, Pageable pageable);
}
