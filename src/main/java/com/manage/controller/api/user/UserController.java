package com.manage.controller.api.user;


import com.manage.ConstantsMessage;
import com.manage.model.User;
import com.manage.response.ApiResponse;
import com.manage.response.ResponseStatus;
import com.manage.service.user.UserService;
import com.manage.utils.dto.UserDto;
import com.manage.utils.exception.JwtTokenException;
import com.manage.utils.hashing.SecurityUtils;
import com.manage.config.JwtTokenUtil;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final JwtTokenUtil jwtTokenUtil;
    private final SecurityUtils securityUtils;
    private final UserService userService;

    @PostMapping("/login")
    public ApiResponse<UserDto> login(@RequestBody User user) {
        User userData = userService.auth(user.getUsername(), user.getPassword());
        if (userData == null)
            return new ApiResponse<>(ConstantsMessage.MSG2, ResponseStatus.FAILED);
        UserDto UserDto = new UserDto(userData);
        String token = jwtTokenUtil.generateToken(UserDto);
        UserDto.setToken(token);
        return new ApiResponse<>(UserDto, ResponseStatus.SUCCESS);
    }

    /*@GetMapping("/{id}")
    public ApiResponse<UserDto> getById(@PathVariable long id) {
        try {
            User result = userService.getById(id);
            return new ApiResponse<>(new UserDto(result), ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ApiResponse<>(e);
        }
    }*/

    @GetMapping("/getAll")
    public ApiResponse<UserDto> getAll(
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber) {
        try {
            List<User> result = userService.getAll(pageSize, pageNumber);
            List<UserDto> userDtoList = new ArrayList<>();
            result.forEach(data->userDtoList.add(new UserDto(data)));
            long totalCount = userService.getAllCount();
            return new ApiResponse<>(userDtoList,totalCount, ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ApiResponse<>(e);
        }
    }

    @GetMapping("/getUserInfo")
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

            User result = userService.getByUsername(username);
            return new ApiResponse<>(new UserDto(result), ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ApiResponse<>(e);
        }
    }

    @PostMapping("/add")
    public ApiResponse<UserDto> add(@RequestBody @Valid User data) {
        try {
            data.setPassword(securityUtils.encryptSHA1(data.getPassword()));
            User result = userService.add(data);
            return new ApiResponse<>(new UserDto(result), ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ApiResponse<>(e);
        }
    }

    /*@PutMapping("/update")
    public ApiResponse<UserDto> update(@RequestBody User data) {
        try {
            User result = userService.update(data);
            return new ApiResponse<>(new UserDto(result), ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ApiResponse<>(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Boolean> delete(@PathVariable long id) {
        try {
            boolean result = userService.deleteById(id);
            return new ApiResponse<>(result, ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ApiResponse<>(e);
        }
    }

    @PutMapping("/changePass")
    public ApiResponse<UserDto> changePassword(@RequestBody UserDto data) {
        try {
            User result = userService.changePassword(data.getId(), data.getPassword(), data.getNewPassword());
            return new ApiResponse<>(new UserDto(result), ResponseStatus.SUCCESS);
        } catch (Exception e) {
            return new ApiResponse<>(e);
        }
    }*/

}
