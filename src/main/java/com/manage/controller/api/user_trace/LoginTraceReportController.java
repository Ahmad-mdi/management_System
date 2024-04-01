package com.manage.controller.api.user_trace;

import com.manage.model.user_trace.LoginTrace;
import com.manage.service.user_trace.LoginTraceReportServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/reports")
@AllArgsConstructor
public class LoginTraceReportController {
    private final LoginTraceReportServiceImpl service;

    @GetMapping("/login-trace/{username}")
    public List<LoginTrace> getLoginTraceByUserName(@PathVariable String username) {
        return service.getLoginTraceByUsername(username);
    }

}
