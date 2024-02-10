package com.manage.service.user;

import com.manage.config.JwtTokenUtil;
import com.manage.model.User;
import com.manage.model.dto.UserDto;
import com.manage.model.mapper.UserMapper;
import com.manage.repository.user.UserRepository;
import com.manage.utils.exception.*;
import com.manage.utils.hashing.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

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
            if (user.getTryCount() >= 3) {
                LocalDateTime lockTime = user.getLockTime();
                if (lockTime != null && lockTime.isAfter(LocalDateTime.now())) {
                    throw new LockedUserException("");
                }
                user.setTryCount(0);
                user.setLockTime(null);
                repository.save(user); // save the updated user entity with reset tryCount and lock time
            }
            UserDto dto = UserMapper.mapToDTO(user);
            String token = jwtTokenUtil.generateToken(dto);
            dto.setToken(token);
            return dto;
        } else {
            user = repository.findFirstByUsername(username);
            if (user != null) {
                LocalDateTime lockTime = user.getLockTime();
                if (lockTime != null && lockTime.isAfter(LocalDateTime.now())) {
                    throw new LockedUserException("");
                }
                int tryCount = user.getTryCount() + 1;
                lockTime = LocalDateTime.now().plusMinutes(10);
                if (tryCount >= 3) {
                    user.setLockTime(lockTime);
                }
                user.setTryCount(tryCount);
                repository.save(user); // save the updated user entity with incremented tryCount and lock time
            }
            logger.error("Invalid username or password");
            throw new LoginException("");
        }
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
        throw new UserNotFoundException("");


    }

    public List<UserDto> getByUsernameLike(String usernameLike) {
        List<User> searchedList = repository.findByUsernameLike(usernameLike);
        return UserMapper.mapToDTOList(searchedList);
    }

    public List<User> getAll(Integer pageSize, Integer pageNumber) {
        Pageable pagination = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<User> all = repository.findAll(pagination);
        if (all.isEmpty()) {
            logger.warn("pageNumber: " + pageNumber + " " + "pageSize: " + pageSize + " not found");
            throw new DataNotFoundException("");
        }
        return all.getContent();
    }

    public long getAllCount() {
        return repository.count();
    }

    public UserDto getById(long id) {
        Optional<User> data = repository.findById(id);
        if (data.isEmpty())
            throw new DataNotFoundException("");
        return UserMapper.mapToDTO(data.get());
    }

    public UserDto update(UserDto userDto) {
        Optional<User> userData = repository.findById(userDto.getId());

        if (userData.isEmpty())
            throw new DataNotFoundException("");

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
    private boolean notEmptyAndNotNull(String value) {
        return value != null && !value.isEmpty();
    }

    public boolean deleteById(long id) {
        UserDto user = getById(id);
        if (user != null) {
            repository.deleteById(id);
            return true;
        }
        throw new DataNotFoundException("");
    }

    //for updatePass:
    public User changePassword(long id, String oldPassword, String newPassword) throws Exception {
        oldPassword = securityUtils.encryptSHA1(oldPassword);
        Optional<User> find = repository.findById(id);
        User user = find.orElseThrow(() -> new DataNotFoundException(""));

        if (!user.getPassword().equals(oldPassword))
            throw new OldPasswordNotFoundException("");

        if (!validatePasswordPattern(newPassword))
            throw new ValidateNewPasswordException("");

        newPassword = securityUtils.encryptSHA1(newPassword);
        user.setPassword(newPassword);
        return repository.save(user);
    }

    private boolean validatePasswordPattern(String password) {
        String passwordPattern = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        return password.matches(passwordPattern);
    }
}
