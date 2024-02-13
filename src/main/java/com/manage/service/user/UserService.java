package com.manage.service.user;

import com.manage.config.JwtTokenUtil;
import com.manage.model.User;
import com.manage.model.dto.UserDto;
import com.manage.model.mapper.UserMapper;
import com.manage.repository.user_trace.LoginTraceRepository;
import com.manage.repository.user.UserRepository;
import com.manage.utils.exception.*;
import com.manage.utils.hashing.SecurityUtils;
import com.manage.helper.ListOfUsersInExcel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Data
public class UserService {
    private final UserRepository repository;
    private final SecurityUtils securityUtils;
    private final JwtTokenUtil jwtTokenUtil;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final LoginTraceRepository loginTraceRepository;

    public UserDto login(String username, String password) throws NoSuchAlgorithmException {
        password = securityUtils.encryptSHA1(password);
        User user = repository.findFirstByUsername(username);
        if (user != null) {
            String storedPassword = user.getPassword();
            if (password.equals(storedPassword)) {
                handleSuccessfulLogin(user);
                UserDto dto = UserMapper.mapToDTO(user);
                String token = jwtTokenUtil.generateToken(dto);
                dto.setToken(token);
                return dto;
            } else
                handleFailedLogin(user);
        }
        logger.error("Invalid username or password");
        throw new LoginException("invalid.username.password");
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
        logger.error("user:" + username + "notFound");
        throw new UserNotFoundException("user.not.found");

    }

    public List<UserDto> getByUsernameLike(String usernameLike) {
        List<User> searchedList = repository.findByUsernameLike(usernameLike);
        return UserMapper.mapToDTOList(searchedList);
    }

    public ByteArrayInputStream importToExcel() throws IOException {
        List<User> users = repository.findAll();
        return ListOfUsersInExcel.dataToExcel(users);
    }

    public List<User> getAll(Integer pageSize, Integer pageNumber) {
        Pageable pagination = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<User> all = repository.findAll(pagination);
        if (all.isEmpty()) {
            logger.warn("pageNumber: " + pageNumber + " " + "pageSize: " + pageSize + " not found");
            throw new DataNotFoundException("data.not.found");
        }
        return all.getContent();
    }

    public long getAllCount() {
        return repository.count();
    }

    public UserDto getById(long id) {
        Optional<User> data = repository.findById(id);
        if (data.isEmpty())
            throw new DataNotFoundException("data.not.found");
        return UserMapper.mapToDTO(data.get());
    }

    public UserDto update(UserDto userDto) {
        Optional<User> userData = repository.findById(userDto.getId());

        if (userData.isEmpty())
            throw new DataNotFoundException("data.not.found");

        User user = userData.get();

        if (notEmptyAndNotNull(userDto.getUsername()))
            user.setUsername(userDto.getUsername());

        if (notEmptyAndNotNull(userDto.getFirstname()))
            user.setFirstname(userDto.getFirstname());

        if (notEmptyAndNotNull(userDto.getLastname()))
            user.setLastname(userDto.getLastname());

        user.setEnable(userDto.isEnable());
        User dataUpdated = repository.save(user);
        return UserMapper.mapToDTO(dataUpdated);
    }

    public boolean deleteById(long id) {
        UserDto user = getById(id);
        if (user != null) {
            repository.deleteById(id);
            return true;
        }
        throw new DataNotFoundException("data.not.found");
    }

    //for updatePass:
    public UserDto changePassword(long id, String oldPassword, String newPassword) throws Exception {
        oldPassword = securityUtils.encryptSHA1(oldPassword);
        Optional<User> find = repository.findById(id);
        User user = find.orElseThrow(() -> new DataNotFoundException("data.not.found"));

        if (!user.getPassword().equals(oldPassword))
            throw new OldPasswordNotFoundException("invalid.old.password");

        if (!validatePasswordPattern(newPassword))
            throw new ValidateNewPasswordException("pattern.password");

        newPassword = securityUtils.encryptSHA1(newPassword);
        user.setPassword(newPassword);
        User updatedPass = repository.save(user);
        return UserMapper.mapToDTO(updatedPass);
    }

    private boolean validatePasswordPattern(String password) {
        String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return password.matches(passwordPattern);
    }

    private boolean notEmptyAndNotNull(String value) {
        return value != null && !value.isEmpty();
    }

    private void handleSuccessfulLogin(User user) {
        if (user.getTryCount() >= 3) {
            LocalDateTime lockTime = user.getLockTime();
            if (lockTime != null && lockTime.isAfter(LocalDateTime.now())) {
                throw new LockedUserException("user.locked");
            }
            user.setTryCount(0);
            user.setLockTime(null);
            repository.save(user); // save the updated user entity with reset tryCount and lock time
        }
        repository.save(user);
    }

    private void handleFailedLogin(User user)  {
       LocalDateTime lockTime = user.getLockTime();
       if (lockTime != null && lockTime.isAfter(LocalDateTime.now())) {
           throw new LockedUserException("user.locked");
       }
       int tryCount = user.getTryCount() + 1;
       lockTime = LocalDateTime.now().plusMinutes(10);
       if (tryCount >= 3) {
           user.setLockTime(lockTime);
       }
       user.setTryCount(tryCount);
       repository.save(user);
   }

}
