package com.manage.model.mapper.systemManagement;

import com.manage.model.system_management.SM;
import com.manage.model.dto.systemManagement.SMDto;

import java.util.ArrayList;
import java.util.List;


public class SMMapper {
    public static SMDto mapToDTO(SM sm) {
        SMDto smDto = new SMDto();
        smDto.setId(sm.getId());
        smDto.setEn_name(sm.getEn_name());
        smDto.setFa_name(sm.getFa_name());
        smDto.setRoute(sm.getRoute());
        /*smDto.setCreated_user(sm.getCreated_user());
        smDto.setUpdated_user(sm.getUpdated_user());*/
        smDto.setCreated_date(sm.getCreated_date());
        smDto.setUpdated_date(sm.getUpdated_date());
        return smDto;
    }


    public static SM mapToEntity(SMDto smDto) {
        SM sm = new SM();
        sm.setEn_name(smDto.getEn_name());
        sm.setFa_name(smDto.getFa_name());
        sm.setRoute(smDto.getRoute());
        return sm;
    }

    public static List<SMDto> mapToDTOList(List<SM> list) {
        List<SMDto> dtoList = new ArrayList<>();
        for (SM sm : list) {
            SMDto smDto = mapToDTO(sm);
            dtoList.add(smDto);
        }
        return dtoList;
    }

    public static List<SM> mapToEntityList(List<SMDto> list) {
        List<SM> smEntityList = new ArrayList<>();
        for (SMDto dto : list) {
            SM sm = mapToEntity(dto);
            smEntityList.add(sm);
        }
        return smEntityList;
    }
}
