package com.alpine.repository;

import org.springframework.data.repository.CrudRepository;

import com.alpine.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
    // Custom method to find a user by their username
    User findByUsername(String username);
    // Custom method to find a user by their email
    User findByEmail(String email);
}
