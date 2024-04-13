package com.manage.repository.user;

import com.manage.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> , JpaSpecificationExecutor<User>{
    @Query("SELECT COUNT(u) FROM User u WHERE u.username LIKE %:username%")
    long countByUsername(@Param("username") String username);

    @Query("SELECT COUNT(u) FROM User u WHERE u.username LIKE %:username% OR u.firstname LIKE %:firstname% OR u.lastname LIKE %:lastname% OR u.nationalCode LIKE %:nationalCode%")
    long countByUsernameOrFirstNameOrLastNameOrNationalCode(
            @Param("username") String username,
            @Param("firstname") String firstname,
            @Param("lastname") String lastname,
            @Param("nationalCode") String nationalCode);
    User findFirstByUsername(String username);
}
