package com.manage.controllers;

import com.manage.models.User;
import com.manage.response.ApiResponse;
import com.manage.response.ResponseStatus;
import com.manage.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
@Validated
public class UserController {
    private UserService service;

    @GetMapping("/getAll")
    public ApiResponse<User> findAll(){
        try {
            return new ApiResponse<>(service.getAll(),ResponseStatus.SUCCESS);
        }catch (Exception ex){
            return new ApiResponse<>(ex);
        }
    }

    @PostMapping("/save")
    public ApiResponse<User> saveUser(@RequestBody @Valid User user){
       try {
           User result = service.add(user);
           return new ApiResponse<>(result, ResponseStatus.SUCCESS);
       }catch (Exception ex){
           return new ApiResponse<>(ex);
       }
    }
}
