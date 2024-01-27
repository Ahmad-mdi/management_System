package com.manage.repository.user;

import com.manage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findFirstByUsernameAndPassword(String username,String password);
    User findFirstByUsername(String username);
}
