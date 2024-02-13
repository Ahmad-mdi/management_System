package com.manage.repository.login;

import com.manage.model.LoginTrace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginTraceRepository extends JpaRepository<LoginTrace,Long> {
}
