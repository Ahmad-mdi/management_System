package com.manage.repository.sysman;

import com.manage.model.sysman.Sysman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SysmanRepository extends JpaRepository<Sysman, Long>, JpaSpecificationExecutor<Sysman> {
}


