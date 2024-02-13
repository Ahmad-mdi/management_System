package com.manage.controller.api.user_trace;

import com.manage.model.LoginTrace;
import com.manage.service.user_trace.LoginTraceReportService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/reports")
@AllArgsConstructor
public class LoginTraceReportController {
    private final LoginTraceReportService service;
    @GetMapping("/login-trace/{username}")
    public List<LoginTrace> getLoginTraceByUserName(@PathVariable String username) {
        return service.getLoginTraceByUsername(username);
    }

}
