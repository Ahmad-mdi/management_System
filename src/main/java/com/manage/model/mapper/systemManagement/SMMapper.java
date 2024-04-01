package com.manage.model.mapper.systemManagement;

import com.manage.model.SystemManagement;
import com.manage.model.dto.systemManagement.SMDto;

import java.util.ArrayList;
import java.util.List;


public class SMMapper {
    public static SMDto mapToDTO(SystemManagement systemManagement) {
        SMDto systemManagementDto = new SMDto();
        systemManagementDto.setEn_name(systemManagement.getEn_name());
        systemManagementDto.setFa_name(systemManagement.getFa_name());
        systemManagementDto.setRoute(systemManagement.getRoute());
        return systemManagementDto;
    }


    public static SystemManagement mapToEntity(SMDto systemManagementDto) {
        SystemManagement systemManagement = new SystemManagement();
        systemManagement.setEn_name(systemManagementDto.getEn_name());
        systemManagement.setFa_name(systemManagementDto.getFa_name());
        systemManagement.setRoute(systemManagementDto.getRoute());
        return systemManagement;
    }

    public static List<SMDto> mapToDTOList(List<SystemManagement> list) {
        List<SMDto> dtoList = new ArrayList<>();
        for (SystemManagement systemManagement : list) {
            SMDto dto = mapToDTO(systemManagement);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public static List<SystemManagement> mapToEntityList(List<SMDto> list) {
        List<SystemManagement> systemManagementEntityList = new ArrayList<>();
        for (SMDto dto : list) {
            SystemManagement systemManagement = mapToEntity(dto);
            systemManagementEntityList.add(systemManagement);
        }
        return systemManagementEntityList;
    }
}
