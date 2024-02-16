package com.manage.service.user_trace;

import com.manage.model.LoginTrace;
import com.manage.repository.user_trace.LoginTraceReportRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class LoginTraceReportServiceImpl implements LoginTraceReport {
    private final LoginTraceReportRepository loginTraceRepository;

    @Override
    public List<LoginTrace> getLoginTraceByUsername(String username) {
        List<LoginTrace> successLoginTrace = loginTraceRepository
                .findTop3ByIsSuccessTrueAndUsernameOrderByLoginTimeDesc(username);
        List<LoginTrace> failedLoginTrace = loginTraceRepository
                .findTop3ByIsSuccessFalseAndUsernameOrderByLoginTimeDesc(username);

        List<LoginTrace> loginTraceList = new ArrayList<>();
        loginTraceList.addAll(successLoginTrace);
        loginTraceList.addAll(failedLoginTrace);

        return loginTraceList;
    }
}
