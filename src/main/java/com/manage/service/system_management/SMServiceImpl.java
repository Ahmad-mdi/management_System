package com.manage.service.system_management;

import com.manage.model.system_management.SM;
import com.manage.model.dto.systemManagement.SMDto;
import com.manage.model.mapper.systemManagement.SMMapper;
import com.manage.repository.system_management.SMRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class SMServiceImpl implements SMService {

    private final SMRepository repository;

    @Override
    public List<SM> getAll(Integer pageSize, Integer pageNumber){
        Pageable pagination = PageRequest.of(pageNumber,pageSize, Sort.by("id"));
        Page<SM> all = repository.findAll(pagination);
        return all.getContent();
    }

    @Override
    public long getAllCount() {
        return repository.count();
    }
    @Override
     public SMDto add(SMDto dto) {
        SM sm = SMMapper.mapToEntity(dto);
        SM savedData = repository.save(sm);
        return SMMapper.mapToDTO(savedData);
    }
}
