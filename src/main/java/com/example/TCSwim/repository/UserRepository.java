package com.example.TCSwim.repository;

import com.example.TCSwim.model.Athlete;
import com.example.TCSwim.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<Users, Long> {
    UserDetails findByUserName(String userName);
}
