package com.manage.config;

import com.manage.response.ApiResponse;
import com.manage.response.ApiResponseStatus;
import org.springframework.stereotype.Component;

@Component
public class ConstantsMessage {
    //errors:
    public static final String INVALID_NATIONAL_CODE = "National code already exists!";
    public static final String INVALID_USERNAME_OR_PASSWORD = "Incorrect username or password";
    public static final String DATA_INTEGRITY = "Data integrity violation occurred";
}
