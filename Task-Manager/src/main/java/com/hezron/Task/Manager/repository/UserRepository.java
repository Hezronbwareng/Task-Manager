package com.hezron.Task.Manager.repository;

import com.hezron.Task.Manager.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);


}
