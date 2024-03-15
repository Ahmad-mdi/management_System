package com.manage.service.user;

import com.manage.model.User;
import com.manage.model.dto.UserDto;
import org.springframework.http.ResponseEntity;

import javax.transaction.Transactional;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface UserService {
    UserDto login(String username, String password) throws NoSuchAlgorithmException;

    UserDto addUser(UserDto userDto) throws Exception;

    ResponseEntity<String> generateAndSaveUsersToExcel() throws IOException;

    void processUsersFromExcel() throws IOException, NoSuchAlgorithmException;

    UserDto getFirstByUsername(String username);

    List<UserDto> getByUsernameLike(String username);

    ByteArrayInputStream importToExcel() throws IOException;

    List<User> getAll(Integer pageSize, Integer pageNumber);

    long getAllCount();

    UserDto getById(long id);

    UserDto update(UserDto userDto);

    boolean deleteById(long id);

    UserDto changePassword(long id, String oldPassword, String newPassword) throws Exception;
}
