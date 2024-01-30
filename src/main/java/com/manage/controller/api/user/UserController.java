package com.manage.controller.api.user;

import com.manage.config.ConstantsMessage;
import com.manage.config.JwtTokenUtil;
import com.manage.model.User;
import com.manage.model.dto.UserDto;
import com.manage.model.mapper.UserMapper;
import com.manage.response.ApiResponse;
import com.manage.response.ApiResponseStatus;
import com.manage.service.user.UserService;
import com.manage.utils.exception.JwtTokenException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;
    private final ConstantsMessage messageError;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ApiResponse<UserDto> login(@RequestBody UserDto user) {
        try {
            UserDto loggedIn = service.login(user.getUsername(), user.getPassword());
            return new ApiResponse<>(loggedIn, ApiResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ApiResponse<>(messageError.validUsernameAndPassword, ApiResponseStatus.FAILED);
        }
    }

    @PostMapping("/add")
    public ApiResponse<UserDto> add(@RequestBody @Valid UserDto data) throws Exception {
        UserDto result = service.addUser(data);
        return new ApiResponse<>(result, ApiResponseStatus.SUCCESS);
        /*try {
            UserDto result = service.addUser(data);
            return new ApiResponse<>(result, ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ApiResponse<>(e);
        }*/
    }

    @GetMapping("/search")
    public ApiResponse<UserDto> searchUsersByUsername(@RequestParam String username_like) {
        try {
            List<UserDto> result = service.getByUsernameLike(username_like);
            return new ApiResponse<>(result, ApiResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ApiResponse<>(e);
        }
    }

    @GetMapping("/get_all")
    public ApiResponse<UserDto> getAll(@RequestParam Integer pageSize, @RequestParam Integer pageNumber) {
        try {
            List<User> result = service.getAll(pageSize, pageNumber);
            List<UserDto> userDtoList = UserMapper.mapToDTOList(result);//mapped to dto
            long totalCount = service.getAllCount();
            return new ApiResponse<>(userDtoList, totalCount, ApiResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ApiResponse<>(e);
        }
    }

    @GetMapping("/get_user_info")
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

    /*@PutMapping("/update")
    public ApiResponse<UserDto> update(@RequestBody User data) {
        try {
            User result = service.update(data);
            return new ApiResponse<>(new UserDto(result), ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ApiResponse<>(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Boolean> delete(@PathVariable long id) {
        try {
            boolean result = service.deleteById(id);
            return new ApiResponse<>(result, ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ApiResponse<>(e);
        }
    }

    @PutMapping("/changePass")
    public ApiResponse<UserDto> changePassword(@RequestBody UserDto data) {
        try {
            User result = service.changePassword(data.getId(), data.getPassword(), data.getNewPassword());
            return new ApiResponse<>(new UserDto(result), ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ApiResponse<>(e);
        }
    }*/

}