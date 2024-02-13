package com.manage.repository.user_trace;

import com.manage.model.LoginTrace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LoginTraceRepository extends JpaRepository<LoginTrace,Long> {
    List<LoginTrace> findTop3ByIsSuccessTrueAndUsernameOrderByLoginTimeDesc(String username);
    List<LoginTrace> findTop3ByIsSuccessFalseAndUsernameOrderByLoginTimeDesc(String username);
}
