package com.manage.controller.api.user;

import com.manage.config.JwtTokenUtil;
import com.manage.model.user.User;
import com.manage.model.dto.user.UserDto;
import com.manage.model.mapper.user.UserMapper;
import com.manage.response.ApiResponse;
import com.manage.response.ApiResponseStatus;
import com.manage.service.user.UserServiceImpl;
import com.manage.utils.exception.JwtTokenException;
import com.manage.utils.exception.LockUserException;
import com.manage.utils.exception.LoginException;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.core.io.Resource;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserServiceImpl service;
    private final JwtTokenUtil jwtTokenUtil;

    /*******************************************************************************************************************
     ************************************************** Get Mapping ****************************************************
     *******************************************************************************************************************/

    @GetMapping("/get-all")
    public ApiResponse<UserDto> getAll(@RequestParam Integer pageSize, @RequestParam Integer pageNumber) {
        List<User> result = service.getAll(pageSize, pageNumber);
        List<UserDto> userDtoList = UserMapper.mapToDTOList(result);//mapped to dto
        long totalCount = service.getAllCount();
        return new ApiResponse<>(userDtoList, totalCount, ApiResponseStatus.SUCCESS);
    }

    @GetMapping("/search")
    public ApiResponse<UserDto> searchByUsername(@RequestParam(required = false) String username,
                                                 @RequestParam int pageSize,
                                                 @RequestParam int pageNumber) {
        List<User> users = service.searchByUsername(username, pageSize,pageNumber);
        List<UserDto> userDtoList = UserMapper.mapToDTOList(users);//mapped to dto
        long totalCont = service.getAllCountForUserName(username);
        return new ApiResponse<>(userDtoList, totalCont, ApiResponseStatus.SUCCESS);
    }

    @GetMapping("/filter")
    public ApiResponse<UserDto> filterUsers(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String firstname,
            @RequestParam(required = false) String lastname,
            @RequestParam(required = false) String nationalCode,
            @RequestParam int pageSize,
            @RequestParam int pageNumber) {
        List<User> usersFilterd = service.filterUsers(username,firstname,lastname,nationalCode, pageSize,pageNumber);
        List<UserDto> userDtoList = UserMapper.mapToDTOList(usersFilterd);//mapped to dto
        long totalCont = service.countByUsernameOrFirstNameOrLastNameOrNationalCode(username,firstname,lastname,nationalCode);
        return new ApiResponse<>(userDtoList, totalCont, ApiResponseStatus.SUCCESS);
    }


    //jwt auth:
    @GetMapping("/get-user-info")
    public ApiResponse<UserDto> getUserInfo(HttpServletRequest servletRequest) {
        try {
            //read token header and if==ok set token:
            String requestTokenHeader = servletRequest.getHeader("Authorization");
            if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer "))
                throw new JwtTokenException("request token header dose not set");
            //get token and username:
            String token = requestTokenHeader.substring(7);
            String username = jwtTokenUtil.getUsernameFromToken(token);

            if (username == null)
                throw new JwtTokenException("username can not resolve");

            UserDto result = service.getFirstByUsername(username);
            return new ApiResponse<>(result, ApiResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ApiResponse<>(e);
        }
    }

    @GetMapping("/{id}")
    public ApiResponse<UserDto> getById(@PathVariable long id) {
        UserDto user = service.getById(id);
        return new ApiResponse<>(user, ApiResponseStatus.SUCCESS);
    }

    @GetMapping("/download-excel")
    public ResponseEntity<Resource> downloadExcelFile() throws IOException {
        String fileName = "list-of-users.xlsx";
        ByteArrayInputStream actualData = service.importToExcel();
        InputStreamResource file = new InputStreamResource(actualData);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }

    @GetMapping("/generate-user-to-excel")
    public ResponseEntity<String> generateAndSaveUsersToExcel() throws IOException {
        return service.generateAndSaveUsersToExcel();
    }

    @GetMapping("/saved-users-from-excel")
    public void processUsersFromExcel() throws IOException, NoSuchAlgorithmException {
        service.processUsersFromExcel();
    }

    /*******************************************************************************************************************
     ************************************************** Post Mapping ***************************************************
     *******************************************************************************************************************/

    @PostMapping("/login")
    public ApiResponse<UserDto> login(@RequestBody UserDto user) throws NoSuchAlgorithmException {
        UserDto loggedIn = service.login(user.getUsername(), user.getPassword());
        return new ApiResponse<>(loggedIn, ApiResponseStatus.SUCCESS);
    }

    @PostMapping("/add")
    public ApiResponse<UserDto> add(@RequestBody @Valid UserDto data) throws Exception {
        UserDto result = service.addUser(data);
        return new ApiResponse<>(result, ApiResponseStatus.SUCCESS);
    }

    /*******************************************************************************************************************
     ************************************************ Put/Delete Mapping ***********************************************
     *******************************************************************************************************************/

    @PutMapping("/update")
    public ApiResponse<UserDto> update(@RequestBody UserDto data) throws Exception {
        UserDto result = service.update(data);
        return new ApiResponse<>(result, ApiResponseStatus.SUCCESS);
    }

    @PutMapping("/change-password")
    public ApiResponse<UserDto> changePassword(@RequestParam long id ,
                                               @RequestParam String password ,
                                               @RequestParam String newPassword) throws Exception {
        UserDto result = service.changePassword(id, password, newPassword);
        return new ApiResponse<>(result, ApiResponseStatus.SUCCESS);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Boolean> delete(@PathVariable long id) {
        boolean result = service.deleteById(id);
        return new ApiResponse<>(result, ApiResponseStatus.SUCCESS);
    }


}