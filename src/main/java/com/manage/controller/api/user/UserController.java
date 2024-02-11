package com.manage.controller.api.user;

import com.manage.config.JwtTokenUtil;
import com.manage.model.User;
import com.manage.model.dto.UserDto;
import com.manage.model.mapper.UserMapper;
import com.manage.response.ApiResponse;
import com.manage.response.ApiResponseStatus;
import com.manage.service.user.UserService;
import com.manage.utils.exception.JwtTokenException;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
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
    private final UserService service;
    private final JwtTokenUtil jwtTokenUtil;
    @GetMapping("/list-excel")
    public ResponseEntity<Resource> downloadExcelFile() throws IOException {
        String fileName = "users.xlsx";
        ByteArrayInputStream actualData = service.importToExcel();
        InputStreamResource file = new InputStreamResource(actualData);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename="+fileName)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }


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

    @GetMapping("/search")
    public ApiResponse<UserDto> searchUsersByUsername(@RequestParam String usernameLike) {
        List<UserDto> result = service.getByUsernameLike(usernameLike);
        return new ApiResponse<>(result, ApiResponseStatus.SUCCESS);
    }

    @GetMapping("/get-all")
    public ApiResponse<UserDto> getAll(@RequestParam Integer pageSize, @RequestParam Integer pageNumber) {
        List<User> result = service.getAll(pageSize, pageNumber);
        List<UserDto> userDtoList = UserMapper.mapToDTOList(result);//mapped to dto
        long totalCount = service.getAllCount();
        return new ApiResponse<>(userDtoList, totalCount, ApiResponseStatus.SUCCESS);
    }

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
        return new ApiResponse<>(user,ApiResponseStatus.SUCCESS);
    }

    @PutMapping("/update")
    public ApiResponse<UserDto> update(@RequestBody UserDto data) throws Exception {
        UserDto result = service.update(data);
        return new ApiResponse<>(result,ApiResponseStatus.SUCCESS);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Boolean> delete(@PathVariable long id) {
        boolean result = service.deleteById(id);
        return new ApiResponse<>(result, ApiResponseStatus.SUCCESS);
    }

    @PutMapping("/change-password")
    public ApiResponse<UserDto> changePassword(@RequestBody UserDto data) throws Exception {
        User result = service.changePassword(data.getId(), data.getPassword(), data.getNewPassword());
        UserDto userDto = UserMapper.mapToDTO(result);
        return new ApiResponse<>(userDto,ApiResponseStatus.SUCCESS);
    }

}