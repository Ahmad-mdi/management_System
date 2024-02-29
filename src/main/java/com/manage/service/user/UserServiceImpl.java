package com.manage.service.user;

import com.manage.config.JwtTokenUtil;
import com.manage.helper.ListOfUsersInExcel;
import com.manage.model.User;
import com.manage.model.dto.UserDto;
import com.manage.model.mapper.UserMapper;
import com.manage.repository.user_trace.LoginTraceReportRepository;
import com.manage.repository.user.UserRepository;
import com.manage.utils.exception.*;
import com.manage.utils.hashing.SecurityUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Data
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final SecurityUtils securityUtils;
    private final JwtTokenUtil jwtTokenUtil;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final LoginTraceReportRepository loginTraceRepository;

    @Override
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

    @Override
    public UserDto addUser(UserDto userDto) throws Exception {
        userDto.setPassword(securityUtils.encryptSHA1(userDto.getPassword()));
        User user = UserMapper.mapToEntity(userDto);
        User savedUser = repository.save(user);
        return UserMapper.mapToDTO(savedUser);
    }

    @Override
    public ResponseEntity<String> generateUsersAndExportToExcel() throws IOException {
        List<User> users = generateUsersToExcel(3000);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Users");

        for (int i = 0; i < users.size(); i++) {
            Row row = sheet.createRow(i);
            row.createCell(0).setCellValue(users.get(i).getUsername()); // Setting username
            row.createCell(1).setCellValue(users.get(i).getPassword()); // Setting password
        }

        FileOutputStream fileOut = new FileOutputStream("users.xlsx");
        workbook.write(fileOut);

        workbook.close();

        return ResponseEntity.ok("Excel file created successfully.");
    }

    @Override
    public UserDto getFirstByUsername(String username) {
        User findUsername = repository.findFirstByUsername(username);
        if (findUsername != null)
            return UserMapper.mapToDTO(findUsername);
        logger.error("user:" + username + "notFound");
        throw new UserNotFoundException("user.not.found");

    }

    @Override
    public List<UserDto> getByUsernameLike(String usernameLike) {
        List<User> searchedList = repository.findByUsernameLike(usernameLike);
        return UserMapper.mapToDTOList(searchedList);
    }

    @Override
    public ByteArrayInputStream importToExcel() throws IOException {
        List<User> users = repository.findAll();
        return ListOfUsersInExcel.dataToExcel(users);
    }

    @Override
    public List<User> getAll(Integer pageSize, Integer pageNumber) {
        Pageable pagination = PageRequest.of(pageNumber, pageSize, Sort.by("id"));
        Page<User> all = repository.findAll(pagination);
        if (all.isEmpty()) {
            logger.warn("pageNumber: " + pageNumber + " " + "pageSize: " + pageSize + " not found");
            throw new DataNotFoundException("data.not.found");
        }
        return all.getContent();
    }

    @Override
    public long getAllCount() {
        return repository.count();
    }

    @Override
    public UserDto getById(long id) {
        Optional<User> data = repository.findById(id);
        if (data.isEmpty())
            throw new DataNotFoundException("data.not.found");
        return UserMapper.mapToDTO(data.get());
    }

    @Override
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

    @Override
    public boolean deleteById(long id) {
        UserDto user = getById(id);
        if (user != null) {
            repository.deleteById(id);
            return true;
        }
        throw new DataNotFoundException("data.not.found");
    }

    @Override
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

    private void handleFailedLogin(User user) {
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

    private List<User> generateUsersToExcel(int numUsers) {
        List<User> users = new ArrayList<>();

        for (int i = 0; i < numUsers; i++) {
            User user = new User();
            user.setUsername("user "+i);
            user.setPassword("pass "+i);
            users.add(user);
        }

        return users;
    }


}
