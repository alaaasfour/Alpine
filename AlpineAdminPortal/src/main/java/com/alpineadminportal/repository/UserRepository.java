package com.alpineadminportal.repository;
import org.springframework.data.repository.CrudRepository;
import com.alpineadminportal.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
}
