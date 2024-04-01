package com.manage.service.user_trace;

import com.manage.model.user_trace.LoginTrace;

import java.util.List;

public interface LoginTraceReport {

    List<LoginTrace> getLoginTraceByUsername(String username);
}
