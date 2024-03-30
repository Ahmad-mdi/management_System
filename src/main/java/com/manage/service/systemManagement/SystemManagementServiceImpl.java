package com.manage.service.systemManagement;

import com.manage.model.SystemManagement;
import com.manage.model.dto.SystemManagementDto;
import com.manage.model.mapper.SystemManagementMapper;
import com.manage.repository.systemManagement.SystemManagementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SystemManagementServiceImpl implements SystemManagementService {

    private final SystemManagementRepository repository;
    @Override
     public SystemManagementDto add(SystemManagementDto dto) {
        SystemManagement systemManagement = SystemManagementMapper.mapToEntity(dto);
        SystemManagement savedData = repository.save(systemManagement);
        return SystemManagementMapper.mapToDTO(savedData);
    }
}
