package com.manage.repository.user;

import com.manage.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    @Query("SELECT u FROM User u WHERE lower(u.username) LIKE lower(concat('%', :query, '%'))")
    List<User> findByUsernameLike(@Param("query") String query);

    User findFirstByUsername(String username);

    @Query("SELECT u FROM User u " +
            "WHERE (:username is null or u.username = :username) and " +
            "(:firstname is null or u.firstname = :firstname) and " +
            "(:lastname is null or  u.lastname = :lastname) and " +
            "(:enable is false or  u.enable = :enable) and " +
            "(:enable is true or  u.enable = :enable) and " +
            "(:nationalCode is null or u.nationalCode = :nationalCode)")

    List<User> findByFilter(@Param("username") String username,
                            @Param("firstname") String firstname,
                            @Param("lastname") String lastname,
                            @Param("enable") boolean enable,
                            @Param("nationalCode") String nationalCode,
                            Pageable pageable);
}
