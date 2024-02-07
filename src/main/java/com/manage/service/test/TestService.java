package com.manage.service.test;

import com.manage.utils.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@AllArgsConstructor
@Data
public class TestService {
    private final MessageSource messageSource;
    public void getText() {
//        throw new UserNotFoundException(getMessage("invalid.username"));
        throw new UserNotFoundException(getMessage("ahmad"));
    }
    public String getMessage(String key) {
        Locale locale = Locale.getDefault();
        return messageSource.getMessage(key, null, locale);
    }
}
