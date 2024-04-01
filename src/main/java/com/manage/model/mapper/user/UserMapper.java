package com.manage.model.mapper.user;

import com.manage.model.User;
import com.manage.model.dto.user.UserDto;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public static UserDto mapToDTO(User user) {
        UserDto userDto = new UserDto();
        userDto.setUsername(user.getUsername());
        userDto.setId(user.getId());
        userDto.setPassword(user.getPassword());
        userDto.setFirstname(user.getFirstname());
        userDto.setLastname(user.getLastname());
        userDto.setNationalCode(user.getNationalCode());
        userDto.setFullName(user.getFirstname()+" "+user.getLastname());
        userDto.setEnable(user.isEnable());
        return userDto;
    }

    public static User mapToEntity(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setFirstname(userDto.getFirstname());
        user.setLastname(userDto.getLastname());
        user.setNationalCode(userDto.getNationalCode());
        user.setEnable(userDto.isEnable());
        return user;
    }

    public static List<UserDto> mapToDTOList(List<User> userList) {
        List<UserDto> UserDtoList = new ArrayList<>();
        for (User user : userList) {
            UserDto UserDto = mapToDTO(user);
            UserDtoList.add(UserDto);
        }
        return UserDtoList;
    }

    public static List<User> mapToEntityList(List<UserDto> userList) {
        List<User> userEntityList = new ArrayList<>();
        for (UserDto userDto : userList) {
            User user = mapToEntity(userDto);
            userEntityList.add(user);
        }
        return userEntityList;
    }
}
