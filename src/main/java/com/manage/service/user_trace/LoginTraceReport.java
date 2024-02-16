package com.manage.service.user_trace;

import com.manage.model.LoginTrace;

import java.util.List;

public interface LoginTraceReport {

    List<LoginTrace> getLoginTraceByUsername(String username);
}
