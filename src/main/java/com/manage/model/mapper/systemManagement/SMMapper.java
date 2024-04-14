package com.manage.model.mapper.systemManagement;

import com.manage.model.sysman.Sysman;
import com.manage.model.dto.systemManagement.SMDto;

import java.util.ArrayList;
import java.util.List;


public class SMMapper {
    public static SMDto mapToDTO(Sysman sysman) {
        SMDto smDto = new SMDto();
        smDto.setId(sysman.getId());
        smDto.setEn_name(sysman.getEn_name());
        smDto.setFa_name(sysman.getFa_name());
        smDto.setRoute(sysman.getRoute());
        /*smDto.setCreated_user(sm.getCreated_user());
        smDto.setUpdated_user(sm.getUpdated_user());*/
        smDto.setCreated_date(sysman.getCreated_date());
        smDto.setUpdated_date(sysman.getUpdated_date());
        return smDto;
    }


    public static Sysman mapToEntity(SMDto smDto) {
        Sysman sysman = new Sysman();
        sysman.setEn_name(smDto.getEn_name());
        sysman.setFa_name(smDto.getFa_name());
        sysman.setRoute(smDto.getRoute());
        return sysman;
    }

    public static List<SMDto> mapToDTOList(List<Sysman> list) {
        List<SMDto> dtoList = new ArrayList<>();
        for (Sysman sysman : list) {
            SMDto smDto = mapToDTO(sysman);
            dtoList.add(smDto);
        }
        return dtoList;
    }

    public static List<Sysman> mapToEntityList(List<SMDto> list) {
        List<Sysman> sysmanEntityList = new ArrayList<>();
        for (SMDto dto : list) {
            Sysman sysman = mapToEntity(dto);
            sysmanEntityList.add(sysman);
        }
        return sysmanEntityList;
    }
}
