package com.manage.service.user;

import com.manage.model.user.User;
import com.manage.model.dto.user.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

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

    ByteArrayInputStream importToExcel() throws IOException;

    List<User> getAll(Integer pageSize, Integer pageNumber);

    long getAllCount();

    long getAllCountForUserName(String username);

    UserDto getById(long id);

    UserDto update(UserDto userDto);

    boolean deleteById(long id);

    UserDto changePassword(long id, String oldPassword, String newPassword) throws Exception;

    List<User> searchByUsername(String username, int pageSize, int pageNumber);

    Page<User> filterUsers(String username, String firstname, String lastname, String nationalCode, Pageable pageable);

}
