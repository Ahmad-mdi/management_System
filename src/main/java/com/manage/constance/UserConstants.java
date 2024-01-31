package com.manage.constance;

import org.springframework.stereotype.Component;

@Component
public class UserConstants {
    //errors:
    public static final String INVALID_NATIONAL_CODE = "National code already exists!";
    public static final String INVALID_USERNAME_OR_PASSWORD = "Incorrect username or password";
    public static final String DATA_INTEGRITY = "Data integrity violation occurred";
    public static final String DATA_NOT_FOUND = "data with %id% not found";
}
