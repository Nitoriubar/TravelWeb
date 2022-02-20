package com.webservice.web.api.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webservice.web.api.entity.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUserId(String userId);
}
