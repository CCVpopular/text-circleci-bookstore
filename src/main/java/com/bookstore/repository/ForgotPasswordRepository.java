package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bookstore.entity.ForgotPassword;


public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Integer> {
    @Query(value = "SELECT * FROM forgot_password f WHERE f.user_id = ?1", nativeQuery = true)
    ForgotPassword findByUserId(long id);

    ForgotPassword findByOtp(int otp);
    @Query(value = "SELECT * FROM forgot_password f WHERE f.otp = ?1 AND f.user_id = ?2" ,nativeQuery = true)
    ForgotPassword findByOtpAndUser(int otp, Long user);
}
