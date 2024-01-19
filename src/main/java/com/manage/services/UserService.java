package com.manage.services;

import com.manage.models.User;
import com.manage.repositories.UserRepository;
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
    public User add(User user) throws Exception {
        if (user.getFirstname().isEmpty())
             throw new Exception("not null");
        return repository.save(user);
    }
}
