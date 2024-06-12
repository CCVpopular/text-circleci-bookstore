package com.bookstore.validator;

import org.springframework.beans.factory.annotation.Autowired;

import com.bookstore.repository.IUserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidUserIdValidator implements ConstraintValidator<ValidUserId,Long> {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public boolean isValid(Long userId, ConstraintValidatorContext constraintValidatorContext){
        if(userRepository == null)
            return true;
        return userRepository.findById(userId) == null;
    }
    
}
