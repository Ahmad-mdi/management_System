package com.manage.service.system_management;

import com.manage.model.dto.user.UserDto;
import com.manage.model.mapper.user.UserMapper;
import com.manage.model.system_management.SM;
import com.manage.model.dto.systemManagement.SMDto;
import com.manage.model.mapper.systemManagement.SMMapper;
import com.manage.model.user.User;
import com.manage.repository.system_management.SMRepository;
import com.manage.utils.exception.DataNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class SMServiceImpl implements SMService {

    private final SMRepository repository;

    @Override
    public List<SM> getAll(Integer pageSize, Integer pageNumber) {
        Pageable pagination = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
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


    @Override
    public SMDto update(SMDto dto) {
        Optional<SM> findId = repository.findById(dto.getId());

        if (findId.isEmpty())
            throw new DataNotFoundException("data.not.found");

        SM sm = findId.get();

        if (notEmptyAndNotNull(dto.getEn_name()))
            sm.setEn_name(dto.getEn_name());
        if (notEmptyAndNotNull(dto.getFa_name()))
            sm.setFa_name(dto.getFa_name());
        if (notEmptyAndNotNull(dto.getRoute()))
            sm.setRoute(dto.getRoute());

        sm.setUpdated_date(LocalDateTime.now());
        sm.setUpdated_user(LocalDateTime.now());


        SM dataUpdated = repository.save(sm);
        return SMMapper.mapToDTO(dataUpdated);
    }

    @Override
    public boolean notEmptyAndNotNull(String value) {
        return value != null && !value.isEmpty();
    }
}
