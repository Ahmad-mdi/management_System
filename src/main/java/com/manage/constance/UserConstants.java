package com.manage.constance;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ResourceBundle;

@Component
public class UserConstants {
    @Value("${errors}")
    private static String resourceBundleFile;
    //errors:
    public static final String INVALID_NATIONAL_CODE = "national code already exists!";
//    public static final String INVALID_USERNAME_OR_PASSWORD = "incorrect username or password";
    public static final String INVALID_USERNAME_OR_PASSWORD = "نام کاربری یا پسورد وارد شده نامعتبر میباشد";

    public static final String USER_NOT_FOUND = "data with username: %username% not found";
    public static final String DATA_INTEGRITY = "data integrity violation occurred";
    public static final String DATA_NOT_FOUND = "data with id %id% not found";
    public static final String PAGE_NUMBER_NOT_FOUND = "data with pageNumber %page% not found";
}
