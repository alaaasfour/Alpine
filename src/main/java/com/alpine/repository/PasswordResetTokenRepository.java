package com.alpine.repository;

import java.util.Date;
import com.alpine.domain.User;
import com.alpine.domain.security.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.stream.Stream;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    // Method to find a PasswordResetToken by its token
    PasswordResetToken findByToken(String token);
    // Method to find a PasswordResetToken by its associated user
    PasswordResetToken findByUser(User user);
    // Method to find all PasswordResetTokens with expiry dates less than the given date
    Stream<PasswordResetToken> findAllByExpiryDateLessThan(Date now);

    // Custom query to delete all expired PasswordResetTokens
    @Modifying
    @Query("delete from PasswordResetToken t where t.expiryDate <= ?1")
    void deleteAllExpiredSince(Date now);
}
