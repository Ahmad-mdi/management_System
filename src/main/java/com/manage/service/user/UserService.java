package com.manage.service.user;

import com.manage.config.JwtTokenUtil;
import com.manage.model.User;
import com.manage.model.dto.UserDto;
import com.manage.model.mapper.UserMapper;
import com.manage.repository.user.UserRepository;
import com.manage.utils.exception.DataNotFoundException;
import com.manage.utils.exception.UserNotFoundException;
import com.manage.utils.hashing.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import static com.manage.constance.UserConstants.DATA_NOT_FOUND;
import static com.manage.constance.UserConstants.INVALID_USERNAME_OR_PASSWORD;

@Service
@AllArgsConstructor
@Data
public class UserService {

    private final UserRepository repository;
    private final SecurityUtils securityUtils;
    private final JwtTokenUtil jwtTokenUtil;

    public UserDto login(String username, String password) throws NoSuchAlgorithmException {
        password = securityUtils.encryptSHA1(password);
        User user = repository.findFirstByUsernameAndPassword(username, password);
        if (user != null) {
            UserDto dto = UserMapper.mapToDTO(user);
            String token = jwtTokenUtil.generateToken(dto);
            dto.setToken(token);
            return dto;
        }
        throw new UserNotFoundException(INVALID_USERNAME_OR_PASSWORD);
    }

    public UserDto addUser(UserDto userDto) throws Exception {
        userDto.setPassword(securityUtils.encryptSHA1(userDto.getPassword()));
        User user = UserMapper.mapToEntity(userDto);
        User savedUser = repository.save(user);
        return UserMapper.mapToDTO(savedUser);
    }

    public UserDto getFirstByUsername(String username) {
        User find = repository.findFirstByUsername(username);
        return UserMapper.mapToDTO(find);
    }

    public List<UserDto> getByUsernameLike(String usernameLike) {
        List<User> searchedList = repository.findByUsernameLike(usernameLike);
        return UserMapper.mapToDTOList(searchedList);
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
    public User getById(long id) {
        Optional<User> data = repository.findById(id);
        if (data.isEmpty())
            throw new DataNotFoundException(DATA_NOT_FOUND.replace("%id%", String.valueOf(id)));
        return data.orElse(null);
    }

    public UserDto update(UserDto data) throws NoSuchAlgorithmException {
        User oldData = getById(data.getId());
        if (oldData == null){
            throw new DataNotFoundException(DATA_NOT_FOUND.replace("%id%", String.valueOf(data.getId())));
        }
        oldData.setUsername(data.getUsername());
        oldData.setFirstname(data.getFirstname());
        oldData.setLastname(data.getLastname());
        oldData.setEnable(data.isEnable());
        if (data.getPassword() != null && !data.getPassword().isEmpty())
            oldData.setPassword(securityUtils.encryptSHA1(data.getPassword()));
        User savedUser = repository.save(oldData);
        return UserMapper.mapToDTO(savedUser);
    }

    /*public boolean deleteById(long id) throws DataNotFoundException {
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
