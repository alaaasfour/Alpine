package com.alpineadminportal.service;

import com.alpineadminportal.domain.User;
import com.alpineadminportal.domain.security.UserRole;

import java.util.Set;

public interface UserService {
    User createUser(User user, Set<UserRole> userRoles) throws Exception;
    User save(User user);
}
