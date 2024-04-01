package com.manage.service.systemManagement;

import com.manage.model.SystemManagement;
import com.manage.model.dto.systemManagement.SMDto;
import com.manage.model.mapper.systemManagement.SMMapper;
import com.manage.repository.systemManagement.SMRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SMServiceImpl implements SMService {

    private final SMRepository repository;
    @Override
     public SMDto add(SMDto dto) {
        SystemManagement systemManagement = SMMapper.mapToEntity(dto);
        SystemManagement savedData = repository.save(systemManagement);
        return SMMapper.mapToDTO(savedData);
    }
}
