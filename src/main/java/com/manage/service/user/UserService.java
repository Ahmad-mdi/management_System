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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;
import static com.manage.constance.UserConstants.*;

@Service
@AllArgsConstructor
@Data
public class UserService {
    private final UserRepository repository;
    private final SecurityUtils securityUtils;
    private final JwtTokenUtil jwtTokenUtil;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    public UserDto login(String username, String password) throws NoSuchAlgorithmException {
        password = securityUtils.encryptSHA1(password);
        User user = repository.findFirstByUsernameAndPassword(username, password);
        if (user != null) {
            UserDto dto = UserMapper.mapToDTO(user);
            String token = jwtTokenUtil.generateToken(dto);
            dto.setToken(token);
            return dto;
        }
        logger.error("invalid username or password");
        throw new UserNotFoundException(INVALID_USERNAME_OR_PASSWORD);
    }

    public UserDto addUser(UserDto userDto) throws Exception {
        userDto.setPassword(securityUtils.encryptSHA1(userDto.getPassword()));
        User user = UserMapper.mapToEntity(userDto);
        User savedUser = repository.save(user);
        return UserMapper.mapToDTO(savedUser);
    }

    public UserDto getFirstByUsername(String username) {
        User findUsername = repository.findFirstByUsername(username);
        if (findUsername != null)
            return UserMapper.mapToDTO(findUsername);
        logger.error("user:"+username+"notFound");
        throw new UserNotFoundException(USER_NOT_FOUND.replace("%username%", username));


    }

    public List<UserDto> getByUsernameLike(String usernameLike) {
        List<User> searchedList = repository.findByUsernameLike(usernameLike);
        return UserMapper.mapToDTOList(searchedList);
    }

    public List<User> getAll(Integer pageSize, Integer pageNumber) {
        Pageable pagination = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<User> all = repository.findAll(pagination);
        if (all.isEmpty()){
            logger.warn("pageNumber: " +pageNumber + " " +"pageSize: "+ +pageSize+ " not found");
            throw new DataNotFoundException(PAGE_NUMBER_NOT_FOUND.replace("%page%",String.valueOf(pageNumber)));
        }
        return all.getContent();
    }
    public long getAllCount() {
        return repository.count();
    }
    public UserDto getById(long id) {
        Optional<User> data = repository.findById(id);
        if (data.isEmpty())
            throw new DataNotFoundException(DATA_NOT_FOUND.replace("%id%", String.valueOf(id)));
        return UserMapper.mapToDTO(data.get());
    }

    public UserDto update(UserDto userDto) throws NoSuchAlgorithmException {
        Optional<User> userData = repository.findById(userDto.getId());
        if (userData.isPresent()) {
            User user = userData.get();
            user.setUsername(userDto.getUsername());
            user.setFirstname(userDto.getFirstname());
            user.setLastname(userDto.getLastname());
            user.setEnable(userDto.isEnable());
            if (!StringUtils.isEmpty(userDto.getPassword()))
                user.setPassword(securityUtils.encryptSHA1(userDto.getPassword()));
            User dataUpdated = repository.save(user);
            return UserMapper.mapToDTO(dataUpdated);
        }
        throw new DataNotFoundException(DATA_NOT_FOUND.replace("%id%", String.valueOf(userDto.getId())));
    }

    public boolean deleteById(long id) {
        UserDto user = getById(id);
        if (user != null) {
            repository.deleteById(id);
            return true;
        }
        throw new DataNotFoundException(DATA_NOT_FOUND.replace("%id%", String.valueOf(id)));
    }
    //for updatePass:
    /*public User changePassword(long id,String oldPassword,String newPassword) throws Exception {
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
