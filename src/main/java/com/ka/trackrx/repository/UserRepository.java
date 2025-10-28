package com.ka.trackrx.repository;

import com.ka.trackrx.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
