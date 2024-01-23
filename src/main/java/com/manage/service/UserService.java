package com.manage.service;

import com.manage.model.User;
import com.manage.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository repository;

    public List<User> getAll(){
        return repository.findAll();
    }
    public User add(User user) {
        return repository.save(user);
    }
}
