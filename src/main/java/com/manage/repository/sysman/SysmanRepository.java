package com.manage.repository.sysman;

import com.manage.model.sysman.Sysman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SysmanRepository extends JpaRepository<Sysman, Long>, JpaSpecificationExecutor<Sysman> {
    @Query("SELECT COUNT(s) FROM Sysman s WHERE s.en_name LIKE %:en_name% OR s.fa_name LIKE %:fa_name% OR s.route LIKE %:route%")
    long countByEn_nameOrFa_nameOrRoute(
            @Param("en_name") String en_name,
            @Param("fa_name") String fa_name,
            @Param("route") String route);
}


