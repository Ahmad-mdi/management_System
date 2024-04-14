package com.manage.model.mapper.sysman;

import com.manage.model.dto.sysman.SysmanDto;
import com.manage.model.sysman.Sysman;

import java.util.ArrayList;
import java.util.List;


public class SysmanMapper {
    public static SysmanDto mapToDTO(Sysman sysman) {
        SysmanDto sysmanDto = new SysmanDto();
        sysmanDto.setId(sysman.getId());
        sysmanDto.setEn_name(sysman.getEn_name());
        sysmanDto.setFa_name(sysman.getFa_name());
        sysmanDto.setRoute(sysman.getRoute());
        /*smDto.setCreated_user(sm.getCreated_user());
        smDto.setUpdated_user(sm.getUpdated_user());*/
        sysmanDto.setCreated_date(sysman.getCreated_date());
        sysmanDto.setUpdated_date(sysman.getUpdated_date());
        return sysmanDto;
    }


    public static Sysman mapToEntity(SysmanDto sysmanDto) {
        Sysman sysman = new Sysman();
        sysman.setEn_name(sysmanDto.getEn_name());
        sysman.setFa_name(sysmanDto.getFa_name());
        sysman.setRoute(sysmanDto.getRoute());
        return sysman;
    }

    public static List<SysmanDto> mapToDTOList(List<Sysman> list) {
        List<SysmanDto> dtoList = new ArrayList<>();
        for (Sysman sysman : list) {
            SysmanDto sysmanDto = mapToDTO(sysman);
            dtoList.add(sysmanDto);
        }
        return dtoList;
    }

    public static List<Sysman> mapToEntityList(List<SysmanDto> list) {
        List<Sysman> sysmanEntityList = new ArrayList<>();
        for (SysmanDto dto : list) {
            Sysman sysman = mapToEntity(dto);
            sysmanEntityList.add(sysman);
        }
        return sysmanEntityList;
    }
}
