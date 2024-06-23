package com.bookstore.controller;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookstore.dto.MailBody;
import com.bookstore.entity.ForgotPassword;
import com.bookstore.entity.User;
import com.bookstore.services.EmailService;
import com.bookstore.services.ForgotPasswordService;
import com.bookstore.services.UserServices;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;





@Controller
@RequestMapping("/forgotpassword")
public class ForgotPasswordController {

    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @Autowired
    private UserServices userServices;

    @Autowired
    private EmailService emailService;

    @GetMapping("/verify")
    public String forgotpassword(Model model) {
        model.addAttribute("error", false);
        model.addAttribute("errordetail","");
        return "/user/forgotpassword";
    }
    

    @PostMapping("/verify")
    public String forgotpassword(@RequestParam("email") String email, Model model) {
        User user = forgotPasswordService.findByEmail(email);   
        if(user == null){
            model.addAttribute("error", true);
            model.addAttribute("errordetail", "Tài khoản không tồn tại");
            return "/user/forgotpassword";
        }  
        ForgotPassword forgotPassword1 = forgotPasswordService.findBuUserId(user.getId());
        if(forgotPassword1 != null){
            model.addAttribute("email",email);
            return "/user/otp";
        }
        int otp = otpgenerator();
        MailBody mailBody = MailBody.builder()
            .to(email)
            .text("Day la ma otp cua ban: " + otp)
            .subject(" OTP for forgot password")
            .build();

        ForgotPassword forgotPassword = ForgotPassword.builder()
        .otp(otp)
        .exDate(new Date(System.currentTimeMillis() + 280 * 1000))
        .user(user)
        .build();
        emailService.sendmessage(mailBody);
        forgotPasswordService.save(forgotPassword);
        model.addAttribute("email",email);
        model.addAttribute("status", "");
        return "/user/otp";
    }
    
    @GetMapping("/verifyotp")
    public String verifyotp(Model model) {
        model.addAttribute("status", "");
        return "/user/otp";
    }
    
    @PostMapping("/verifyotp")
    public String verifyotp(@RequestParam("email") String email, @RequestParam("otp") int otp, Model model) {
        User user = forgotPasswordService.findByEmail(email);
        if (user != null) {
            ForgotPassword forgotPassword = forgotPasswordService.findByOtpAndUser1(otp, user.getId());
            if (forgotPassword != null) {
                if (forgotPassword.getExDate().before(new Date())) {
                    model.addAttribute("status", "otp het han");
                    return "/user/otp";
                }
            } else {
                model.addAttribute("status", "user that bai");
                return "/user/otp";
            }
            model.addAttribute("email", email);
            forgotPasswordService.deleteById(forgotPassword.getId());
            return "/user/resetpassword";
        }
        return "/user/otp";
    }

    @GetMapping("/resetPassword")
    public String resetPassword(Model model) {
        return "/user/resetpassword";
    }
    
    @PostMapping("/resetPassword")
    public String resetPassword(@RequestParam("email") String email, @RequestParam("password") String password) {
        User user = forgotPasswordService.findByEmail(email);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        userServices.save(user);
        return "/user/login";
    }
    
    private int otpgenerator(){
        Random random = new Random();
        return random.nextInt(100000, 999999);
    }
}   
