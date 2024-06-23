package com.bookstore.utils;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.bookstore.entity.User;
import com.bookstore.services.UserServices;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserServices userServices;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        
        DefaultOAuth2User user1 = (DefaultOAuth2User)authentication.getPrincipal();
        String email = user1.getAttribute("email").toString();
        String name = user1.getAttribute("name").toString();
        User user2 = userServices.findByEmail(email);
        if(user2 == null){
            User user = new User();
            user.setName(name);
            user.setEmail(email);
            user.setPassword("null"); 
            user.setUsername(email); 
            userServices.save(user);
        }
        response.sendRedirect("/");
    }

}
