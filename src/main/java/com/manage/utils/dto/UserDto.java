package com.manage.utils.dto;

import com.manage.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String newPassword;
    private String nationalCode;
    private boolean enable;
    private String token;
    private String fullName;

    public UserDto(User user) {
        setId(user.getId());
        setEnable(user.isEnable());
        setFirstname(user.getFirstname());
        setLastname(user.getLastname());
        setUsername(user.getUsername());
        setNationalCode(user.getNationalCode());
        setFullName(getFirstname()+" "+getLastname());
    }
}
