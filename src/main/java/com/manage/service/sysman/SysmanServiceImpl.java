package com.manage.service.sysman;

import com.manage.model.sysman.Sysman;
import com.manage.model.dto.sysman.SysmanDto;
import com.manage.model.mapper.sysman.SysmanMapper;
import com.manage.repository.sysman.SysmanRepository;
import com.manage.utils.exception.DataNotFoundException;
import com.manage.utils.specification.SysmanSpecification;
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
    public SysmanDto add(SysmanDto dto) {
        Sysman sysman = SysmanMapper.mapToEntity(dto);
        sysman.setCreated_date(LocalDateTime.now());
        Sysman savedData = repository.save(sysman);
        return SysmanMapper.mapToDTO(savedData);
    }


    @Override
    public SysmanDto update(SysmanDto dto) {
        Optional<Sysman> findId = repository.findById(dto.getId());

        if (findId.isEmpty())
            throw new DataNotFoundException("data.not.found");

        Sysman sysman = findId.get();

        sysman.setEn_name(dto.getEn_name());
        sysman.setFa_name(dto.getFa_name());
        sysman.setRoute(dto.getRoute());
        sysman.setUpdated_date(LocalDateTime.now());

        Sysman dataUpdated = repository.save(sysman);
        return SysmanMapper.mapToDTO(dataUpdated);
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
    public SysmanDto getById(long id) {
        Optional<Sysman> data = repository.findById(id);
        if (data.isEmpty())
            throw new DataNotFoundException("data.not.found");
        return SysmanMapper.mapToDTO(data.get());
    }

    @Override
    public List<Sysman> filterSysman(String en_name, String fa_name, String route, int pageSize, int pageNumber) {
        Pageable pagination = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<Sysman> pageList = repository.findAll(SysmanSpecification.filterBy(en_name, fa_name, route), pagination);
        return pageList.getContent();
    }

    @Override
    public long countByEn_nameOrFa_nameOrRoute(String en_name, String fa_name, String route) {
        return repository.countByEn_nameOrFa_nameOrRoute(en_name,fa_name,route);
    }

}
