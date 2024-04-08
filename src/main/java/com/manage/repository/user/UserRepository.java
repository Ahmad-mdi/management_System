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
public interface UserRepository extends JpaRepository<User, Long> , JpaSpecificationExecutor<User>{
    @Query("SELECT u FROM User u WHERE lower(u.username) LIKE lower(concat('%', :query, '%'))")
    List<User> findByUsernameLike(@Param("query") String query);

    User findFirstByUsername(String username);

}
