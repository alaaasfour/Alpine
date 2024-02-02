package com.alpine;

import com.alpine.domain.security.Role;
import com.alpine.domain.security.UserRole;
import com.alpine.repository.RoleRepository;
import com.alpine.service.UserService;
import com.alpine.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.alpine.domain.User;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AlpineApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(AlpineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user1 = new User();
		user1.setFirstName("John");
		user1.setLastName("Adams");
		user1.setUsername("j");
		user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));
		user1.setEmail("JAdams@gmail.com");
		Set<UserRole> userRoles = new HashSet<>();
		Role role1= new Role();
		role1.setRoleId(1);
		role1.setName("ROLE_USER");
		userRoles.add(new UserRole(user1, role1));

		userService.createUser(user1, userRoles);
	}
}
