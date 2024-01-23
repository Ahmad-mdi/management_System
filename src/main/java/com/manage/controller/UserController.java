package com.manage.controller;

import com.manage.model.User;
import com.manage.response.ApiResponse;
import com.manage.response.ResponseStatus;
import com.manage.service.UserService;
import com.manage.utils.exception.GlobalExceptionHandler;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
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
       }catch (DataIntegrityViolationException ex){
           String errorMessage = "the national code has already been taken!";
           return new ApiResponse<>(errorMessage,ResponseStatus.EXCEPTION);
       }
    }
}
