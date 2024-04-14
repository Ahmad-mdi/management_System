package com.manage.service.sysman;

import com.manage.model.sysman.Sysman;
import com.manage.model.dto.systemManagement.SMDto;
import com.manage.model.mapper.systemManagement.SMMapper;
import com.manage.repository.sysman.SysmanRepository;
import com.manage.utils.exception.DataNotFoundException;
import com.manage.utils.specification.SMSpecification;
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
public class SysmanServiceImpl implements SysmanService {

    private final SysmanRepository repository;

    @Override
    public List<Sysman> getAll(Integer pageSize, Integer pageNumber) {
        Pageable pagination = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Sysman> all = repository.findAll(pagination);
        return all.getContent();
    }

    @Override
    public long getAllCount() {
        return repository.count();
    }

    @Override
    public SMDto add(SMDto dto) {
        Sysman sysman = SMMapper.mapToEntity(dto);
        sysman.setCreated_date(LocalDateTime.now());
        Sysman savedData = repository.save(sysman);
        return SMMapper.mapToDTO(savedData);
    }


    @Override
    public SMDto update(SMDto dto) {
        Optional<Sysman> findId = repository.findById(dto.getId());

        if (findId.isEmpty())
            throw new DataNotFoundException("data.not.found");

        Sysman sysman = findId.get();

        sysman.setEn_name(dto.getEn_name());
        sysman.setFa_name(dto.getFa_name());
        sysman.setRoute(dto.getRoute());
        sysman.setUpdated_date(LocalDateTime.now());

        Sysman dataUpdated = repository.save(sysman);
        return SMMapper.mapToDTO(dataUpdated);
    }

    @Override
    public boolean deleteById(long id) {
        Optional<Sysman> sm = repository.findById(id);
        if (sm.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        throw new DataNotFoundException("data.not.found");
    }

    @Override
    public Page<Sysman> filterSm(String en_name, String fa_name, String route, Pageable pageable) {
        return repository.findAll(SMSpecification.filterBy(en_name, fa_name, route), pageable);
    }

}
