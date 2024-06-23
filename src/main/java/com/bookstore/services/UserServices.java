package com.bookstore.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.entity.User;
import com.bookstore.repository.IRoleRepository;
import com.bookstore.repository.IUserRepository;

@Service
public class UserServices {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    public void save(User user){
        userRepository.save(user);
        Long userId = userRepository.getUserIdByUserName(user.getUsername());
        Long roleId = roleRepository.getRoleIdByName("USER");
        if(roleId != 0 && userId != 0){
            userRepository.addRoleToUser(userId, roleId);
        }
    }
    public User findByEmail (String user){
        return userRepository.findByEmail(user);
    }
    public List<User> findAllUser(){
        return userRepository.findAll();
    }

    public void updaterole(Long userId, Long roleId){
        userRepository.updateRoleToUser(userId, roleId);
    }

    public Long getRoleIdByUserName(String name){
        return roleRepository.getRoleIdByName(name);
    }
}
