package com.manage.service.user;

import com.manage.model.User;
import com.manage.repository.user.UserRepository;
import com.manage.utils.hashing.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final SecurityUtils securityUtils;

    public User auth(String username,String password){
        try {
            password = securityUtils.encryptSHA1(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return repository.findFirstByUsernameAndPassword(username,password);
    }

    public User getFirstByUsername(String username){
        return repository.findFirstByUsername(username);
    }
    
    public List<User> getByUsernameLike(String usernameLike){
        return repository.findByUsernameLike(usernameLike);
    }

    public List<User> getAll(Integer pageSize, Integer pageNumber) {
        Pageable pagination = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<User> all = repository.findAll(pagination);
        return all.toList();
    }

    //totalCount pagination:
    public long getAllCount() {
        return repository.count();
    }

    public User add(User data) throws Exception {
        if (data.getUsername()==null || data.getPassword()==null)
            throw new Exception("username password notNull!");
        return repository.save(data);
    }

    /*public User update(User data) throws DataNotFoundException, NoSuchAlgorithmException {
        User oldData = getById(data.getId());//getId with db
        if (oldData == null){
            throw new DataNotFoundException("data with id"+data.getId()+"not found");
        }
        oldData.setEmail(data.getEmail());
        oldData.setFirstname(data.getFirstname());
        oldData.setLastname(data.getLastname());
        oldData.setEnable(data.isEnable());
        if (data.getPassword() != null && !data.getPassword().isEmpty())
            oldData.setPassword(securityUtils.encryptSHA1(data.getPassword()));
        return repository.save(oldData);
    }

    public boolean deleteById(long id) throws DataNotFoundException {
        User oldData = getById(id);//getId with db
        if (oldData == null){
            throw new DataNotFoundException("data with id"+id+"not found");
        }
        repository.deleteById(id);
        return true;
    }

    //for updatePass:
    public User changePassword(long id,String oldPassword,String newPassword) throws Exception {
        try {
            oldPassword = securityUtils.encryptSHA1(oldPassword);
            newPassword = securityUtils.encryptSHA1(newPassword);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }        User userChanePass = getById(id);
        if (userChanePass == null)
            throw new DataNotFoundException("user not found!");
        if (!userChanePass.getPassword().equals(oldPassword))
            throw new Exception("Invalid old password");
        userChanePass.setPassword(newPassword);
        return repository.save(userChanePass);
    }*/
}
