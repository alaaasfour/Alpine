package com.alpineadminportal;

import com.alpineadminportal.domain.User;
import com.alpineadminportal.domain.security.Role;
import com.alpineadminportal.domain.security.UserRole;
import com.alpineadminportal.service.UserService;
import com.alpineadminportal.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AlpineAdminPortalApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(AlpineAdminPortalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user1 = new User();
		user1.setUsername("admin");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("admin"));
		user1.setEmail("admin@gmail.com");
		Set<UserRole> userRoles = new HashSet<>();
		Role role1 = new Role();
		role1.setRoleId(0);
		role1.setName("ROLE_ADMIN");
		userRoles.add(new UserRole(user1, role1));
		userService.createUser(user1, userRoles);
	}
}
