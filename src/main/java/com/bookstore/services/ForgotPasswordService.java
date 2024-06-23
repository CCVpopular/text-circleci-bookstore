package com.bookstore.services;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.ForgotPassword;
import com.bookstore.entity.User;
import com.bookstore.repository.ForgotPasswordRepository;
import com.bookstore.repository.IUserRepository;

@Service
public class ForgotPasswordService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private ForgotPasswordRepository forgotPasswordRepository;

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public ForgotPassword findBuUserId(Long userid){
        return forgotPasswordRepository.findByUserId(userid);
    }
    
    public void save (ForgotPassword forgotPassword){
        forgotPasswordRepository.save(forgotPassword);
    }

    public ForgotPassword findByOtpAndUser(int otp){
        return forgotPasswordRepository.findByOtp(otp);
    }

    public ForgotPassword findByOtpAndUser1(int otp, Long user){
        return forgotPasswordRepository.findByOtpAndUser(otp, user);
    }

    public void deleteById(int id){
        forgotPasswordRepository.deleteById(id);
    }
}
