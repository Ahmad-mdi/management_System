package com.manage.controller;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Locale;

@RestController
@RequestMapping("/me")
@AllArgsConstructor
public class Heloo {
    private final MessageSource messageSource;
    @GetMapping("/hello")
    public String helloPersian() {
        return messageSource.getMessage("message.me", null, Locale.forLanguageTag("fa"));
    }
}
