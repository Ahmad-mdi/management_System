package com.manage.repository.system_management;

import com.manage.model.system_management.SM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SMRepository extends JpaRepository<SM, Long> {
    @Query("SELECT s FROM SM s " +
            "WHERE (:en_name is null or s.en_name = :en_name) and " +
            "(:fa_name is null or s.fa_name = :fa_name) and " +
            "(:route is null or s.route = :route)")

    List<SM> findByFilter(@Param("en_name") String en_name,
                          @Param("fa_name") String fa_name,
                          @Param("route") String route);


}

