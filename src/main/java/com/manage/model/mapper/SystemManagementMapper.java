package com.manage.model.mapper;

import com.manage.model.SystemManagement;
import com.manage.model.User;
import com.manage.model.dto.SystemManagementDto;
import com.manage.model.dto.UserDto;

import java.util.ArrayList;
import java.util.List;


public class SystemManagementMapper {
    public static SystemManagementDto mapToDTO(SystemManagement systemManagement) {
        SystemManagementDto systemManagementDto = new SystemManagementDto();
        systemManagementDto.setEn_name(systemManagement.getEn_name());
        systemManagementDto.setFa_name(systemManagement.getFa_name());
        systemManagementDto.setRoute(systemManagement.getRoute());
        return systemManagementDto;
    }


    public static SystemManagement mapToEntity(SystemManagementDto systemManagementDto) {
        SystemManagement systemManagement = new SystemManagement();
        systemManagement.setEn_name(systemManagementDto.getEn_name());
        systemManagement.setFa_name(systemManagementDto.getFa_name());
        systemManagement.setRoute(systemManagementDto.getRoute());
        return systemManagement;
    }

    public static List<SystemManagementDto> mapToDTOList(List<SystemManagement> list) {
        List<SystemManagementDto> dtoList = new ArrayList<>();
        for (SystemManagement systemManagement : list) {
            SystemManagementDto dto = mapToDTO(systemManagement);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public static List<SystemManagement> mapToEntityList(List<SystemManagementDto> list) {
        List<SystemManagement> systemManagementEntityList = new ArrayList<>();
        for (SystemManagementDto dto : list) {
            SystemManagement systemManagement = mapToEntity(dto);
            systemManagementEntityList.add(systemManagement);
        }
        return systemManagementEntityList;
    }
}
