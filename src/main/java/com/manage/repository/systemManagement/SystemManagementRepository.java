package com.manage.repository.systemManagement;

import com.manage.model.SystemManagement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemManagementRepository extends JpaRepository<SystemManagement,Long> {
}
