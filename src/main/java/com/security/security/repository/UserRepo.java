package com.security.security.repository;

import com.security.security.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository <UserInfo,Integer> {


    Optional<UserInfo> findByName(String username);
}
