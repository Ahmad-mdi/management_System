package com.manage.service.user_trace;

import com.manage.model.LoginTrace;
import com.manage.repository.user_trace.LoginTraceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LoginTraceReportService {
    private final LoginTraceRepository loginTraceRepository;

    public List<LoginTrace> getLoginTraceByUsername(String username) {
        List<LoginTrace> successfulLoginTrace = loginTraceRepository
                .findTop3ByIsSuccessTrueAndUsernameOrderByLoginTimeDesc(username);
        List<LoginTrace> unsuccessfulLoginTrace = loginTraceRepository
                .findTop3ByIsSuccessFalseAndUsernameOrderByLoginTimeDesc(username);

        List<LoginTrace> loginTraceList = new ArrayList<>();
        loginTraceList.addAll(successfulLoginTrace);
        loginTraceList.addAll(unsuccessfulLoginTrace);

        return loginTraceList;
    }
}
